package org.activiti.ignite.manager;

import org.activiti.engine.impl.persistence.entity.PropertyEntity;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.activiti.ignite.entity.PropertyEntityImpl;
import org.apache.ignite.configuration.CacheConfiguration;

import java.util.List;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class PropertyDataManager extends AbstractDataManager<PropertyEntity> implements org.activiti.engine.impl.persistence.entity.data.PropertyDataManager {

    public PropertyDataManager(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
        CacheConfiguration<String, PropertyEntity> ccfg = new CacheConfiguration<>(this.getClass().getName());
        ccfg.setIndexedTypes(String.class, PropertyEntityImpl.class);
        cache = processEngineConfiguration.getIgnite().getOrCreateCache(ccfg);
    }

    public List<PropertyEntity> findAll() {
        throw new UnsupportedOperationException();
    }

    public PropertyEntity create() {
        return new PropertyEntityImpl();
    }

}
