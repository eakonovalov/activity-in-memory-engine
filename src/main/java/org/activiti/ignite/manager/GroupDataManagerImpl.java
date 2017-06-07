package org.activiti.ignite.manager;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.GroupDataManager;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

/**
 * Created by ekonovalov on 07.06.2017.
 */
public class GroupDataManagerImpl extends AbstractDataManager<GroupEntity, GroupEntityImpl> implements GroupDataManager {

    @Autowired
    @Qualifier("groupEntityCache")
    private CacheConfiguration<String, GroupEntity> config;

    public GroupDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, GroupEntity> getConfig() {
        return config;
    }


    @Override
    public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Group> findGroupsByUser(String userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Group> findGroupsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findGroupCountByNativeQuery(Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GroupEntity create() {
        return new GroupEntityImpl();
    }

}
