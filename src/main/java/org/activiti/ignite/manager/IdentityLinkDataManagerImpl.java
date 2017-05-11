package org.activiti.ignite.manager;

import org.activiti.engine.impl.persistence.entity.IdentityLinkEntity;
import org.activiti.engine.impl.persistence.entity.IdentityLinkEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.IdentityLinkDataManager;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class IdentityLinkDataManagerImpl extends AbstractDataManager<IdentityLinkEntity, IdentityLinkEntityImpl> implements IdentityLinkDataManager {

    @Autowired
    @Qualifier("identityLinkEntityCache")
    private CacheConfiguration<String, IdentityLinkEntity> config;

    public IdentityLinkDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, IdentityLinkEntity> getConfig() {
        return config;
    }

    public IdentityLinkEntity create() {
        return new IdentityLinkEntityImpl();
    }

    public List<IdentityLinkEntity> findIdentityLinksByTaskId(String taskId) {
        return findList(IdentityLinkEntityImpl.class, "taskId = ?", taskId);
    }

    public List<IdentityLinkEntity> findIdentityLinksByProcessInstanceId(String processInstanceId) {
        return findList(IdentityLinkEntityImpl.class, "processInstanceId = ?", processInstanceId);
    }

    public List<IdentityLinkEntity> findIdentityLinksByProcessDefinitionId(String processDefinitionId) {
        throw new UnsupportedOperationException();
    }

    public List<IdentityLinkEntity> findIdentityLinkByTaskUserGroupAndType(String taskId, String userId, String groupId, String type) {
        throw new UnsupportedOperationException();
    }

    public List<IdentityLinkEntity> findIdentityLinkByProcessInstanceUserGroupAndType(String processInstanceId, String userId, String groupId, String type) {
        throw new UnsupportedOperationException();
    }

    public List<IdentityLinkEntity> findIdentityLinkByProcessDefinitionUserAndGroup(String processDefinitionId, String userId, String groupId) {
        throw new UnsupportedOperationException();
    }

    public void deleteIdentityLinksByProcDef(String processDefId) {
        throw new UnsupportedOperationException();
    }

}
