package org.activiti.ignite.manager;

import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.ProcessDefinitionQueryImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.ProcessDefinitionDataManager;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class ProcessDefinitionDataManagerImpl extends AbstractDataManager<ProcessDefinitionEntity, ProcessDefinitionEntityImpl> implements ProcessDefinitionDataManager {

    @Autowired
    @Qualifier("processDefinitionEntityCache")
    private CacheConfiguration<String, ProcessDefinitionEntity> config;

    public ProcessDefinitionDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, ProcessDefinitionEntity> getConfig() {
        return config;
    }

    public ProcessDefinitionEntity create() {
        return new ProcessDefinitionEntityImpl();
    }

    public ProcessDefinitionEntity findLatestProcessDefinitionByKey(String processDefinitionKey) {
        List<ProcessDefinitionEntity> list = findList(ProcessDefinitionEntityImpl.class, "key = ?", processDefinitionKey);
        ProcessDefinitionEntity result = null;
        for (ProcessDefinitionEntity e : list) {
            if (result == null || result.getVersion() < e.getVersion()) {
                result = e;
            }
        }

        return result;
    }

    public ProcessDefinitionEntity findLatestProcessDefinitionByKeyAndTenantId(String processDefinitionKey, String tenantId) {
        List<ProcessDefinitionEntity> list = findList(ProcessDefinitionEntityImpl.class, "key = ? and tenantId = ?", processDefinitionKey, tenantId);
        ProcessDefinitionEntity result = null;
        for (ProcessDefinitionEntity e : list) {
            if (result == null || result.getVersion() < e.getVersion()) {
                result = e;
            }
        }

        return result;
    }

    public void deleteProcessDefinitionsByDeploymentId(String deploymentId) {
        throw new UnsupportedOperationException();
    }

    public List<ProcessDefinition> findProcessDefinitionsByQueryCriteria(ProcessDefinitionQueryImpl processDefinitionQuery, Page page) {
        throw new UnsupportedOperationException();
    }

    public long findProcessDefinitionCountByQueryCriteria(ProcessDefinitionQueryImpl processDefinitionQuery) {
        throw new UnsupportedOperationException();
    }

    public ProcessDefinitionEntity findProcessDefinitionByDeploymentAndKey(String deploymentId, String processDefinitionKey) {
        return findOne(ProcessDefinitionEntityImpl.class, "deploymentId = ? and key = ?", deploymentId, processDefinitionKey);
    }

    public ProcessDefinitionEntity findProcessDefinitionByDeploymentAndKeyAndTenantId(String deploymentId, String processDefinitionKey, String tenantId) {
        return findOne(ProcessDefinitionEntityImpl.class, "deploymentId = ? and key = ? and tenantId = ?", deploymentId, processDefinitionKey, tenantId);
    }

    public ProcessDefinitionEntity findProcessDefinitionByKeyAndVersion(String processDefinitionKey, Integer processDefinitionVersion) {
        return findOne(ProcessDefinitionEntityImpl.class, "key = ? and version = ?", processDefinitionKey, processDefinitionVersion);
    }

    public ProcessDefinitionEntity findProcessDefinitionByKeyAndVersionAndTenantId(String processDefinitionKey, Integer processDefinitionVersion, String tenantId) {
        return findOne(ProcessDefinitionEntityImpl.class, "key = ? and version = ? and tenantId = ?", processDefinitionKey, processDefinitionVersion, tenantId);
    }

    public List<ProcessDefinition> findProcessDefinitionsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
        throw new UnsupportedOperationException();
    }

    public long findProcessDefinitionCountByNativeQuery(Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

    public void updateProcessDefinitionTenantIdForDeployment(String deploymentId, String newTenantId) {
        throw new UnsupportedOperationException();
    }

}
