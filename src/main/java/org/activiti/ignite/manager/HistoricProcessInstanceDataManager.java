package org.activiti.ignite.manager;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.HistoricProcessInstanceQueryImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.activiti.ignite.entity.HistoricProcessInstanceEntityImpl;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.CacheConfiguration;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Joram Barrez
 */
public class HistoricProcessInstanceDataManager extends AbstractDataManager<HistoricProcessInstanceEntity> implements org.activiti.engine.impl.persistence.entity.data.HistoricProcessInstanceDataManager {

    public HistoricProcessInstanceDataManager(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
        CacheConfiguration<String, HistoricProcessInstanceEntity> ccfg = new CacheConfiguration<>(this.getClass().getName());
        ccfg.setIndexedTypes(String.class, HistoricProcessInstanceEntityImpl.class);
        cache = processEngineConfiguration.getIgnite().getOrCreateCache(ccfg);
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
        String queryString = "";
        List<Object> args = new ArrayList<>();
        if (historicProcessInstanceQuery.getProcessInstanceId() != null) {
            queryString += "processInstanceId = ?";
            args.add(historicProcessInstanceQuery.getProcessInstanceId());
        }
        if (historicProcessInstanceQuery.isFinished()) {
            queryString += queryString.length() == 0 ? "" : " or ";
            queryString += "endTime IS NOT NULL";
        }
        if (historicProcessInstanceQuery.isUnfinished()) {
            queryString += queryString.length() == 0 ? "" : " or ";
            queryString += "endTime IS NULL";
        }

        if (queryString.length() == 0) return cache.size();

        List<Cache.Entry<String, HistoricProcessInstanceEntityImpl>> list = cache.query(new SqlQuery<String, HistoricProcessInstanceEntityImpl>(HistoricProcessInstanceEntityImpl.class, queryString).setArgs(args.toArray())).getAll();

        return list.size();
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
