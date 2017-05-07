package org.activiti.ignite.manager;

import org.activiti.engine.impl.persistence.entity.PropertyEntity;
import org.activiti.engine.impl.persistence.entity.PropertyEntityImpl;
import org.activiti.engine.impl.persistence.entity.SuspendedJobEntity;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class PropertyDataManager extends AbstractDataManager<PropertyEntity> implements org.activiti.engine.impl.persistence.entity.data.PropertyDataManager {

    @Autowired
    @Qualifier("propertyEntityCache")
    private CacheConfiguration<String, PropertyEntity> config;

    public PropertyDataManager(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, PropertyEntity> getConfig() {
        return config;
    }

    public List<PropertyEntity> findAll() {
        throw new UnsupportedOperationException();
    }

    public PropertyEntity create() {
        return new PropertyEntityImpl();
    }

}
