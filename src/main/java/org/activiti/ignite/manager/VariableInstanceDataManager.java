package org.activiti.ignite.manager;

import org.activiti.engine.impl.persistence.entity.TimerJobEntity;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntityImpl;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Joram Barrez
 */
public class VariableInstanceDataManager extends AbstractDataManager<VariableInstanceEntity> implements org.activiti.engine.impl.persistence.entity.data.VariableInstanceDataManager {

    @Autowired
    @Qualifier("variableInstanceEntityCache")
    private CacheConfiguration<String, VariableInstanceEntity> config;

    public VariableInstanceDataManager(IgniteProcessEngineConfiguration processEngineConfiguration) {
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
        String query = "taskId = ?";

        List<Cache.Entry<String, VariableInstanceEntityImpl>> list = getCache().query(new SqlQuery<String, VariableInstanceEntityImpl>(VariableInstanceEntityImpl.class, query).setArgs(taskId)).getAll();
        List<VariableInstanceEntity> results = new ArrayList<>();
        for (Cache.Entry<String, VariableInstanceEntityImpl> entry : list) {
            results.add(entry.getValue());
        }

        return results;
    }

    public List<VariableInstanceEntity> findVariableInstancesByTaskIds(Set<String> taskIds) {
        throw new UnsupportedOperationException();
    }

    public List<VariableInstanceEntity> findVariableInstancesByExecutionId(String executionId) {
        String query = "executionId = ?";

        List<Cache.Entry<String, VariableInstanceEntityImpl>> list = getCache().query(new SqlQuery<String, VariableInstanceEntityImpl>(VariableInstanceEntityImpl.class, query).setArgs(executionId)).getAll();
        List<VariableInstanceEntity> results = new ArrayList<>();
        for (Cache.Entry<String, VariableInstanceEntityImpl> entry : list) {
            results.add(entry.getValue());
        }

        return results;
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
