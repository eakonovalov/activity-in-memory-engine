package org.activiti.ignite.manager;

import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.SuspendedJobQueryImpl;
import org.activiti.engine.impl.persistence.entity.SuspendedJobEntity;
import org.activiti.engine.impl.persistence.entity.SuspendedJobEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.SuspendedJobDataManager;
import org.activiti.engine.runtime.Job;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class SuspendedJobDataManagerImpl extends AbstractDataManager<SuspendedJobEntity, SuspendedJobEntityImpl> implements SuspendedJobDataManager {

    @Autowired
    @Qualifier("suspendedJobEntityCache")
    private CacheConfiguration<String, SuspendedJobEntity> config;

    public SuspendedJobDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, SuspendedJobEntity> getConfig() {
        return config;
    }

    @Override
    public List<SuspendedJobEntity> findJobsByExecutionId(String executionId) {
        return findList(SuspendedJobEntityImpl.class, "executionId = ?", executionId);
    }

    @Override
    public List<SuspendedJobEntity> findJobsByProcessInstanceId(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Job> findJobsByQueryCriteria(SuspendedJobQueryImpl jobQuery, Page page) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findJobCountByQueryCriteria(SuspendedJobQueryImpl jobQuery) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateJobTenantIdForDeployment(String deploymentId, String newTenantId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SuspendedJobEntity create() {
        return new SuspendedJobEntityImpl();
    }

}
