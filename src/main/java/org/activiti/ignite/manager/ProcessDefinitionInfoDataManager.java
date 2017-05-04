package org.activiti.ignite.manager;

import org.activiti.engine.impl.persistence.entity.ProcessDefinitionInfoEntity;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.activiti.ignite.entity.ProcessDefinitionInfoEntityImpl;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.CacheConfiguration;

import javax.cache.Cache;
import java.util.List;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class ProcessDefinitionInfoDataManager extends AbstractDataManager<ProcessDefinitionInfoEntity> implements org.activiti.engine.impl.persistence.entity.data.ProcessDefinitionInfoDataManager {

    public ProcessDefinitionInfoDataManager(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
        CacheConfiguration<String, ProcessDefinitionInfoEntity> ccfg = new CacheConfiguration<>(this.getClass().getName());
        ccfg.setIndexedTypes(String.class, ProcessDefinitionInfoEntityImpl.class);
        cache = processEngineConfiguration.getIgnite().getOrCreateCache(ccfg);
    }

    @Override
    public ProcessDefinitionInfoEntity findProcessDefinitionInfoByProcessDefinitionId(String processDefinitionId) {
        String query = "processDefinitionId = ?";

        List<Cache.Entry<String, ProcessDefinitionInfoEntityImpl>> list = cache.query(new SqlQuery<String, ProcessDefinitionInfoEntityImpl>(ProcessDefinitionInfoEntityImpl.class, query).setArgs(processDefinitionId)).getAll();

        return list.size() > 0 ? list.get(0).getValue() : null;
    }

    @Override
    public ProcessDefinitionInfoEntity create() {
        return new ProcessDefinitionInfoEntityImpl();
    }

}
