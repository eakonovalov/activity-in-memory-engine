package org.activiti.ignite.manager;

import org.activiti.engine.impl.ModelQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.ModelEntity;
import org.activiti.engine.impl.persistence.entity.ModelEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.ModelDataManager;
import org.activiti.engine.repository.Model;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

/**
 * Created by ekonovalov on 08.06.2017.
 */
public class ModelDataManagerImpl extends AbstractDataManager<ModelEntity, ModelEntityImpl> implements ModelDataManager {

    @Autowired
    @Qualifier("modelEntityCache")
    private CacheConfiguration<String, ModelEntity> config;

    public ModelDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, ModelEntity> getConfig() {
        return config;
    }


    @Override
    public List<Model> findModelsByQueryCriteria(ModelQueryImpl query, Page page) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findModelCountByQueryCriteria(ModelQueryImpl query) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Model> findModelsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findModelCountByNativeQuery(Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ModelEntity create() {
        return new ModelEntityImpl();
    }

}
