package org.activiti.ignite.manager;

import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.TimerJobQueryImpl;
import org.activiti.engine.impl.persistence.entity.TimerJobEntity;
import org.activiti.engine.impl.persistence.entity.TimerJobEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.TimerJobDataManager;
import org.activiti.engine.runtime.Job;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class TimerJobDataManagerImpl extends AbstractDataManager<TimerJobEntity, TimerJobEntityImpl> implements TimerJobDataManager {

    @Autowired
    @Qualifier("timerJobEntityCache")
    private CacheConfiguration<String, TimerJobEntity> config;

    public TimerJobDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, TimerJobEntity> getConfig() {
        return config;
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
        return findList(TimerJobEntityImpl.class, "processDefinitionId = ? and jobType = ? and tenantId = ?", type, processDefinitionKey, tenantId);
    }

    @Override
    public List<TimerJobEntity> findJobsByTypeAndProcessDefinitionKeyNoTenantId(String type, String processDefinitionKey) {
        return findList(TimerJobEntityImpl.class, "processDefinitionId = ? and jobType = ? and tenantId = NULL", type, processDefinitionKey);
    }

    @Override
    public List<TimerJobEntity> findJobsByExecutionId(String executionId) {
        return findList(TimerJobEntityImpl.class, "executionId = ?", executionId);
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
    public void updateJobTenantIdForDeployment(String deploymentId, String newTenantId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TimerJobEntity create() {
        return new TimerJobEntityImpl();
    }

}
