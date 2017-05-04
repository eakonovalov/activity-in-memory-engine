package org.activiti.inmemory.manager;

import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.PropertyEntity;
import org.activiti.ignite.entity.PropertyEntityImpl;

import java.util.List;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class PropertyDataManager extends AbstractDataManager<PropertyEntity> implements org.activiti.engine.impl.persistence.entity.data.PropertyDataManager {

    public PropertyDataManager(ProcessEngineConfigurationImpl processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    public List<PropertyEntity> findAll() {
        throw new UnsupportedOperationException();
    }

    public PropertyEntity create() {
        return new PropertyEntityImpl();
    }

}
