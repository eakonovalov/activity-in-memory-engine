package org.activiti.ignite.manager;

import org.activiti.engine.impl.DeploymentQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.DeploymentEntity;
import org.activiti.engine.impl.persistence.entity.DeploymentEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.DeploymentDataManager;
import org.activiti.engine.repository.Deployment;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class DeploymentDataManagerImpl extends AbstractDataManager<DeploymentEntity, DeploymentEntityImpl> implements DeploymentDataManager {

    @Autowired
    @Qualifier("deploymentEntityCache")
    private CacheConfiguration<String, DeploymentEntity> config;

    public DeploymentDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, DeploymentEntity> getConfig() {
        return config;
    }

    public DeploymentEntity findLatestDeploymentByName(String deploymentName) {
        throw new UnsupportedOperationException();
    }

    public long findDeploymentCountByQueryCriteria(DeploymentQueryImpl deploymentQuery) {
        throw new UnsupportedOperationException();
    }

    public List<Deployment> findDeploymentsByQueryCriteria(DeploymentQueryImpl deploymentQuery, Page page) {
        throw new UnsupportedOperationException();
    }

    public List<String> getDeploymentResourceNames(String deploymentId) {
        throw new UnsupportedOperationException();
    }

    public List<Deployment> findDeploymentsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
        throw new UnsupportedOperationException();
    }

    public long findDeploymentCountByNativeQuery(Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

    public DeploymentEntity create() {
        return new DeploymentEntityImpl();
    }

}
