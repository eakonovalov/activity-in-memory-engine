package org.activiti.ignite.manager;

import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.SuspendedJobQueryImpl;
import org.activiti.engine.impl.persistence.entity.SuspendedJobEntity;
import org.activiti.engine.runtime.Job;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.activiti.ignite.entity.SuspendedJobEntityImpl;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.CacheConfiguration;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class SuspendedJobEntityManager extends AbstractDataManager<SuspendedJobEntity> implements org.activiti.engine.impl.persistence.entity.SuspendedJobEntityManager {

    public SuspendedJobEntityManager(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
        CacheConfiguration<String, SuspendedJobEntity> ccfg = new CacheConfiguration<>(this.getClass().getName());
        ccfg.setIndexedTypes(String.class, SuspendedJobEntityImpl.class);
        cache = processEngineConfiguration.getIgnite().getOrCreateCache(ccfg);
    }

    @Override
    public List<SuspendedJobEntity> findJobsByExecutionId(String executionId) {
        String query = "executionId = ?";

        List<Cache.Entry<String, SuspendedJobEntityImpl>> list = cache.query(new SqlQuery<String, SuspendedJobEntityImpl>(SuspendedJobEntityImpl.class, query).setArgs(executionId)).getAll();
        List<SuspendedJobEntity> results = new ArrayList<>();
        for (Cache.Entry<String, SuspendedJobEntityImpl> entry : list) {
            results.add(entry.getValue());
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
