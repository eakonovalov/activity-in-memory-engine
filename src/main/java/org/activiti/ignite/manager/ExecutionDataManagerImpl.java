package org.activiti.ignite.manager;

import org.activiti.engine.impl.ExecutionQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.ProcessInstanceQueryImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.ExecutionDataManager;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class ExecutionDataManagerImpl extends AbstractDataManager<ExecutionEntity, ExecutionEntityImpl> implements ExecutionDataManager {

    @Autowired
    @Qualifier("executionEntityCache")
    private CacheConfiguration<String, ExecutionEntity> config;

    private Map<String, ExecutionEntity> entities = new ConcurrentHashMap<>();

    public ExecutionDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, ExecutionEntity> getConfig() {
        return config;
    }

    public ExecutionEntity findById(String entityId) {
        if (entityId == null) {
            return null;
        }

        ExecutionEntity e = entities.get(entityId);
        if (e != null) return e;

        return getCache().get(entityId);
    }

    public void insert(ExecutionEntity entity) {
        if (entity.getId() == null) {
            entity.setId(getProcessEngineConfiguration().getIdGenerator().getNextId());
        }
        entities.put(entity.getId(), entity);
        getCache().put(entity.getId(), entity);
    }

    public ExecutionEntity update(ExecutionEntity entity) {
        ExecutionEntity matchingEntity = getCache().get(entity.getId());
        if (matchingEntity != null) {
            entities.put(entity.getId(), entity);
            getCache().put(entity.getId(), entity);
            return entity;
        } else {
            return null;
        }
    }

    public void delete(String id) {
        entities.remove(id);
        getCache().remove(id);
    }

    public ExecutionEntity create() {
        return new ExecutionEntityImpl();
    }

    public ExecutionEntity findSubProcessInstanceBySuperExecutionId(String superExecutionId) {
        return findOne(ExecutionEntityImpl.class, "superExecutionId = ?", superExecutionId);
    }

    public List<ExecutionEntity> findChildExecutionsByParentExecutionId(String parentExecutionId) {
        return findList(ExecutionEntityImpl.class, "parentId = ?", parentExecutionId);
    }

    public List<ExecutionEntity> findChildExecutionsByProcessInstanceId(String processInstanceId) {
        return findList(ExecutionEntityImpl.class, "processInstanceId = ?", processInstanceId);
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
        return findList(ExecutionEntityImpl.class, "rootProcessInstanceId = ?", rootProcessInstanceId);
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
