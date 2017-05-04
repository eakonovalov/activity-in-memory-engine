package org.activiti.ignite.manager;

import org.activiti.engine.impl.persistence.entity.ResourceEntity;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.activiti.ignite.entity.ResourceEntityImpl;
import org.apache.ignite.configuration.CacheConfiguration;

import java.util.List;

/**
 * @author Joram Barrez
 */
public class ResourceDataManager extends AbstractDataManager<ResourceEntity> implements org.activiti.engine.impl.persistence.entity.data.ResourceDataManager {

    public ResourceDataManager(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
        CacheConfiguration<String, ResourceEntity> ccfg = new CacheConfiguration<>(this.getClass().getName());
        ccfg.setIndexedTypes(String.class, ResourceEntityImpl.class);
        cache = processEngineConfiguration.getIgnite().getOrCreateCache(ccfg);
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
