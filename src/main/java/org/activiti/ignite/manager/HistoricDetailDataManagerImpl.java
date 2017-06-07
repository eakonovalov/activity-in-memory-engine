package org.activiti.ignite.manager;

import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.impl.HistoricDetailQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.*;
import org.activiti.engine.impl.persistence.entity.data.HistoricDetailDataManager;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

/**
 * Created by ekonovalov on 07.06.2017.
 */
public class HistoricDetailDataManagerImpl extends AbstractDataManager<HistoricDetailEntity, HistoricDetailEntityImpl> implements HistoricDetailDataManager {

    @Autowired
    @Qualifier("historicDetailEntityCache")
    private CacheConfiguration<String, HistoricDetailEntity> config;

    public HistoricDetailDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, HistoricDetailEntity> getConfig() {
        return config;
    }


    @Override
    public HistoricDetailAssignmentEntity createHistoricDetailAssignment() {
        return new HistoricDetailAssignmentEntityImpl();
    }

    @Override
    public HistoricDetailTransitionInstanceEntity createHistoricDetailTransitionInstance() {
        return new HistoricDetailTransitionInstanceEntityImpl();
    }

    @Override
    public HistoricDetailVariableInstanceUpdateEntity createHistoricDetailVariableInstanceUpdate() {
        return new HistoricDetailVariableInstanceUpdateEntityImpl();
    }

    @Override
    public HistoricFormPropertyEntity createHistoricFormProperty() {
        return new HistoricFormPropertyEntityImpl();
    }

    @Override
    public List<HistoricDetailEntity> findHistoricDetailsByProcessInstanceId(String processInstanceId) {
        return null;
    }

    @Override
    public List<HistoricDetailEntity> findHistoricDetailsByTaskId(String taskId) {
        return null;
    }

    @Override
    public long findHistoricDetailCountByQueryCriteria(HistoricDetailQueryImpl historicVariableUpdateQuery) {
        return 0;
    }

    @Override
    public List<HistoricDetail> findHistoricDetailsByQueryCriteria(HistoricDetailQueryImpl historicVariableUpdateQuery, Page page) {
        return null;
    }

    @Override
    public List<HistoricDetail> findHistoricDetailsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
        return null;
    }

    @Override
    public long findHistoricDetailCountByNativeQuery(Map<String, Object> parameterMap) {
        return 0;
    }

    @Override
    public HistoricDetailEntity create() {
        throw new UnsupportedOperationException();
    }

}
