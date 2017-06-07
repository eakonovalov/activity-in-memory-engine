package org.activiti.ignite.manager;

import org.activiti.engine.impl.DeadLetterJobQueryImpl;
import org.activiti.engine.impl.persistence.entity.DeadLetterJobEntity;
import org.activiti.engine.impl.persistence.entity.DeadLetterJobEntityImpl;
import org.activiti.engine.runtime.Job;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by ekonovalov on 15.05.2017.
 */
public class DeadLetterJobDataManagerImplTest extends AbstractDataManagerImplTest {

    @Test
    public void findJobByIdQueryCriteria() {
        DeadLetterJobEntity entity1 = config.getDeadLetterJobDataManager().create();
        DeadLetterJobEntity entity2 = config.getDeadLetterJobDataManager().create();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setId(id1);
            config.getDeadLetterJobDataManager().insert(entity1);

            String id2 = config.getIdGenerator().getNextId();
            entity2.setId(id2);
            config.getDeadLetterJobDataManager().insert(entity2);

            List<Job> jobs = config.getDeadLetterJobDataManager().findJobsByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().jobId(id1), null);
            assertTrue(jobs.size() == 1);
            assertTrue(jobs.get(0) instanceof DeadLetterJobEntityImpl);

            long count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().jobId(id1));
            assertTrue(count == 1);
        } finally {
            config.getDeadLetterJobDataManager().delete(entity1);
            config.getDeadLetterJobDataManager().delete(entity2);
        }
    }

    @Test
    public void findJobByProcessInstanceIdQueryCriteria() {
        DeadLetterJobEntity entity1 = config.getDeadLetterJobDataManager().create();
        DeadLetterJobEntity entity2 = config.getDeadLetterJobDataManager().create();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setProcessInstanceId(id1);
            config.getDeadLetterJobDataManager().insert(entity1);

            String id2 = config.getIdGenerator().getNextId();
            entity2.setProcessInstanceId(id2);
            config.getDeadLetterJobDataManager().insert(entity2);

            List<Job> jobs = config.getDeadLetterJobDataManager().findJobsByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().processInstanceId(id1), null);
            assertTrue(jobs.size() == 1);
            assertTrue(jobs.get(0) instanceof DeadLetterJobEntityImpl);

            long count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().processInstanceId(id1));
            assertTrue(count == 1);
        } finally {
            config.getDeadLetterJobDataManager().delete(entity1);
            config.getDeadLetterJobDataManager().delete(entity2);
        }
    }

    @Test
    public void findJobByExecutionIdQueryCriteria() {
        DeadLetterJobEntity entity1 = config.getDeadLetterJobDataManager().create();
        DeadLetterJobEntity entity2 = config.getDeadLetterJobDataManager().create();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setExecutionId(id1);
            config.getDeadLetterJobDataManager().insert(entity1);

            String id2 = config.getIdGenerator().getNextId();
            entity2.setExecutionId(id2);
            config.getDeadLetterJobDataManager().insert(entity2);

            List<Job> jobs = config.getDeadLetterJobDataManager().findJobsByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().executionId(id1), null);
            assertTrue(jobs.size() == 1);
            assertTrue(jobs.get(0) instanceof DeadLetterJobEntityImpl);

            long count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().executionId(id1));
            assertTrue(count == 1);
        } finally {
            config.getDeadLetterJobDataManager().delete(entity1);
            config.getDeadLetterJobDataManager().delete(entity2);
        }
    }

    @Test
    public void findJobByProcessDefinitionIdQueryCriteria() {
        DeadLetterJobEntity entity1 = config.getDeadLetterJobDataManager().create();
        DeadLetterJobEntity entity2 = config.getDeadLetterJobDataManager().create();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setProcessDefinitionId(id1);
            config.getDeadLetterJobDataManager().insert(entity1);

            String id2 = config.getIdGenerator().getNextId();
            entity2.setProcessDefinitionId(id2);
            config.getDeadLetterJobDataManager().insert(entity2);

            List<Job> jobs = config.getDeadLetterJobDataManager().findJobsByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().processDefinitionId(id1), null);
            assertTrue(jobs.size() == 1);
            assertTrue(jobs.get(0) instanceof DeadLetterJobEntityImpl);

            long count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().processDefinitionId(id1));
            assertTrue(count == 1);
        } finally {
            config.getDeadLetterJobDataManager().delete(entity1);
            config.getDeadLetterJobDataManager().delete(entity2);
        }
    }

    @Test
    public void findJobByExecutableQueryCriteria() {
        DeadLetterJobEntity entity1 = config.getDeadLetterJobDataManager().create();
        DeadLetterJobEntity entity2 = config.getDeadLetterJobDataManager().create();
        DeadLetterJobEntity entity3 = config.getDeadLetterJobDataManager().create();
        try {
            config.getDeadLetterJobDataManager().insert(entity1);

            entity2.setDuedate(new Date(new Date().getTime() + 1000000));
            config.getDeadLetterJobDataManager().insert(entity2);

            entity3.setDuedate(new Date(new Date().getTime() - 1000000));
            config.getDeadLetterJobDataManager().insert(entity3);

            List<Job> jobs = config.getDeadLetterJobDataManager().findJobsByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().executable(), null);
            assertTrue(jobs.size() == 2);
            assertTrue(jobs.get(0) instanceof DeadLetterJobEntityImpl);

            long count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().executable());
            assertTrue(count == 2);
        } finally {
            config.getDeadLetterJobDataManager().delete(entity1);
            config.getDeadLetterJobDataManager().delete(entity2);
            config.getDeadLetterJobDataManager().delete(entity3);
        }
    }

    @Test
    public void findJobByTypeQueryCriteria() {
        DeadLetterJobEntity entity1 = config.getDeadLetterJobDataManager().create();
        entity1.setJobType("timer");
        config.getDeadLetterJobDataManager().insert(entity1);

        DeadLetterJobEntity entity2 = config.getDeadLetterJobDataManager().create();
        entity2.setJobType("message");
        config.getDeadLetterJobDataManager().insert(entity2);

        List<Job> jobs = config.getDeadLetterJobDataManager().findJobsByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().timers(), null);
        assertTrue(jobs.size() == 1);
        assertTrue(jobs.get(0) instanceof DeadLetterJobEntityImpl);

        jobs = config.getDeadLetterJobDataManager().findJobsByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().messages(), null);
        assertTrue(jobs.size() == 1);
        assertTrue(jobs.get(0) instanceof DeadLetterJobEntityImpl);

        long count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().timers());
        assertTrue(count == 1);

        count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().messages());
        assertTrue(count == 1);

        config.getDeadLetterJobDataManager().delete(entity1);
        config.getDeadLetterJobDataManager().delete(entity2);
    }

    @Test
    public void findJobByDuedateQueryCriteria() {
        DeadLetterJobEntity entity1 = config.getDeadLetterJobDataManager().create();
        DeadLetterJobEntity entity2 = config.getDeadLetterJobDataManager().create();
        DeadLetterJobEntity entity3 = config.getDeadLetterJobDataManager().create();
        try {
            config.getDeadLetterJobDataManager().insert(entity1);

            entity2.setDuedate(new Date(new Date().getTime() + 1000000));
            config.getDeadLetterJobDataManager().insert(entity2);

            entity3.setDuedate(new Date(new Date().getTime() - 1000000));
            config.getDeadLetterJobDataManager().insert(entity3);

            List<Job> jobs = config.getDeadLetterJobDataManager().findJobsByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().duedateHigherThan(new Date()), null);
            assertTrue(jobs.size() == 1);
            assertTrue(jobs.get(0) instanceof DeadLetterJobEntityImpl);

            long count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().duedateHigherThan(new Date()));
            assertTrue(count == 1);
            count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().duedateLowerThan(new Date()));
            assertTrue(count == 1);
        } finally {
            config.getDeadLetterJobDataManager().delete(entity1);
            config.getDeadLetterJobDataManager().delete(entity2);
            config.getDeadLetterJobDataManager().delete(entity3);
        }
    }

    @Test
    public void findJobWithExceptionQueryCriteria() {
        DeadLetterJobEntity entity1 = config.getDeadLetterJobDataManager().create();
        DeadLetterJobEntity entity2 = config.getDeadLetterJobDataManager().create();
        DeadLetterJobEntity entity3 = config.getDeadLetterJobDataManager().create();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setExceptionMessage(id1);
            config.getDeadLetterJobDataManager().insert(entity1);

            String id2 = config.getIdGenerator().getNextId();
            entity2.setExceptionStacktrace(id2);
            config.getDeadLetterJobDataManager().insert(entity2);

            config.getDeadLetterJobDataManager().insert(entity3);

            List<Job> jobs = config.getDeadLetterJobDataManager().findJobsByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().withException(), null);
            assertTrue(jobs.size() == 2);
            assertTrue(jobs.get(0) instanceof DeadLetterJobEntityImpl);

            long count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().withException());
            assertTrue(count == 2);
        } finally {
            config.getDeadLetterJobDataManager().delete(entity1);
            config.getDeadLetterJobDataManager().delete(entity2);
            config.getDeadLetterJobDataManager().delete(entity3);
        }

    }

    @Test
    public void findJobByTenantIdQueryCriteria() {
        DeadLetterJobEntity entity1 = config.getDeadLetterJobDataManager().create();
        DeadLetterJobEntity entity2 = config.getDeadLetterJobDataManager().create();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setTenantId(id1);
            config.getDeadLetterJobDataManager().insert(entity1);

            config.getDeadLetterJobDataManager().insert(entity2);

            List<Job> jobs = config.getDeadLetterJobDataManager().findJobsByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().jobTenantId(id1), null);
            assertTrue(jobs.size() == 1);
            assertTrue(jobs.get(0) instanceof DeadLetterJobEntityImpl);

            long count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().jobTenantId(id1));
            assertTrue(count == 1);
            count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().jobTenantIdLike(id1.substring(3)));
            assertTrue(count == 1);
            count = config.getDeadLetterJobDataManager().findJobCountByQueryCriteria((DeadLetterJobQueryImpl) processEngine.getManagementService().createDeadLetterJobQuery().jobWithoutTenantId());
            assertTrue(count == 1);
        } finally {
            config.getDeadLetterJobDataManager().delete(entity1);
            config.getDeadLetterJobDataManager().delete(entity2);
        }
    }

}
