package org.activiti.inmemory.manager;

import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionInfoEntity;
import org.activiti.ignite.entity.ProcessDefinitionInfoEntityImpl;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class ProcessDefinitionInfoDataManager extends AbstractDataManager<ProcessDefinitionInfoEntity> implements org.activiti.engine.impl.persistence.entity.data.ProcessDefinitionInfoDataManager {

    public ProcessDefinitionInfoDataManager(ProcessEngineConfigurationImpl processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    public ProcessDefinitionInfoEntity findProcessDefinitionInfoByProcessDefinitionId(String processDefinitionId) {
        for (ProcessDefinitionInfoEntity processDefinitionInfoEntity : entities.values()) {
            if (processDefinitionInfoEntity.getProcessDefinitionId() != null && processDefinitionInfoEntity.getProcessDefinitionId().equals(processDefinitionId)) {
                return processDefinitionInfoEntity;
            }
        }

        return null;
    }

    @Override
    public ProcessDefinitionInfoEntity create() {
        return new ProcessDefinitionInfoEntityImpl();
    }

}
