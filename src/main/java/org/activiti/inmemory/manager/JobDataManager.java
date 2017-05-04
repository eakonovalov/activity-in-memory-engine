package org.activiti.inmemory.manager;

import org.activiti.engine.impl.JobQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.JobEntity;
import org.activiti.engine.runtime.Job;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joram Barrez
 */
public class JobDataManager extends AbstractDataManager<JobEntity> implements org.activiti.engine.impl.persistence.entity.data.JobDataManager {

    public JobDataManager(ProcessEngineConfigurationImpl processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    public JobEntity create() {
        throw new UnsupportedOperationException();
    }

    public List<JobEntity> findJobsToExecute(Page page) {
        throw new UnsupportedOperationException();
    }

    public List<JobEntity> findJobsByExecutionId(String executionId) {
        List<JobEntity> results = new ArrayList<JobEntity>();
        for (JobEntity jobEntity : entities.values()) {
            if (jobEntity.getExecutionId() != null && jobEntity.getExecutionId().equals(executionId)) {
                results.add(jobEntity);
            }
        }
        return results;
    }

    public List<JobEntity> findJobsByProcessInstanceId(String processInstanceId) {
        throw new UnsupportedOperationException();
    }

    public List<JobEntity> findExpiredJobs(Page page) {
        throw new UnsupportedOperationException();
    }

    public List<Job> findJobsByQueryCriteria(JobQueryImpl jobQuery, Page page) {
        throw new UnsupportedOperationException();
    }

    public long findJobCountByQueryCriteria(JobQueryImpl jobQuery) {
        throw new UnsupportedOperationException();
    }

    public void updateJobTenantIdForDeployment(String deploymentId, String newTenantId) {
        throw new UnsupportedOperationException();
    }

    public void resetExpiredJob(String jobId) {
        throw new UnsupportedOperationException();
    }

}
