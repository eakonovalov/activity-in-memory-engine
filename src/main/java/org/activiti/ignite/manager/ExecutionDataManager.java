package org.activiti.ignite.manager;

import org.activiti.engine.impl.ExecutionQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.ProcessInstanceQueryImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.activiti.ignite.entity.ExecutionEntityImpl;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.CacheConfiguration;

import javax.cache.Cache;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Joram Barrez
 */
public class ExecutionDataManager extends AbstractDataManager<ExecutionEntity> implements org.activiti.engine.impl.persistence.entity.data.ExecutionDataManager {

    protected Map<String, ExecutionEntity> entities = new ConcurrentHashMap<>();

    public ExecutionDataManager(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
        CacheConfiguration<String, ExecutionEntity> ccfg = new CacheConfiguration<>(this.getClass().getName());
        ccfg.setIndexedTypes(String.class, ExecutionEntityImpl.class);
        cache = processEngineConfiguration.getIgnite().getOrCreateCache(ccfg);
    }

    public ExecutionEntity findById(String entityId) {
        if (entityId == null) {
            return null;
        }

        ExecutionEntity e = entities.get(entityId);
        if (e != null) return e;

        return cache.get(entityId);
    }

    public void insert(ExecutionEntity entity) {
        if (entity.getId() == null) {
            entity.setId(getProcessEngineConfiguration().getIdGenerator().getNextId());
        }
        entities.put(entity.getId(), entity);
        cache.put(entity.getId(), entity);
    }

    public ExecutionEntity update(ExecutionEntity entity) {
        ExecutionEntity matchingEntity = cache.get(entity.getId());
        if (matchingEntity != null) {
            entities.put(entity.getId(), entity);
            cache.put(entity.getId(), entity);
            return entity;
        } else {
            return null;
        }
    }

    public void delete(String id) {
        entities.remove(id);
        cache.remove(id);
    }

    public ExecutionEntity create() {
        return new ExecutionEntityImpl();
    }

    public ExecutionEntity findSubProcessInstanceBySuperExecutionId(String superExecutionId) {
        String query = "superExecutionId = ?";

        List<Cache.Entry<String, ExecutionEntityImpl>> list = cache.query(new SqlQuery<String, ExecutionEntityImpl>(ExecutionEntityImpl.class, query).setArgs(superExecutionId)).getAll();

        return list.size() > 0 ? list.get(0).getValue() : null;
    }

    public List<ExecutionEntity> findChildExecutionsByParentExecutionId(String parentExecutionId) {
        String query = "parentId = ?";

        List<Cache.Entry<String, ExecutionEntityImpl>> list = cache.query(new SqlQuery<String, ExecutionEntityImpl>(ExecutionEntityImpl.class, query).setArgs(parentExecutionId)).getAll();
        List<ExecutionEntity> results = new ArrayList<>();
        for (Cache.Entry<String, ExecutionEntityImpl> entry : list) {
            results.add(entry.getValue());
        }

        return results;
    }

    public List<ExecutionEntity> findChildExecutionsByProcessInstanceId(String processInstanceId) {
        String query = "processInstanceId = ?";

        List<Cache.Entry<String, ExecutionEntityImpl>> list = cache.query(new SqlQuery<String, ExecutionEntityImpl>(ExecutionEntityImpl.class, query).setArgs(processInstanceId)).getAll();
        List<ExecutionEntity> results = new ArrayList<>();
        for (Cache.Entry<String, ExecutionEntityImpl> entry : list) {
            results.add(entry.getValue());
        }

        return results;
    }

    public List<ExecutionEntity> findExecutionsByParentExecutionAndActivityIds(String parentExecutionId, Collection<String> activityIds) {
        throw new UnsupportedOperationException();
    }

    public long findExecutionCountByQueryCriteria(ExecutionQueryImpl executionQuery) {
        throw new UnsupportedOperationException();
    }

    public List<ExecutionEntity> findExecutionsByQueryCriteria(ExecutionQueryImpl executionQuery, Page page) {
        throw new UnsupportedOperationException();
    }

    public long findProcessInstanceCountByQueryCriteria(ProcessInstanceQueryImpl executionQuery) {
        throw new UnsupportedOperationException();
    }

    public List<ProcessInstance> findProcessInstanceByQueryCriteria(ProcessInstanceQueryImpl executionQuery) {
        throw new UnsupportedOperationException();
    }

    public List<ExecutionEntity> findExecutionsByRootProcessInstanceId(String rootProcessInstanceId) {
        String query = "rootProcessInstanceId = ?";

        List<Cache.Entry<String, ExecutionEntityImpl>> list = cache.query(new SqlQuery<String, ExecutionEntityImpl>(ExecutionEntityImpl.class, query).setArgs(rootProcessInstanceId)).getAll();
        List<ExecutionEntity> results = new ArrayList<>();
        for (Cache.Entry<String, ExecutionEntityImpl> entry : list) {
            results.add(entry.getValue());
        }

        return results;
    }

    public List<ExecutionEntity> findExecutionsByProcessInstanceId(String processInstanceId) {
        throw new UnsupportedOperationException();
    }

    public List<ProcessInstance> findProcessInstanceAndVariablesByQueryCriteria(ProcessInstanceQueryImpl executionQuery) {
        throw new UnsupportedOperationException();
    }

    public Collection<ExecutionEntity> findInactiveExecutionsByProcessInstanceId(String processInstanceId) {
        throw new UnsupportedOperationException();
    }

    public Collection<ExecutionEntity> findInactiveExecutionsByActivityIdAndProcessInstanceId(String activityId, String processInstanceId) {
        throw new UnsupportedOperationException();
    }

    public List<String> findProcessInstanceIdsByProcessDefinitionId(String processDefinitionId) {
        throw new UnsupportedOperationException();
    }

    public List<Execution> findExecutionsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
        throw new UnsupportedOperationException();
    }

    public List<ProcessInstance> findProcessInstanceByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
        throw new UnsupportedOperationException();
    }

    public long findExecutionCountByNativeQuery(Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

    public void updateExecutionTenantIdForDeployment(String deploymentId, String newTenantId) {
        throw new UnsupportedOperationException();
    }

    public void updateProcessInstanceLockTime(String processInstanceId, Date lockDate, Date expirationTime) {
        throw new UnsupportedOperationException();
    }

    public void updateAllExecutionRelatedEntityCountFlags(boolean newValue) {
        throw new UnsupportedOperationException();
    }

    public void clearProcessInstanceLockTime(String processInstanceId) {
        throw new UnsupportedOperationException();
    }

}