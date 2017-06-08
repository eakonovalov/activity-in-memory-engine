package org.activiti.ignite.manager;

import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.VariableInstanceDataManager;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class VariableInstanceDataManagerImpl extends AbstractDataManager<VariableInstanceEntity, VariableInstanceEntityImpl> implements VariableInstanceDataManager {

    @Autowired
    @Qualifier("variableInstanceEntityCache")
    private CacheConfiguration<String, VariableInstanceEntity> config;

    public VariableInstanceDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, VariableInstanceEntity> getConfig() {
        return config;
    }

    public VariableInstanceEntity create() {
        return new VariableInstanceEntityImpl();
    }

    public List<VariableInstanceEntity> findVariableInstancesByTaskId(String taskId) {
        return findList(VariableInstanceEntityImpl.class, "taskId = ?", taskId);
    }

    public List<VariableInstanceEntity> findVariableInstancesByTaskIds(Set<String> taskIds) {
        throw new UnsupportedOperationException();
    }

    public List<VariableInstanceEntity> findVariableInstancesByExecutionId(String executionId) {
        return findList(VariableInstanceEntityImpl.class, "executionId = ?", executionId);
    }

    public List<VariableInstanceEntity> findVariableInstancesByExecutionIds(Set<String> executionIds) {
        throw new UnsupportedOperationException();
    }

    public VariableInstanceEntity findVariableInstanceByExecutionAndName(String executionId, String variableName) {
        throw new UnsupportedOperationException();
    }

    public List<VariableInstanceEntity> findVariableInstancesByExecutionAndNames(String executionId, Collection<String> names) {
        throw new UnsupportedOperationException();
    }

    public VariableInstanceEntity findVariableInstanceByTaskAndName(String taskId, String variableName) {
        throw new UnsupportedOperationException();
    }

    public List<VariableInstanceEntity> findVariableInstancesByTaskAndNames(String taskId, Collection<String> names) {
        throw new UnsupportedOperationException();
    }

}
