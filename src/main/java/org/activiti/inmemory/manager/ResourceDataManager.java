package org.activiti.inmemory.manager;

import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.ResourceEntity;
import org.activiti.engine.impl.persistence.entity.ResourceEntityImpl;

import java.util.List;

/**
 * @author Joram Barrez
 */
public class ResourceDataManager extends AbstractDataManager<ResourceEntity> implements org.activiti.engine.impl.persistence.entity.data.ResourceDataManager {

    public ResourceDataManager(ProcessEngineConfigurationImpl processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    public ResourceEntity create() {
        return new ResourceEntityImpl();
    }

    public void deleteResourcesByDeploymentId(String deploymentId) {
        throw new UnsupportedOperationException();
    }

    public ResourceEntity findResourceByDeploymentIdAndResourceName(String deploymentId, String resourceName) {
        throw new UnsupportedOperationException();
    }

    public List<ResourceEntity> findResourcesByDeploymentId(String deploymentId) {
        throw new UnsupportedOperationException();
    }

}
