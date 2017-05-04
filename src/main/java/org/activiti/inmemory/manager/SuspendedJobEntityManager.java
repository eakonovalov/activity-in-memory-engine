package org.activiti.inmemory.manager;

import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.SuspendedJobQueryImpl;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.SuspendedJobEntity;
import org.activiti.engine.runtime.Job;
import org.activiti.ignite.entity.SuspendedJobEntityImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class SuspendedJobEntityManager extends AbstractDataManager<SuspendedJobEntity> implements org.activiti.engine.impl.persistence.entity.SuspendedJobEntityManager {

    public SuspendedJobEntityManager(ProcessEngineConfigurationImpl processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    public List<SuspendedJobEntity> findJobsByExecutionId(String executionId) {
        List<SuspendedJobEntity> results = new ArrayList<>();
        for (SuspendedJobEntity suspendedJobEntity : entities.values()) {
            if (suspendedJobEntity.getExecutionId() != null && suspendedJobEntity.getExecutionId().equals(executionId)) {
                results.add(suspendedJobEntity);
            }
        }
        return results;
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
