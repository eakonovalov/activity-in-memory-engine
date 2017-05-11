package org.activiti.ignite.manager;

import org.activiti.engine.impl.persistence.entity.AttachmentEntity;
import org.activiti.engine.impl.persistence.entity.AttachmentEntityImpl;
import org.activiti.engine.impl.persistence.entity.ByteArrayEntity;
import org.activiti.engine.impl.persistence.entity.ByteArrayEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.ByteArrayDataManager;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by ekonovalov on 11.05.2017.
 */
public class ByteArrayDataManagerImpl extends AbstractDataManager<ByteArrayEntity, ByteArrayEntityImpl> implements ByteArrayDataManager {

    @Autowired
    @Qualifier("byteArrayEntityCache")
    private CacheConfiguration<String, ByteArrayEntity> config;

    public ByteArrayDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, ByteArrayEntity> getConfig() {
        return config;
    }

    @Override
    public ByteArrayEntity create() {
        return new ByteArrayEntityImpl();
    }

    @Override
    public void deleteByteArrayNoRevisionCheck(String byteArrayEntityId) {
        getCache().remove(byteArrayEntityId);
    }

}
