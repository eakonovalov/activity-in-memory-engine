package org.activiti.ignite.manager;

import org.activiti.engine.impl.persistence.entity.IdentityInfoEntity;
import org.activiti.engine.impl.persistence.entity.IdentityInfoEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.IdentityInfoDataManager;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by ekonovalov on 08.06.2017.
 */
public class IdentityInfoDataManagerImpl extends AbstractDataManager<IdentityInfoEntity, IdentityInfoEntityImpl> implements IdentityInfoDataManager {

    @Autowired
    @Qualifier("identityInfoEntityCache")
    private CacheConfiguration<String, IdentityInfoEntity> config;

    public IdentityInfoDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, IdentityInfoEntity> getConfig() {
        return config;
    }


    @Override
    public List<IdentityInfoEntity> findIdentityInfoDetails(String identityInfoId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<IdentityInfoEntity> findIdentityInfoByUserId(String userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IdentityInfoEntity findUserInfoByUserIdAndKey(String userId, String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> findUserInfoKeysByUserIdAndType(String userId, String type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IdentityInfoEntity create() {
        return new IdentityInfoEntityImpl();
    }

}
