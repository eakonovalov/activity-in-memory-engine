package org.activiti.ignite.manager;

import org.activiti.engine.impl.EventSubscriptionQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.*;
import org.activiti.engine.impl.persistence.entity.data.EventSubscriptionDataManager;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class EventSubscriptionDataManagerImpl extends AbstractDataManager<EventSubscriptionEntity, EventSubscriptionEntityImpl> implements EventSubscriptionDataManager {

    @Autowired
    @Qualifier("eventSubscriptionEntityCache")
    private CacheConfiguration<String, EventSubscriptionEntity> config;

    public EventSubscriptionDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, EventSubscriptionEntity> getConfig() {
        return config;
    }

    public CompensateEventSubscriptionEntity createCompensateEventSubscription() {
        return new CompensateEventSubscriptionEntityImpl();
    }

    public MessageEventSubscriptionEntity createMessageEventSubscription() {
        return new MessageEventSubscriptionEntityImpl();
    }

    public SignalEventSubscriptionEntity createSignalEventSubscription() {
        return new SignalEventSubscriptionEntityImpl();
    }

    private QueryBuilder getQueryBuilder(EventSubscriptionQueryImpl eventSubscriptionQuery) {
        QueryBuilder result = new QueryBuilder();
        if (eventSubscriptionQuery.getEventSubscriptionId() != null) {
            result.appendCondition("e.id = ?");
            result.appendArgs(eventSubscriptionQuery.getEventSubscriptionId());
        }
        if (eventSubscriptionQuery.getEventName() != null) {
            result.appendCondition("e.eventName = ?");
            result.appendArgs(eventSubscriptionQuery.getEventName());
        }
        if (eventSubscriptionQuery.getEventType() != null) {
            result.appendCondition("e.eventType = ?");
            result.appendArgs(eventSubscriptionQuery.getEventType());
        }
        if (eventSubscriptionQuery.getExecutionId() != null) {
            result.appendCondition("e.executionId = ?");
            result.appendArgs(eventSubscriptionQuery.getExecutionId());
        }
        if (eventSubscriptionQuery.getProcessInstanceId() != null) {
            result.appendCondition("e.processInstanceId = ?");
            result.appendArgs(eventSubscriptionQuery.getProcessInstanceId());
        }
        if (eventSubscriptionQuery.getActivityId() != null) {
            result.appendCondition("e.activityId = ?");
            result.appendArgs(eventSubscriptionQuery.getActivityId());
        }
        if (eventSubscriptionQuery.getTenantId() != null) {
            result.appendCondition("e.tenantId = ?");
            result.appendArgs(eventSubscriptionQuery.getTenantId());
        }

        return result;
    }

    public long findEventSubscriptionCountByQueryCriteria(EventSubscriptionQueryImpl eventSubscriptionQuery) {
        StringBuilder query = new StringBuilder();
        QueryBuilder queryBuilder = getQueryBuilder(eventSubscriptionQuery);
        queryBuilder.setSelectClause("COUNT(*)");
        queryBuilder.setFromClause("CompensateEventSubscriptionEntityImpl e");
        query.append(queryBuilder.getQuery());
        queryBuilder.setFromClause("MessageEventSubscriptionEntityImpl e");
        query.append(" UNION ").append(queryBuilder.getQuery());
        queryBuilder.setFromClause("SignalEventSubscriptionEntityImpl e");
        query.append(" UNION ").append(queryBuilder.getQuery());

        List<Object> args = new ArrayList<>();
        args.addAll(queryBuilder.getArgs());
        args.addAll(queryBuilder.getArgs());
        args.addAll(queryBuilder.getArgs());

        SqlFieldsQuery qry = new SqlFieldsQuery(query.toString()).setArgs(args.toArray());
        Collection<List<?>> res = getCache().query(qry).getAll();

        return (Long) res.iterator().next().get(0);
    }

    public List<EventSubscriptionEntity> findEventSubscriptionsByQueryCriteria(EventSubscriptionQueryImpl eventSubscriptionQuery, Page page) {
        StringBuilder query = new StringBuilder();
        QueryBuilder queryBuilder = getQueryBuilder(eventSubscriptionQuery);
        queryBuilder.setSelectClause("*");
        queryBuilder.setFromClause("CompensateEventSubscriptionEntityImpl e");
        query.append(queryBuilder.getQuery());
        queryBuilder.setFromClause("MessageEventSubscriptionEntityImpl e");
        query.append(" UNION ").append(queryBuilder.getQuery());
        queryBuilder.setFromClause("SignalEventSubscriptionEntityImpl e");
        query.append(" UNION ").append(queryBuilder.getQuery());

        List<Object> args = new ArrayList<>();
        args.addAll(queryBuilder.getArgs());
        args.addAll(queryBuilder.getArgs());
        args.addAll(queryBuilder.getArgs());

        SqlFieldsQuery qry = new SqlFieldsQuery(query.toString()).setArgs(args.toArray());
        Iterator<List<?>> itr = getCache().query(qry).iterator();
        List<EventSubscriptionEntity> result = new ArrayList<>();
        while (itr.hasNext()) {
            List<?> o = itr.next();
            result.add((EventSubscriptionEntity) o.get(1));
        }

        return result;
    }

    public List<MessageEventSubscriptionEntity> findMessageEventSubscriptionsByProcessInstanceAndEventName(String processInstanceId, String eventName) {
        return findList(MessageEventSubscriptionEntityImpl.class, "processInstanceId = ? AND eventName = ?", processInstanceId, eventName).stream().map(e -> (MessageEventSubscriptionEntity) e).collect(Collectors.toList());
    }

    public List<SignalEventSubscriptionEntity> findSignalEventSubscriptionsByEventName(String eventName, String tenantId) {
        return findList(SignalEventSubscriptionEntityImpl.class, "eventName = ? AND tenantId = ?", eventName, tenantId).stream().map(e -> (SignalEventSubscriptionEntity) e).collect(Collectors.toList());
    }

    public List<SignalEventSubscriptionEntity> findSignalEventSubscriptionsByProcessInstanceAndEventName(String processInstanceId, String eventName) {
        return findList(SignalEventSubscriptionEntityImpl.class, "processInstanceId = ? AND eventName = ?", processInstanceId, eventName).stream().map(e -> (SignalEventSubscriptionEntity) e).collect(Collectors.toList());
    }

    public List<SignalEventSubscriptionEntity> findSignalEventSubscriptionsByNameAndExecution(String name, String executionId) {
        return findList(SignalEventSubscriptionEntityImpl.class, "eventName = ? AND executionId = ?", name, executionId).stream().map(e -> (SignalEventSubscriptionEntity) e).collect(Collectors.toList());
    }

    public List<EventSubscriptionEntity> findEventSubscriptionsByExecutionAndType(String executionId, String type) {
        if(CompensateEventSubscriptionEntity.EVENT_TYPE.equals(type)) {
            return findList(CompensateEventSubscriptionEntityImpl.class, "executionId = ?", executionId);
        }
        else if(MessageEventSubscriptionEntity.EVENT_TYPE.equals(type)) {
            return findList(MessageEventSubscriptionEntityImpl.class, "executionId = ?", executionId);
        }
        else if(SignalEventSubscriptionEntity.EVENT_TYPE.equals(type)) {
            return findList(SignalEventSubscriptionEntityImpl.class, "executionId = ?", executionId);
        }

        throw new UnsupportedOperationException("Unknown event type: " + type);
    }

    public List<EventSubscriptionEntity> findEventSubscriptionsByProcessInstanceAndActivityId(String processInstanceId, String activityId, String type) {
        if(CompensateEventSubscriptionEntity.EVENT_TYPE.equals(type)) {
            return findList(CompensateEventSubscriptionEntityImpl.class, "processInstanceId = ? AND activityId = ?", processInstanceId, activityId);
        }
        else if(MessageEventSubscriptionEntity.EVENT_TYPE.equals(type)) {
            return findList(MessageEventSubscriptionEntityImpl.class, "processInstanceId = ? AND activityId = ?", processInstanceId, activityId);
        }
        else if(SignalEventSubscriptionEntity.EVENT_TYPE.equals(type)) {
            return findList(SignalEventSubscriptionEntityImpl.class, "processInstanceId = ? AND activityId = ?", processInstanceId, activityId);
        }

        throw new UnsupportedOperationException("Unknown event type: " + type);
    }

    public List<EventSubscriptionEntity> findEventSubscriptionsByExecution(String executionId) {
        List<EventSubscriptionEntity> result = new ArrayList<>();
        result.addAll(findList(CompensateEventSubscriptionEntityImpl.class, "executionId = ?", executionId));
        result.addAll(findList(MessageEventSubscriptionEntityImpl.class, "executionId = ?", executionId));
        result.addAll(findList(SignalEventSubscriptionEntityImpl.class, "executionId = ?", executionId));

        return result;
    }

    public List<EventSubscriptionEntity> findEventSubscriptionsByTypeAndProcessDefinitionId(String type, String processDefinitionId, String tenantId) {
        if(CompensateEventSubscriptionEntity.EVENT_TYPE.equals(type)) {
            return findList(CompensateEventSubscriptionEntityImpl.class, "processDefinitionId = ? AND tenantId = ?", processDefinitionId, tenantId);
        }
        else if(MessageEventSubscriptionEntity.EVENT_TYPE.equals(type)) {
            return findList(MessageEventSubscriptionEntityImpl.class, "processDefinitionId = ? AND tenantId = ?", processDefinitionId, tenantId);
        }
        else if(SignalEventSubscriptionEntity.EVENT_TYPE.equals(type)) {
            return findList(SignalEventSubscriptionEntityImpl.class, "processDefinitionId = ? AND tenantId = ?", processDefinitionId, tenantId);
        }

        throw new UnsupportedOperationException("Unknown event type: " + type);
    }

    public List<EventSubscriptionEntity> findEventSubscriptionsByName(String type, String eventName, String tenantId) {
        if(CompensateEventSubscriptionEntity.EVENT_TYPE.equals(type)) {
            return findList(CompensateEventSubscriptionEntityImpl.class, "eventName = ? AND tenantId = ?", eventName, tenantId);
        }
        else if(MessageEventSubscriptionEntity.EVENT_TYPE.equals(type)) {
            return findList(MessageEventSubscriptionEntityImpl.class, "eventName = ? AND tenantId = ?", eventName, tenantId);
        }
        else if(SignalEventSubscriptionEntity.EVENT_TYPE.equals(type)) {
            return findList(SignalEventSubscriptionEntityImpl.class, "eventName = ? AND tenantId = ?", eventName, tenantId);
        }

        throw new UnsupportedOperationException("Unknown event type: " + type);
    }

    public List<EventSubscriptionEntity> findEventSubscriptionsByNameAndExecution(String type, String eventName, String executionId) {
        if(CompensateEventSubscriptionEntity.EVENT_TYPE.equals(type)) {
            return findList(CompensateEventSubscriptionEntityImpl.class, "eventName = ? AND executionId = ?", eventName, executionId);
        }
        else if(MessageEventSubscriptionEntity.EVENT_TYPE.equals(type)) {
            return findList(MessageEventSubscriptionEntityImpl.class, "eventName = ? AND executionId = ?", eventName, executionId);
        }
        else if(SignalEventSubscriptionEntity.EVENT_TYPE.equals(type)) {
            return findList(SignalEventSubscriptionEntityImpl.class, "eventName = ? AND executionId = ?", eventName, executionId);
        }

        throw new UnsupportedOperationException("Unknown event type: " + type);
    }

    public MessageEventSubscriptionEntity findMessageStartEventSubscriptionByName(String messageName, String tenantId) {
        return (MessageEventSubscriptionEntity) findOne(MessageEventSubscriptionEntityImpl.class, "eventName = ? AND tenantId = ?", messageName, tenantId);
    }

    public void updateEventSubscriptionTenantId(String oldTenantId, String newTenantId) {
        List<EventSubscriptionEntity> events = findEventSubscriptionsByQueryCriteria(new EventSubscriptionQueryImpl((CommandContext) null).tenantId(oldTenantId), null);
        events.forEach(e -> {
            e.setTenantId(newTenantId);
            update(e);
        });
    }

    public void deleteEventSubscriptionsForProcessDefinition(String processDefinitionId) {
        removeList(CompensateEventSubscriptionEntityImpl.class,"processDefinitionId = ?", processDefinitionId);
        removeList(MessageEventSubscriptionEntityImpl.class,"processDefinitionId = ?", processDefinitionId);
        removeList(SignalEventSubscriptionEntityImpl.class,"processDefinitionId = ?", processDefinitionId);
    }

    public EventSubscriptionEntity create() {
        throw new UnsupportedOperationException();
    }

}
