package org.activiti.ignite.manager;

import org.activiti.engine.impl.persistence.entity.PropertyEntity;
import org.activiti.engine.impl.persistence.entity.PropertyEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.PropertyDataManager;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class PropertyDataManagerImpl extends AbstractDataManager<PropertyEntity, PropertyEntityImpl> implements PropertyDataManager {

    @Autowired
    @Qualifier("propertyEntityCache")
    private CacheConfiguration<String, PropertyEntity> config;

    public PropertyDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, PropertyEntity> getConfig() {
        return config;
    }

    public PropertyEntity create() {
        return new PropertyEntityImpl();
    }

}
