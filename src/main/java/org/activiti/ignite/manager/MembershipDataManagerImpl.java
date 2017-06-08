package org.activiti.ignite.manager;

import org.activiti.engine.impl.persistence.entity.MembershipEntity;
import org.activiti.engine.impl.persistence.entity.MembershipEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.MembershipDataManager;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by ekonovalov on 08.06.2017.
 */
public class MembershipDataManagerImpl extends AbstractDataManager<MembershipEntity, MembershipEntityImpl> implements MembershipDataManager {

    @Autowired
    @Qualifier("membershipEntityCache")
    private CacheConfiguration<String, MembershipEntity> config;

    public MembershipDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, MembershipEntity> getConfig() {
        return config;
    }

    @Override
    public void deleteMembership(String userId, String groupId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteMembershipByGroupId(String groupId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteMembershipByUserId(String userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MembershipEntity create() {
        return new MembershipEntityImpl();
    }

}
