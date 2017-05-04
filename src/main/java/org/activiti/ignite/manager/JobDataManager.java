package org.activiti.ignite.manager;

import org.activiti.engine.impl.JobQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.JobEntity;
import org.activiti.engine.runtime.Job;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.activiti.ignite.entity.JobEntityImpl;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.CacheConfiguration;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joram Barrez
 */
public class JobDataManager extends AbstractDataManager<JobEntity> implements org.activiti.engine.impl.persistence.entity.data.JobDataManager {

    public JobDataManager(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
        CacheConfiguration<String, JobEntity> ccfg = new CacheConfiguration<>(this.getClass().getName());
        ccfg.setIndexedTypes(String.class, JobEntityImpl.class);
        cache = processEngineConfiguration.getIgnite().getOrCreateCache(ccfg);
    }

    public JobEntity create() {
        throw new UnsupportedOperationException();
    }

    public List<JobEntity> findJobsToExecute(Page page) {
        throw new UnsupportedOperationException();
    }

    public List<JobEntity> findJobsByExecutionId(String executionId) {
        String query = "executionId = ?";

        List<Cache.Entry<String, JobEntityImpl>> list = cache.query(new SqlQuery<String, JobEntityImpl>(JobEntityImpl.class, query).setArgs(executionId)).getAll();
        List<JobEntity> results = new ArrayList<>();
        for (Cache.Entry<String, JobEntityImpl> entry : list) {
            results.add(entry.getValue());
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
