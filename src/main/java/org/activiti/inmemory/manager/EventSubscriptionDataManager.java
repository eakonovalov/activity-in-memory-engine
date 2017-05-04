package org.activiti.inmemory.manager;

import org.activiti.engine.impl.EventSubscriptionQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.CompensateEventSubscriptionEntity;
import org.activiti.engine.impl.persistence.entity.EventSubscriptionEntity;
import org.activiti.engine.impl.persistence.entity.MessageEventSubscriptionEntity;
import org.activiti.engine.impl.persistence.entity.SignalEventSubscriptionEntity;
import org.activiti.ignite.entity.CompensateEventSubscriptionEntityImpl;
import org.activiti.ignite.entity.MessageEventSubscriptionEntityImpl;
import org.activiti.ignite.entity.SignalEventSubscriptionEntityImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joram Barrez
 */
public class EventSubscriptionDataManager extends AbstractDataManager<EventSubscriptionEntity> implements org.activiti.engine.impl.persistence.entity.data.EventSubscriptionDataManager {

    public EventSubscriptionDataManager(ProcessEngineConfigurationImpl processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    public EventSubscriptionEntity create() {
        throw new UnsupportedOperationException();
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

    public long findEventSubscriptionCountByQueryCriteria(EventSubscriptionQueryImpl eventSubscriptionQueryImpl) {
        throw new UnsupportedOperationException();
    }

    public List<EventSubscriptionEntity> findEventSubscriptionsByQueryCriteria(EventSubscriptionQueryImpl eventSubscriptionQueryImpl, Page page) {
        throw new UnsupportedOperationException();
    }

    public List<MessageEventSubscriptionEntity> findMessageEventSubscriptionsByProcessInstanceAndEventName(String processInstanceId, String eventName) {
        throw new UnsupportedOperationException();
    }

    public List<SignalEventSubscriptionEntity> findSignalEventSubscriptionsByEventName(String eventName, String tenantId) {
        throw new UnsupportedOperationException();
    }

    public List<SignalEventSubscriptionEntity> findSignalEventSubscriptionsByProcessInstanceAndEventName(String processInstanceId, String eventName) {
        throw new UnsupportedOperationException();
    }

    public List<SignalEventSubscriptionEntity> findSignalEventSubscriptionsByNameAndExecution(String name, String executionId) {
        throw new UnsupportedOperationException();
    }

    public List<EventSubscriptionEntity> findEventSubscriptionsByExecutionAndType(String executionId, String type) {
        throw new UnsupportedOperationException();
    }

    public List<EventSubscriptionEntity> findEventSubscriptionsByProcessInstanceAndActivityId(String processInstanceId, String activityId, String type) {
        throw new UnsupportedOperationException();
    }

    public List<EventSubscriptionEntity> findEventSubscriptionsByExecution(String executionId) {
        List<EventSubscriptionEntity> results = new ArrayList<EventSubscriptionEntity>();
        for (EventSubscriptionEntity eventSubscriptionEntity : entities.values()) {
            if (eventSubscriptionEntity.getExecutionId() != null && eventSubscriptionEntity.getExecutionId().equals(executionId)) {
                results.add(eventSubscriptionEntity);
            }
        }
        return results;
    }

    public List<EventSubscriptionEntity> findEventSubscriptionsByTypeAndProcessDefinitionId(String type, String processDefinitionId, String tenantId) {
        throw new UnsupportedOperationException();
    }

    public List<EventSubscriptionEntity> findEventSubscriptionsByConfiguration(String type, String configuration, String tenantId) {
        throw new UnsupportedOperationException();
    }

    public List<EventSubscriptionEntity> findEventSubscriptionsByName(String type, String eventName, String tenantId) {
        throw new UnsupportedOperationException();
    }

    public List<EventSubscriptionEntity> findEventSubscriptionsByNameAndExecution(String type, String eventName, String executionId) {
        throw new UnsupportedOperationException();
    }

    public MessageEventSubscriptionEntity findMessageStartEventSubscriptionByName(String messageName, String tenantId) {
        throw new UnsupportedOperationException();
    }

    public void updateEventSubscriptionTenantId(String oldTenantId, String newTenantId) {
        throw new UnsupportedOperationException();
    }

    public void deleteEventSubscriptionsForProcessDefinition(String processDefinitionId) {
        throw new UnsupportedOperationException();
    }

}
