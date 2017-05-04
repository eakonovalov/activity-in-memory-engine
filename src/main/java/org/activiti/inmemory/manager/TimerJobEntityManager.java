package org.activiti.inmemory.manager;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.delegate.VariableScope;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.TimerJobQueryImpl;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.JobEntity;
import org.activiti.engine.impl.persistence.entity.TimerJobEntity;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.impl.persistence.entity.TimerJobEntityImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class TimerJobEntityManager extends AbstractDataManager<TimerJobEntity> implements org.activiti.engine.impl.persistence.entity.TimerJobEntityManager {

    public TimerJobEntityManager(ProcessEngineConfigurationImpl processEngineConfiguration) {
        super(processEngineConfiguration);
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
        List<TimerJobEntity> results = new ArrayList<>();
        for (TimerJobEntity timerJobEntity : entities.values()) {
            if (timerJobEntity.getJobType() != null && timerJobEntity.getJobType().equals(type) &&
                    timerJobEntity.getProcessDefinitionId() != null && timerJobEntity.getProcessDefinitionId().equals(processDefinitionKey) &&
                    timerJobEntity.getTenantId() != null && timerJobEntity.getTenantId().equals(tenantId)) {

                results.add(timerJobEntity);
            }
        }
        return results;
    }

    @Override
    public List<TimerJobEntity> findJobsByTypeAndProcessDefinitionKeyNoTenantId(String type, String processDefinitionKey) {
        List<TimerJobEntity> results = new ArrayList<>();
        for (TimerJobEntity timerJobEntity : entities.values()) {
            if (timerJobEntity.getJobType() != null && timerJobEntity.getJobType().equals(type) &&
                    timerJobEntity.getProcessDefinitionId() != null && timerJobEntity.getProcessDefinitionId().equals(processDefinitionKey) &&
                    timerJobEntity.getTenantId() != null && timerJobEntity.getTenantId().equals(ProcessEngineConfiguration.NO_TENANT_ID)) {

                results.add(timerJobEntity);
            }
        }
        return results;
    }

    @Override
    public List<TimerJobEntity> findJobsByExecutionId(String executionId) {
        List<TimerJobEntity> results = new ArrayList<>();
        for (TimerJobEntity timerJobEntity : entities.values()) {
            if (timerJobEntity.getExecutionId() != null && timerJobEntity.getExecutionId().equals(executionId)) {
                results.add(timerJobEntity);
            }
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
