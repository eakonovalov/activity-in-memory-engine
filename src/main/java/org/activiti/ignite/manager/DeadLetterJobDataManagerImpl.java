package org.activiti.ignite.manager;

import org.activiti.engine.impl.DeadLetterJobQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.DeadLetterJobEntity;
import org.activiti.engine.impl.persistence.entity.DeadLetterJobEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.DeadLetterJobDataManager;
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
public class DeadLetterJobDataManagerImpl extends AbstractDataManager<DeadLetterJobEntity> implements DeadLetterJobDataManager {

    @Autowired
    @Qualifier("deadLetterJobEntityCache")
    private CacheConfiguration<String, DeadLetterJobEntity> config;

    public DeadLetterJobDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, DeadLetterJobEntity> getConfig() {
        return config;
    }

    @Override
    public List<DeadLetterJobEntity> findJobsByExecutionId(String executionId) {
        String query = "executionId = ?";

        List<Cache.Entry<String, DeadLetterJobEntityImpl>> list = getCache().query(new SqlQuery<String, DeadLetterJobEntityImpl>(DeadLetterJobEntityImpl.class, query).setArgs(executionId)).getAll();
        List<DeadLetterJobEntity> results = new ArrayList<>();
        for (Cache.Entry<String, DeadLetterJobEntityImpl> entry : list) {
            results.add(entry.getValue());
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
