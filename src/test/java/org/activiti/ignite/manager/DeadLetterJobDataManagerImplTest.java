package org.activiti.ignite.manager;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.DeadLetterJobQueryImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.DeadLetterJobEntity;
import org.activiti.engine.impl.persistence.entity.DeadLetterJobEntityImpl;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by ekonovalov on 15.05.2017.
 */
public class DeadLetterJobDataManagerImplTest extends AbstractDataManagerImplTest {

    @Test
    public void findJobCountById() throws Exception {
        DeadLetterJobEntity entity1 = new DeadLetterJobEntityImpl();
        String id1 = config.getIdGenerator().getNextId();
        entity1.setId(id1);
        config.getDeadLetterJobDataManager().insert(entity1);

        DeadLetterJobEntity entity2 = new DeadLetterJobEntityImpl();
        String id2 = config.getIdGenerator().getNextId();
        entity2.setId(id2);
        config.getDeadLetterJobDataManager().insert(entity2);

        long count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().jobId(id1));
        assertTrue(count == 1);

        config.getDeadLetterJobDataManager().delete(entity1);
        config.getDeadLetterJobDataManager().delete(entity2);
    }

    @Test
    public void findJobCountByProcessInstanceId() throws Exception {
        DeadLetterJobEntity entity1 = new DeadLetterJobEntityImpl();
        String id1 = config.getIdGenerator().getNextId();
        entity1.setProcessInstanceId(id1);
        config.getDeadLetterJobDataManager().insert(entity1);

        DeadLetterJobEntity entity2 = new DeadLetterJobEntityImpl();
        String id2 = config.getIdGenerator().getNextId();
        entity2.setProcessInstanceId(id2);
        config.getDeadLetterJobDataManager().insert(entity2);

        long count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().processInstanceId(id1));
        assertTrue(count == 1);

        config.getDeadLetterJobDataManager().delete(entity1);
        config.getDeadLetterJobDataManager().delete(entity2);
    }

    @Test
    public void findJobCountByExecutionId() throws Exception {
        DeadLetterJobEntity entity1 = new DeadLetterJobEntityImpl();
        String id1 = config.getIdGenerator().getNextId();
        entity1.setExecutionId(id1);
        config.getDeadLetterJobDataManager().insert(entity1);

        DeadLetterJobEntity entity2 = new DeadLetterJobEntityImpl();
        String id2 = config.getIdGenerator().getNextId();
        entity2.setExecutionId(id2);
        config.getDeadLetterJobDataManager().insert(entity2);

        long count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().executionId(id1));
        assertTrue(count == 1);

        config.getDeadLetterJobDataManager().delete(entity1);
        config.getDeadLetterJobDataManager().delete(entity2);
    }

    @Test
    public void findJobCountProcessDefinitionId() throws Exception {
        DeadLetterJobEntity entity1 = new DeadLetterJobEntityImpl();
        String id1 = config.getIdGenerator().getNextId();
        entity1.setProcessDefinitionId(id1);
        config.getDeadLetterJobDataManager().insert(entity1);

        DeadLetterJobEntity entity2 = new DeadLetterJobEntityImpl();
        String id2 = config.getIdGenerator().getNextId();
        entity2.setProcessDefinitionId(id2);
        config.getDeadLetterJobDataManager().insert(entity2);

        long count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().processDefinitionId(id1));
        assertTrue(count == 1);

        config.getDeadLetterJobDataManager().delete(entity1);
        config.getDeadLetterJobDataManager().delete(entity2);
    }

    @Test
    public void findJobCountExecutable() throws Exception {
        DeadLetterJobEntity entity1 = new DeadLetterJobEntityImpl();
        config.getDeadLetterJobDataManager().insert(entity1);

        DeadLetterJobEntity entity2 = new DeadLetterJobEntityImpl();
        entity2.setDuedate(new Date(new Date().getTime() + 1000000));
        config.getDeadLetterJobDataManager().insert(entity2);

        DeadLetterJobEntity entity3 = new DeadLetterJobEntityImpl();
        entity3.setDuedate(new Date(new Date().getTime() - 1000000));
        config.getDeadLetterJobDataManager().insert(entity3);

        long count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().executable());
        assertTrue(count == 2);

        config.getDeadLetterJobDataManager().delete(entity1);
        config.getDeadLetterJobDataManager().delete(entity2);
        config.getDeadLetterJobDataManager().delete(entity3);
    }
