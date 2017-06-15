package org.activiti.ignite.manager;

import org.activiti.engine.impl.DeploymentQueryImpl;
import org.activiti.engine.impl.EventSubscriptionQueryImpl;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.*;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ekonovalov on 09.06.2017.
 */
public class EventSubscriptionDataManagerImplTest extends AbstractDataManagerImplTest {

    @Test
    public void findEventSubscriptionsByIdQueryCriteria() throws Exception {
        EventSubscriptionEntity entity1 = config.getEventSubscriptionDataManager().createCompensateEventSubscription();
        EventSubscriptionEntity entity2 = config.getEventSubscriptionDataManager().createMessageEventSubscription();
        EventSubscriptionEntity entity3 = config.getEventSubscriptionDataManager().createSignalEventSubscription();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setId(id1);
            config.getEventSubscriptionDataManager().insert(entity1);

            String id2 = config.getIdGenerator().getNextId();
            entity2.setId(id2);
            config.getEventSubscriptionDataManager().insert(entity2);

            String id3 = config.getIdGenerator().getNextId();
            entity3.setId(id3);
            config.getEventSubscriptionDataManager().insert(entity3);

            List<EventSubscriptionEntity> entities = config.getEventSubscriptionDataManager().findEventSubscriptionsByQueryCriteria(new EventSubscriptionQueryImpl((CommandContext) null).eventSubscriptionId(id1), null);
            assertTrue(entities.size() == 1);
            assertTrue(entities.get(0) instanceof CompensateEventSubscriptionEntityImpl);

            long count = config.getEventSubscriptionDataManager().findEventSubscriptionCountByQueryCriteria(new EventSubscriptionQueryImpl((CommandContext) null).eventSubscriptionId(id1));
            assertTrue(count == 1);
        } finally {
            config.getEventSubscriptionDataManager().delete(entity1);
            config.getEventSubscriptionDataManager().delete(entity2);
            config.getEventSubscriptionDataManager().delete(entity3);
        }
    }

    @Test
    public void findMessageEventSubscriptionsByProcessInstanceAndEventName() throws Exception {
        EventSubscriptionEntity entity1 = config.getEventSubscriptionDataManager().createMessageEventSubscription();
        EventSubscriptionEntity entity2 = config.getEventSubscriptionDataManager().createSignalEventSubscription();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setProcessInstanceId(id1);
            entity1.setEventName("name1");
            config.getEventSubscriptionDataManager().insert(entity1);

            entity2.setProcessInstanceId(id1);
            entity2.setEventName("name1");
            config.getEventSubscriptionDataManager().insert(entity2);

            List<MessageEventSubscriptionEntity> events = config.getEventSubscriptionDataManager().findMessageEventSubscriptionsByProcessInstanceAndEventName(id1, "name1");
            assertTrue(events.size() == 1);
        } finally {
            config.getEventSubscriptionDataManager().delete(entity1);
            config.getEventSubscriptionDataManager().delete(entity2);
        }
    }

    @Test
    public void findSignalEventSubscriptionsByEventName() throws Exception {
        EventSubscriptionEntity entity1 = config.getEventSubscriptionDataManager().createSignalEventSubscription();
        EventSubscriptionEntity entity2 = config.getEventSubscriptionDataManager().createMessageEventSubscription();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setTenantId(id1);
            entity1.setEventName("name1");
            config.getEventSubscriptionDataManager().insert(entity1);

            entity2.setTenantId(id1);
            entity2.setEventName("name1");
            config.getEventSubscriptionDataManager().insert(entity2);

            List<SignalEventSubscriptionEntity> events = config.getEventSubscriptionDataManager().findSignalEventSubscriptionsByEventName("name1", id1);
            assertTrue(events.size() == 1);
        } finally {
            config.getEventSubscriptionDataManager().delete(entity1);
            config.getEventSubscriptionDataManager().delete(entity2);
        }
    }

    @Test
    public void findSignalEventSubscriptionsByProcessInstanceAndEventName() throws Exception {
        EventSubscriptionEntity entity1 = config.getEventSubscriptionDataManager().createSignalEventSubscription();
        EventSubscriptionEntity entity2 = config.getEventSubscriptionDataManager().createMessageEventSubscription();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setProcessInstanceId(id1);
            entity1.setEventName("name1");
            config.getEventSubscriptionDataManager().insert(entity1);

            entity2.setProcessInstanceId(id1);
            entity2.setEventName("name1");
            config.getEventSubscriptionDataManager().insert(entity2);

            List<SignalEventSubscriptionEntity> events = config.getEventSubscriptionDataManager().findSignalEventSubscriptionsByProcessInstanceAndEventName(id1, "name1");
            assertTrue(events.size() == 1);
        } finally {
            config.getEventSubscriptionDataManager().delete(entity1);
            config.getEventSubscriptionDataManager().delete(entity2);
        }
    }

    @Test
    public void findSignalEventSubscriptionsByNameAndExecution() throws Exception {
        EventSubscriptionEntity entity1 = config.getEventSubscriptionDataManager().createSignalEventSubscription();
        EventSubscriptionEntity entity2 = config.getEventSubscriptionDataManager().createMessageEventSubscription();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setExecutionId(id1);
            entity1.setEventName("name1");
            config.getEventSubscriptionDataManager().insert(entity1);

            entity2.setExecutionId(id1);
            entity2.setEventName("name1");
            config.getEventSubscriptionDataManager().insert(entity2);

            List<SignalEventSubscriptionEntity> events = config.getEventSubscriptionDataManager().findSignalEventSubscriptionsByNameAndExecution("name1", id1);
            assertTrue(events.size() == 1);
        } finally {
            config.getEventSubscriptionDataManager().delete(entity1);
            config.getEventSubscriptionDataManager().delete(entity2);
        }
    }

    @Test
    public void findEventSubscriptionsByExecutionAndType() throws Exception {
        EventSubscriptionEntity entity1 = config.getEventSubscriptionDataManager().createSignalEventSubscription();
        EventSubscriptionEntity entity2 = config.getEventSubscriptionDataManager().createMessageEventSubscription();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setExecutionId(id1);
            config.getEventSubscriptionDataManager().insert(entity1);

            entity2.setExecutionId(id1);
            config.getEventSubscriptionDataManager().insert(entity2);

            List<EventSubscriptionEntity> events = config.getEventSubscriptionDataManager().findEventSubscriptionsByExecutionAndType(id1, SignalEventSubscriptionEntity.EVENT_TYPE);
            assertTrue(events.size() == 1);
            assertTrue(events.get(0) instanceof SignalEventSubscriptionEntityImpl);
        } finally {
            config.getEventSubscriptionDataManager().delete(entity1);
            config.getEventSubscriptionDataManager().delete(entity2);
        }
    }

    @Test
    public void findEventSubscriptionsByProcessInstanceAndActivityId() throws Exception {
        EventSubscriptionEntity entity1 = config.getEventSubscriptionDataManager().createSignalEventSubscription();
        EventSubscriptionEntity entity2 = config.getEventSubscriptionDataManager().createMessageEventSubscription();
        try {
            String processInstanceId = config.getIdGenerator().getNextId();
            entity1.setProcessInstanceId(processInstanceId);
            String activityId = config.getIdGenerator().getNextId();
            entity1.setActivityId(activityId);
            config.getEventSubscriptionDataManager().insert(entity1);

            entity2.setProcessInstanceId(processInstanceId);
            entity2.setActivityId(activityId);
            config.getEventSubscriptionDataManager().insert(entity2);

            List<EventSubscriptionEntity> events = config.getEventSubscriptionDataManager().findEventSubscriptionsByProcessInstanceAndActivityId(processInstanceId, activityId, SignalEventSubscriptionEntity.EVENT_TYPE);
            assertTrue(events.size() == 1);
            assertTrue(events.get(0) instanceof SignalEventSubscriptionEntityImpl);
        } finally {
            config.getEventSubscriptionDataManager().delete(entity1);
            config.getEventSubscriptionDataManager().delete(entity2);
        }
    }

    @Test
    public void findEventSubscriptionsByExecution() throws Exception {
        EventSubscriptionEntity entity1 = config.getEventSubscriptionDataManager().createSignalEventSubscription();
        EventSubscriptionEntity entity2 = config.getEventSubscriptionDataManager().createMessageEventSubscription();
        try {
            String executionId = config.getIdGenerator().getNextId();
            entity1.setExecutionId(executionId);
            config.getEventSubscriptionDataManager().insert(entity1);

            entity2.setExecutionId(executionId);
            config.getEventSubscriptionDataManager().insert(entity2);

            List<EventSubscriptionEntity> events = config.getEventSubscriptionDataManager().findEventSubscriptionsByExecution(executionId);
            assertTrue(events.size() == 2);
        } finally {
            config.getEventSubscriptionDataManager().delete(entity1);
            config.getEventSubscriptionDataManager().delete(entity2);
        }
    }

    @Test
    public void findEventSubscriptionsByTypeAndProcessDefinitionId() throws Exception {
        EventSubscriptionEntity entity1 = config.getEventSubscriptionDataManager().createSignalEventSubscription();
        EventSubscriptionEntity entity2 = config.getEventSubscriptionDataManager().createMessageEventSubscription();
        try {
            String processDefinitionId = config.getIdGenerator().getNextId();
            entity1.setProcessDefinitionId(processDefinitionId);
            String tenantId = config.getIdGenerator().getNextId();
            entity1.setTenantId(tenantId);
            config.getEventSubscriptionDataManager().insert(entity1);

            entity2.setProcessDefinitionId(processDefinitionId);
            entity2.setTenantId(tenantId);
            config.getEventSubscriptionDataManager().insert(entity2);

            List<EventSubscriptionEntity> events = config.getEventSubscriptionDataManager().findEventSubscriptionsByTypeAndProcessDefinitionId(SignalEventSubscriptionEntity.EVENT_TYPE, processDefinitionId, tenantId);
            assertTrue(events.size() == 1);
        } finally {
            config.getEventSubscriptionDataManager().delete(entity1);
            config.getEventSubscriptionDataManager().delete(entity2);
        }
    }

    @Test
    public void findEventSubscriptionsByName() throws Exception {
        EventSubscriptionEntity entity1 = config.getEventSubscriptionDataManager().createSignalEventSubscription();
        EventSubscriptionEntity entity2 = config.getEventSubscriptionDataManager().createMessageEventSubscription();
        try {
            String tenantId = config.getIdGenerator().getNextId();
            entity1.setTenantId(tenantId);
            entity1.setEventName("name1");
            config.getEventSubscriptionDataManager().insert(entity1);

            entity2.setTenantId(tenantId);
            entity1.setEventName("name1");
            config.getEventSubscriptionDataManager().insert(entity2);

            List<EventSubscriptionEntity> events = config.getEventSubscriptionDataManager().findEventSubscriptionsByName(SignalEventSubscriptionEntity.EVENT_TYPE, "name1", tenantId);
            assertTrue(events.size() == 1);
        } finally {
            config.getEventSubscriptionDataManager().delete(entity1);
            config.getEventSubscriptionDataManager().delete(entity2);
        }
    }

    @Test
    public void findEventSubscriptionsByNameAndExecution() throws Exception {
        EventSubscriptionEntity entity1 = config.getEventSubscriptionDataManager().createSignalEventSubscription();
        EventSubscriptionEntity entity2 = config.getEventSubscriptionDataManager().createMessageEventSubscription();
        try {
            String executionId = config.getIdGenerator().getNextId();
            entity1.setExecutionId(executionId);
            entity1.setEventName("name1");
            config.getEventSubscriptionDataManager().insert(entity1);

            entity2.setExecutionId(executionId);
            entity1.setEventName("name1");
            config.getEventSubscriptionDataManager().insert(entity2);

            List<EventSubscriptionEntity> events = config.getEventSubscriptionDataManager().findEventSubscriptionsByNameAndExecution(SignalEventSubscriptionEntity.EVENT_TYPE, "name1", executionId);
            assertTrue(events.size() == 1);
        } finally {
            config.getEventSubscriptionDataManager().delete(entity1);
            config.getEventSubscriptionDataManager().delete(entity2);
        }

    }

    @Test
    public void findMessageStartEventSubscriptionByName() throws Exception {
        EventSubscriptionEntity entity1 = config.getEventSubscriptionDataManager().createMessageEventSubscription();
        EventSubscriptionEntity entity2 = config.getEventSubscriptionDataManager().createSignalEventSubscription();
        try {
            String tenantId = config.getIdGenerator().getNextId();
            entity1.setTenantId(tenantId);
            entity1.setEventName("name1");
            config.getEventSubscriptionDataManager().insert(entity1);

            entity2.setTenantId(tenantId);
            entity2.setEventName("name1");
            config.getEventSubscriptionDataManager().insert(entity2);

            MessageEventSubscriptionEntity event = config.getEventSubscriptionDataManager().findMessageStartEventSubscriptionByName("name1", tenantId);
            assertNotNull(event);
        } finally {
            config.getEventSubscriptionDataManager().delete(entity1);
            config.getEventSubscriptionDataManager().delete(entity2);
        }
    }

    @Test
    public void updateEventSubscriptionTenantId() throws Exception {

    }

    @Test
    public void deleteEventSubscriptionsForProcessDefinition() throws Exception {

    }

}