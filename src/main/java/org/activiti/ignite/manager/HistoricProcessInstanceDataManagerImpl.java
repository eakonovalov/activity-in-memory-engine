package org.activiti.ignite.manager;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.HistoricProcessInstanceQueryImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.HistoricProcessInstanceDataManager;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class HistoricProcessInstanceDataManagerImpl extends AbstractDataManager<HistoricProcessInstanceEntity> implements HistoricProcessInstanceDataManager {

    @Autowired
    @Qualifier("historicProcessInstanceEntityCache")
    private CacheConfiguration<String, HistoricProcessInstanceEntity> config;

    public HistoricProcessInstanceDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, HistoricProcessInstanceEntity> getConfig() {
        return config;
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

        if (queryString.length() == 0) return getCache().size();

        List<Cache.Entry<String, HistoricProcessInstanceEntityImpl>> list = getCache().query(new SqlQuery<String, HistoricProcessInstanceEntityImpl>(HistoricProcessInstanceEntityImpl.class, queryString).setArgs(args.toArray())).getAll();

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
