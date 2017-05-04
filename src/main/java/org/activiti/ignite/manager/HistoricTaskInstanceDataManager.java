package org.activiti.ignite.manager;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.HistoricTaskInstanceQueryImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.activiti.ignite.entity.HistoricTaskInstanceEntityImpl;
import org.apache.ignite.configuration.CacheConfiguration;

import java.util.List;
import java.util.Map;

/**
 * @author Joram Barrez
 */
public class HistoricTaskInstanceDataManager extends AbstractDataManager<HistoricTaskInstanceEntity> implements org.activiti.engine.impl.persistence.entity.data.HistoricTaskInstanceDataManager {

    public HistoricTaskInstanceDataManager(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
        CacheConfiguration<String, HistoricTaskInstanceEntity> ccfg = new CacheConfiguration<>(this.getClass().getName());
        ccfg.setIndexedTypes(String.class, HistoricTaskInstanceEntityImpl.class);
        cache = processEngineConfiguration.getIgnite().getOrCreateCache(ccfg);
    }

    public HistoricTaskInstanceEntity create() {
        return new HistoricTaskInstanceEntityImpl();
    }

    public HistoricTaskInstanceEntity create(TaskEntity task, ExecutionEntity execution) {
        return new HistoricTaskInstanceEntityImpl(task, execution);
    }

    public List<HistoricTaskInstanceEntity> findHistoricTasksByParentTaskId(String parentTaskId) {
        throw new UnsupportedOperationException();
    }

    public List<HistoricTaskInstanceEntity> findHistoricTaskInstanceByProcessInstanceId(String processInstanceId) {
        throw new UnsupportedOperationException();
    }

    public long findHistoricTaskInstanceCountByQueryCriteria(HistoricTaskInstanceQueryImpl historicTaskInstanceQuery) {
        throw new UnsupportedOperationException();
    }

    public List<HistoricTaskInstance> findHistoricTaskInstancesByQueryCriteria(HistoricTaskInstanceQueryImpl historicTaskInstanceQuery) {
        throw new UnsupportedOperationException();
    }

    public List<HistoricTaskInstance> findHistoricTaskInstancesAndVariablesByQueryCriteria(HistoricTaskInstanceQueryImpl historicTaskInstanceQuery) {
        throw new UnsupportedOperationException();
    }

    public List<HistoricTaskInstance> findHistoricTaskInstancesByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
        throw new UnsupportedOperationException();
    }

    public long findHistoricTaskInstanceCountByNativeQuery(Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

}
