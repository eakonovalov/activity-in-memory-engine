package org.activiti.ignite.manager;

import org.activiti.engine.impl.JobQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.JobEntity;
import org.activiti.engine.impl.persistence.entity.JobEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.JobDataManager;
import org.activiti.engine.runtime.Job;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class JobDataManagerImpl extends AbstractDataManager<JobEntity, JobEntityImpl> implements JobDataManager {

    @Autowired
    @Qualifier("jobEntityCache")
    private CacheConfiguration<String, JobEntity> config;

    public JobDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, JobEntity> getConfig() {
        return config;
    }

    public JobEntity create() {
        throw new UnsupportedOperationException();
    }

    public List<JobEntity> findJobsToExecute(Page page) {
        throw new UnsupportedOperationException();
    }

    public List<JobEntity> findJobsByExecutionId(String executionId) {
        return findList(JobEntityImpl.class, "executionId = ?", executionId);
    }

    @Override
    public List<JobEntity> findJobsByProcessDefinitionId(String processDefinitionId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<JobEntity> findJobsByTypeAndProcessDefinitionId(String jobTypeTimer, String id) {
        throw new UnsupportedOperationException();
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
