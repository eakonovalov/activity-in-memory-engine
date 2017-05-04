package org.activiti.inmemory.manager;

import org.activiti.engine.impl.DeploymentQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.DeploymentEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.impl.persistence.entity.DeploymentEntityImpl;

import java.util.List;
import java.util.Map;

/**
 * @author Joram Barrez
 */
public class DeploymentDataManager extends AbstractDataManager<DeploymentEntity> implements org.activiti.engine.impl.persistence.entity.data.DeploymentDataManager {

    public DeploymentDataManager(ProcessEngineConfigurationImpl processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    public DeploymentEntity create() {
        return new DeploymentEntityImpl();
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

}
