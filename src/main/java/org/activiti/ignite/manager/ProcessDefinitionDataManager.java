package org.activiti.ignite.manager;

import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.ProcessDefinitionQueryImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.activiti.ignite.entity.ProcessDefinitionEntityImpl;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.CacheConfiguration;

import javax.cache.Cache;
import java.util.List;
import java.util.Map;

/**
 * @author Joram Barrez
 */
public class ProcessDefinitionDataManager extends AbstractDataManager<ProcessDefinitionEntity> implements org.activiti.engine.impl.persistence.entity.data.ProcessDefinitionDataManager {

    public ProcessDefinitionDataManager(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
        CacheConfiguration<String, ProcessDefinitionEntity> ccfg = new CacheConfiguration<>(this.getClass().getName());
        ccfg.setIndexedTypes(String.class, ProcessDefinitionEntityImpl.class);
        cache = processEngineConfiguration.getIgnite().getOrCreateCache(ccfg);
    }

    public ProcessDefinitionEntity create() {
        return new ProcessDefinitionEntityImpl();
    }

    public ProcessDefinitionEntity findLatestProcessDefinitionByKey(String processDefinitionKey) {
        String query = "key = ?";

        List<Cache.Entry<String, ProcessDefinitionEntityImpl>> list = cache.query(new SqlQuery<String, ProcessDefinitionEntityImpl>(ProcessDefinitionEntityImpl.class, query).setArgs(processDefinitionKey)).getAll();
        ProcessDefinitionEntityImpl result = null;
        for (Cache.Entry<String, ProcessDefinitionEntityImpl> entry : list) {
            if (result == null || result.getVersion() < entry.getValue().getVersion()) {
                result = entry.getValue();
            }
        }

        return result;
    }

    public ProcessDefinitionEntity findLatestProcessDefinitionByKeyAndTenantId(String processDefinitionKey, String tenantId) {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    public ProcessDefinitionEntity findProcessDefinitionByDeploymentAndKeyAndTenantId(String deploymentId, String processDefinitionKey, String tenantId) {
        throw new UnsupportedOperationException();
    }

    public ProcessDefinitionEntity findProcessDefinitionByKeyAndVersion(String processDefinitionKey, Integer processDefinitionVersion) {
        throw new UnsupportedOperationException();
    }

    public ProcessDefinitionEntity findProcessDefinitionByKeyAndVersionAndTenantId(String processDefinitionKey, Integer processDefinitionVersion, String tenantId) {
        throw new UnsupportedOperationException();
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
