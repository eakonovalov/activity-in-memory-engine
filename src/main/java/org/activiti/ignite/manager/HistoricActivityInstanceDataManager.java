package org.activiti.ignite.manager;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.HistoricActivityInstanceQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.HistoricActivityInstanceEntity;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.activiti.ignite.entity.HistoricActivityInstanceEntityImpl;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.CacheConfiguration;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Joram Barrez
 */
public class HistoricActivityInstanceDataManager extends AbstractDataManager<HistoricActivityInstanceEntity> implements org.activiti.engine.impl.persistence.entity.data.HistoricActivityInstanceDataManager {

    public HistoricActivityInstanceDataManager(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
        CacheConfiguration<String, HistoricActivityInstanceEntity> ccfg = new CacheConfiguration<>(this.getClass().getName());
        ccfg.setIndexedTypes(String.class, HistoricActivityInstanceEntityImpl.class);
        cache = processEngineConfiguration.getIgnite().getOrCreateCache(ccfg);
    }

    public List<HistoricActivityInstanceEntity> findUnfinishedHistoricActivityInstancesByExecutionAndActivityId(String executionId, String activityId) {
        String query = "executionId = ? and activityId = ? and endTime = NULL";

        List<Cache.Entry<String, HistoricActivityInstanceEntityImpl>> list = cache.query(new SqlQuery<String, HistoricActivityInstanceEntityImpl>(HistoricActivityInstanceEntityImpl.class, query).setArgs(executionId, activityId)).getAll();
        List<HistoricActivityInstanceEntity> results = new ArrayList<>();
        for (Cache.Entry<String, HistoricActivityInstanceEntityImpl> entry : list) {
            results.add(entry.getValue());
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

    public HistoricActivityInstanceEntity create() {
        return new HistoricActivityInstanceEntityImpl();
    }

}
