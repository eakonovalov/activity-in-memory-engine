package org.activiti.inmemory.manager;

import org.activiti.engine.impl.DeadLetterJobQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.DeadLetterJobEntity;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.impl.persistence.entity.DeadLetterJobEntityImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class DeadLetterJobEntityManager extends AbstractDataManager<DeadLetterJobEntity> implements org.activiti.engine.impl.persistence.entity.DeadLetterJobEntityManager {

    public DeadLetterJobEntityManager(ProcessEngineConfigurationImpl processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    public List<DeadLetterJobEntity> findJobsByExecutionId(String executionId) {
        List<DeadLetterJobEntity> results = new ArrayList<DeadLetterJobEntity>();
        for (DeadLetterJobEntity deadLetterJobEntity : entities.values()) {
            if (deadLetterJobEntity.getExecutionId() != null && deadLetterJobEntity.getExecutionId().equals(executionId)) {
                results.add(deadLetterJobEntity);
            }
        }
        return results;
    }

    @Override
    public List<Job> findJobsByQueryCriteria(DeadLetterJobQueryImpl jobQuery, Page page) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findJobCountByQueryCriteria(DeadLetterJobQueryImpl jobQuery) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateJobTenantIdForDeployment(String deploymentId, String newTenantId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DeadLetterJobEntity create() {
        return new DeadLetterJobEntityImpl();
    }

}
