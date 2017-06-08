package org.activiti.ignite.manager;

import org.activiti.engine.impl.persistence.entity.ProcessDefinitionInfoEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionInfoEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.ProcessDefinitionInfoDataManager;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class ProcessDefinitionInfoDataManagerImpl extends AbstractDataManager<ProcessDefinitionInfoEntity, ProcessDefinitionInfoEntityImpl> implements ProcessDefinitionInfoDataManager {

    @Autowired
    @Qualifier("processDefinitionInfoEntityCache")
    private CacheConfiguration<String, ProcessDefinitionInfoEntity> config;

    public ProcessDefinitionInfoDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, ProcessDefinitionInfoEntity> getConfig() {
        return config;
    }

    @Override
    public ProcessDefinitionInfoEntity findProcessDefinitionInfoByProcessDefinitionId(String processDefinitionId) {
        return findOne(ProcessDefinitionInfoEntityImpl.class, "processDefinitionId = ?", processDefinitionId);
    }

    @Override
    public ProcessDefinitionInfoEntity create() {
        return new ProcessDefinitionInfoEntityImpl();
    }

}
