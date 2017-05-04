package org.activiti.inmemory.manager;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.HistoricProcessInstanceQueryImpl;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.ignite.entity.HistoricProcessInstanceEntityImpl;

import java.util.List;
import java.util.Map;

/**
 * @author Joram Barrez
 */
public class HistoricProcessInstanceDataManager extends AbstractDataManager<HistoricProcessInstanceEntity> implements org.activiti.engine.impl.persistence.entity.data.HistoricProcessInstanceDataManager {

    public HistoricProcessInstanceDataManager(ProcessEngineConfigurationImpl processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    public HistoricProcessInstanceEntity create() {
        return new HistoricProcessInstanceEntityImpl();
    }

    public HistoricProcessInstanceEntity create(ExecutionEntity processInstanceExecutionEntity) {
        return new HistoricProcessInstanceEntityImpl();
    }

    public List<String> findHistoricProcessInstanceIdsByProcessDefinitionId(String processDefinitionId) {
        throw new UnsupportedOperationException();
    }

    public List<HistoricProcessInstanceEntity> findHistoricProcessInstancesBySuperProcessInstanceId(String superProcessInstanceId) {
        throw new UnsupportedOperationException();
    }

    public long findHistoricProcessInstanceCountByQueryCriteria(HistoricProcessInstanceQueryImpl historicProcessInstanceQuery) {
        long count = 0;
        for (HistoricProcessInstanceEntity historicProcessInstanceEntity : entities.values()) {

            boolean addHistoricProcessInstance = true;

            if (historicProcessInstanceQuery.getProcessInstanceId() != null
                    && !historicProcessInstanceQuery.getProcessInstanceId().equals(historicProcessInstanceEntity.getProcessInstanceId())) {
                addHistoricProcessInstance = false;
            }

            if (historicProcessInstanceQuery.isFinished() &&
                    historicProcessInstanceEntity.getEndTime() == null) {
                addHistoricProcessInstance = false;
            }

            if (historicProcessInstanceQuery.isUnfinished() &&
                    historicProcessInstanceEntity.getEndTime() != null) {
                addHistoricProcessInstance = false;
            }

            if (addHistoricProcessInstance) {
                count++;
            }

        }
        return count;
    }

    public List<HistoricProcessInstance> findHistoricProcessInstancesByQueryCriteria(HistoricProcessInstanceQueryImpl historicProcessInstanceQuery) {
        throw new UnsupportedOperationException();
    }

    public List<HistoricProcessInstance> findHistoricProcessInstancesAndVariablesByQueryCriteria(HistoricProcessInstanceQueryImpl historicProcessInstanceQuery) {
        throw new UnsupportedOperationException();
    }

    public List<HistoricProcessInstance> findHistoricProcessInstancesByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
        throw new UnsupportedOperationException();
    }

    public long findHistoricProcessInstanceCountByNativeQuery(Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

}