/*
    @Test
    public void findJobCountType() throws Exception {
        DeadLetterJobEntity entity1 = new DeadLetterJobEntityImpl();
        entity1.setJobType("timer");
        config.getDeadLetterJobDataManager().insert(entity1);

        DeadLetterJobEntity entity2 = new DeadLetterJobEntityImpl();
        entity2.setJobType("message");
        config.getDeadLetterJobDataManager().insert(entity2);


        long count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().timers());
        assertTrue(count == 1);

        count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().messages());
        assertTrue(count == 1);

        config.getDeadLetterJobDataManager().delete(entity1);
        config.getDeadLetterJobDataManager().delete(entity2);
    }
*/

    @Test
    public void findJobCountDuedate() throws Exception {
        DeadLetterJobEntity entity1 = new DeadLetterJobEntityImpl();
        config.getDeadLetterJobDataManager().insert(entity1);

        DeadLetterJobEntity entity2 = new DeadLetterJobEntityImpl();
        entity2.setDuedate(new Date(new Date().getTime() + 1000000));
        config.getDeadLetterJobDataManager().insert(entity2);

        DeadLetterJobEntity entity3 = new DeadLetterJobEntityImpl();
        entity3.setDuedate(new Date(new Date().getTime() - 1000000));
        config.getDeadLetterJobDataManager().insert(entity3);

        long count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().duedateHigherThan(new Date()));
        assertTrue(count == 1);
        count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().duedateLowerThan(new Date()));
        assertTrue(count == 1);

        config.getDeadLetterJobDataManager().delete(entity1);
        config.getDeadLetterJobDataManager().delete(entity2);
        config.getDeadLetterJobDataManager().delete(entity3);
    }

    @Test
    public void findJobCountWithException() throws Exception {
        DeadLetterJobEntity entity1 = new DeadLetterJobEntityImpl();
        String id1 = config.getIdGenerator().getNextId();
        entity1.setExceptionMessage(id1);
        config.getDeadLetterJobDataManager().insert(entity1);

        DeadLetterJobEntity entity2 = new DeadLetterJobEntityImpl();
        String id2 = config.getIdGenerator().getNextId();
        entity2.setExceptionStacktrace(id2);
        config.getDeadLetterJobDataManager().insert(entity2);

        DeadLetterJobEntity entity3 = new DeadLetterJobEntityImpl();
        config.getDeadLetterJobDataManager().insert(entity3);

        long count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().withException());
        assertTrue(count == 2);

        config.getDeadLetterJobDataManager().delete(entity1);
        config.getDeadLetterJobDataManager().delete(entity2);
        config.getDeadLetterJobDataManager().delete(entity3);
    }

    @Test
    public void findJobCountTenantId() throws Exception {
        DeadLetterJobEntity entity1 = new DeadLetterJobEntityImpl();
        String id1 = config.getIdGenerator().getNextId();
        entity1.setTenantId(id1);
        config.getDeadLetterJobDataManager().insert(entity1);

        DeadLetterJobEntity entity2 = new DeadLetterJobEntityImpl();
        config.getDeadLetterJobDataManager().insert(entity2);

        long count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().jobTenantId(id1));
        assertTrue(count == 1);
        count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().jobTenantIdLike(id1.substring(3)));
        assertTrue(count == 1);
        count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().jobWithoutTenantId());
        assertTrue(count == 1);

        config.getDeadLetterJobDataManager().delete(entity1);
        config.getDeadLetterJobDataManager().delete(entity2);
    }

}
