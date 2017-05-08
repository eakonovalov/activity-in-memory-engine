package org.activiti.ignite.manager;

import org.activiti.engine.impl.persistence.entity.ResourceEntity;
import org.activiti.engine.impl.persistence.entity.ResourceEntityImpl;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class ResourceDataManagerImpl extends AbstractDataManager<ResourceEntity> implements org.activiti.engine.impl.persistence.entity.data.ResourceDataManager {

    @Autowired
    @Qualifier("resourceEntityCache")
    private CacheConfiguration<String, ResourceEntity> config;

    public ResourceDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, ResourceEntity> getConfig() {
        return config;
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
