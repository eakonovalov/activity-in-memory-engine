package org.activiti.ignite.manager;

import org.activiti.engine.impl.persistence.entity.HistoricIdentityLinkEntity;
import org.activiti.engine.impl.persistence.entity.HistoricIdentityLinkEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.HistoricIdentityLinkDataManager;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by ekonovalov on 07.06.2017.
 */
public class HistoricIdentityLinkDataManagerImpl extends AbstractDataManager<HistoricIdentityLinkEntity, HistoricIdentityLinkEntityImpl> implements HistoricIdentityLinkDataManager {

    @Autowired
    @Qualifier("historicIdentityLinkEntityCache")
    private CacheConfiguration<String, HistoricIdentityLinkEntity> config;

    public HistoricIdentityLinkDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, HistoricIdentityLinkEntity> getConfig() {
        return config;
    }


    @Override
    public List<HistoricIdentityLinkEntity> findHistoricIdentityLinksByTaskId(String taskId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<HistoricIdentityLinkEntity> findHistoricIdentityLinksByProcessInstanceId(String processInstanceId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public HistoricIdentityLinkEntity create() {
        return new HistoricIdentityLinkEntityImpl();
    }

}
