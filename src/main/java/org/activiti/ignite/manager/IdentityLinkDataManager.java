package org.activiti.ignite.manager;

import org.activiti.engine.impl.persistence.entity.IdentityLinkEntity;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.activiti.ignite.entity.IdentityLinkEntityImpl;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.CacheConfiguration;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joram Barrez
 */
public class IdentityLinkDataManager extends AbstractDataManager<IdentityLinkEntity> implements org.activiti.engine.impl.persistence.entity.data.IdentityLinkDataManager {

    public IdentityLinkDataManager(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
        CacheConfiguration<String, IdentityLinkEntity> ccfg = new CacheConfiguration<>(this.getClass().getName());
        ccfg.setIndexedTypes(String.class, IdentityLinkEntityImpl.class);
        cache = processEngineConfiguration.getIgnite().getOrCreateCache(ccfg);
    }

    public IdentityLinkEntity create() {
        return new IdentityLinkEntityImpl();
    }

    public List<IdentityLinkEntity> findIdentityLinksByTaskId(String taskId) {
        String query = "taskId = ?";

        List<Cache.Entry<String, IdentityLinkEntityImpl>> list = cache.query(new SqlQuery<String, IdentityLinkEntityImpl>(IdentityLinkEntityImpl.class, query).setArgs(taskId)).getAll();
        List<IdentityLinkEntity> results = new ArrayList<>();
        for (Cache.Entry<String, IdentityLinkEntityImpl> entry : list) {
            results.add(entry.getValue());
        }

        return results;
    }

    public List<IdentityLinkEntity> findIdentityLinksByProcessInstanceId(String processInstanceId) {
        String query = "processInstanceId = ?";

        List<Cache.Entry<String, IdentityLinkEntityImpl>> list = cache.query(new SqlQuery<String, IdentityLinkEntityImpl>(IdentityLinkEntityImpl.class, query).setArgs(processInstanceId)).getAll();
        List<IdentityLinkEntity> results = new ArrayList<>();
        for (Cache.Entry<String, IdentityLinkEntityImpl> entry : list) {
            results.add(entry.getValue());
        }

        return results;
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
