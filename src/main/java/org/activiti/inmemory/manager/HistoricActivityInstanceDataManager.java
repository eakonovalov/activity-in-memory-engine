package org.activiti.inmemory.manager;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.HistoricActivityInstanceQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.HistoricActivityInstanceEntity;
import org.activiti.ignite.entity.HistoricActivityInstanceEntityImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Joram Barrez
 */
public class HistoricActivityInstanceDataManager extends AbstractDataManager<HistoricActivityInstanceEntity> implements org.activiti.engine.impl.persistence.entity.data.HistoricActivityInstanceDataManager {

    public HistoricActivityInstanceDataManager(ProcessEngineConfigurationImpl processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    public HistoricActivityInstanceEntity create() {
        return new HistoricActivityInstanceEntityImpl();
    }

    public List<HistoricActivityInstanceEntity> findUnfinishedHistoricActivityInstancesByExecutionAndActivityId(String executionId, String activityId) {
        List<HistoricActivityInstanceEntity> results = new ArrayList<HistoricActivityInstanceEntity>();
        for (HistoricActivityInstanceEntity historicActivityInstanceEntity : entities.values()) {
            if (historicActivityInstanceEntity.getEndTime() == null
                    && historicActivityInstanceEntity.getExecutionId() != null && historicActivityInstanceEntity.getExecutionId().equals(executionId)
                    && historicActivityInstanceEntity.getActivityId() != null && historicActivityInstanceEntity.getActivityId().equals(activityId)) {
                results.add(historicActivityInstanceEntity);
            }
        }
        return results;
    }

    public List<HistoricActivityInstanceEntity> findUnfinishedHistoricActivityInstancesByProcessInstanceId(String processInstanceId) {
        throw new UnsupportedOperationException();
    }

    public void deleteHistoricActivityInstancesByProcessInstanceId(String historicProcessInstanceId) {
        throw new UnsupportedOperationException();
    }

    public long findHistoricActivityInstanceCountByQueryCriteria(HistoricActivityInstanceQueryImpl historicActivityInstanceQuery) {
        throw new UnsupportedOperationException();
    }

    public List<HistoricActivityInstance> findHistoricActivityInstancesByQueryCriteria(HistoricActivityInstanceQueryImpl historicActivityInstanceQuery, Page page) {
        throw new UnsupportedOperationException();
    }

    public List<HistoricActivityInstance> findHistoricActivityInstancesByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
        throw new UnsupportedOperationException();
    }

    public long findHistoricActivityInstanceCountByNativeQuery(Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

}
