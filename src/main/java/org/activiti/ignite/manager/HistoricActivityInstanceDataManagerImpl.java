package org.activiti.ignite.manager;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.HistoricActivityInstanceQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.HistoricActivityInstanceEntity;
import org.activiti.engine.impl.persistence.entity.HistoricActivityInstanceEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.HistoricActivityInstanceDataManager;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class HistoricActivityInstanceDataManagerImpl extends AbstractDataManager<HistoricActivityInstanceEntity, HistoricActivityInstanceEntityImpl> implements HistoricActivityInstanceDataManager {

    @Autowired
    @Qualifier("historicActivityInstanceEntityCache")
    private CacheConfiguration<String, HistoricActivityInstanceEntity> config;

    public HistoricActivityInstanceDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, HistoricActivityInstanceEntity> getConfig() {
        return config;
    }

    public List<HistoricActivityInstanceEntity> findUnfinishedHistoricActivityInstancesByExecutionAndActivityId(String executionId, String activityId) {
        return findList(HistoricActivityInstanceEntityImpl.class, "executionId = ? and activityId = ? and endTime = NULL", executionId, activityId);
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

    public HistoricActivityInstanceEntity create() {
        return new HistoricActivityInstanceEntityImpl();
    }

}
