package org.activiti.ignite.manager;

import org.activiti.engine.delegate.VariableScope;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.TimerJobQueryImpl;
import org.activiti.engine.impl.persistence.entity.JobEntity;
import org.activiti.engine.impl.persistence.entity.TimerJobEntity;
import org.activiti.engine.runtime.Job;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.activiti.ignite.entity.TimerJobEntityImpl;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.CacheConfiguration;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class TimerJobEntityManager extends AbstractDataManager<TimerJobEntity> implements org.activiti.engine.impl.persistence.entity.TimerJobEntityManager {

    public TimerJobEntityManager(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
        CacheConfiguration<String, TimerJobEntity> ccfg = new CacheConfiguration<>(this.getClass().getName());
        ccfg.setIndexedTypes(String.class, TimerJobEntityImpl.class);
        cache = processEngineConfiguration.getIgnite().getOrCreateCache(ccfg);
    }

    @Override
    public boolean insertTimerJobEntity(TimerJobEntity timerJobEntity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<TimerJobEntity> findTimerJobsToExecute(Page page) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<TimerJobEntity> findJobsByTypeAndProcessDefinitionId(String type, String processDefinitionId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<TimerJobEntity> findJobsByTypeAndProcessDefinitionKeyAndTenantId(String type, String processDefinitionKey, String tenantId) {
        String query = "processDefinitionId = ? and jobType = ? and tenantId = ?";

        List<Cache.Entry<String, TimerJobEntityImpl>> list = cache.query(new SqlQuery<String, TimerJobEntityImpl>(TimerJobEntityImpl.class, query).setArgs(type, processDefinitionKey, tenantId)).getAll();
        List<TimerJobEntity> results = new ArrayList<>();
        for (Cache.Entry<String, TimerJobEntityImpl> entry : list) {
            results.add(entry.getValue());
        }

        return results;
    }

    @Override
    public List<TimerJobEntity> findJobsByTypeAndProcessDefinitionKeyNoTenantId(String type, String processDefinitionKey) {
        String query = "processDefinitionId = ? and jobType = ? and tenantId = NULL";

        List<Cache.Entry<String, TimerJobEntityImpl>> list = cache.query(new SqlQuery<String, TimerJobEntityImpl>(TimerJobEntityImpl.class, query).setArgs(type, processDefinitionKey)).getAll();
        List<TimerJobEntity> results = new ArrayList<>();
        for (Cache.Entry<String, TimerJobEntityImpl> entry : list) {
            results.add(entry.getValue());
        }

        return results;
    }

    @Override
    public List<TimerJobEntity> findJobsByExecutionId(String executionId) {
        String query = "executionId = ?";

        List<Cache.Entry<String, TimerJobEntityImpl>> list = cache.query(new SqlQuery<String, TimerJobEntityImpl>(TimerJobEntityImpl.class, query).setArgs(executionId)).getAll();
        List<TimerJobEntity> results = new ArrayList<>();
        for (Cache.Entry<String, TimerJobEntityImpl> entry : list) {
            results.add(entry.getValue());
        }

        return results;
    }

    @Override
    public List<TimerJobEntity> findJobsByProcessInstanceId(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Job> findJobsByQueryCriteria(TimerJobQueryImpl jobQuery, Page page) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findJobCountByQueryCriteria(TimerJobQueryImpl jobQuery) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TimerJobEntity createAndCalculateNextTimer(JobEntity timerEntity, VariableScope variableScope) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateJobTenantIdForDeployment(String deploymentId, String newTenantId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TimerJobEntity create() {
        return new TimerJobEntityImpl();
    }

}
