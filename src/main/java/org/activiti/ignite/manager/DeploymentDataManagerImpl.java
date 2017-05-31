package org.activiti.ignite.manager;

import org.activiti.engine.impl.DeploymentQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.DeploymentEntity;
import org.activiti.engine.impl.persistence.entity.DeploymentEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.DeploymentDataManager;
import org.activiti.engine.repository.Deployment;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class DeploymentDataManagerImpl extends AbstractDataManager<DeploymentEntity, DeploymentEntityImpl> implements DeploymentDataManager {

    @Autowired
    @Qualifier("deploymentEntityCache")
    private CacheConfiguration<String, DeploymentEntity> config;

    public DeploymentDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, DeploymentEntity> getConfig() {
        return config;
    }

    public DeploymentEntity findLatestDeploymentByName(String deploymentName) {
        List<DeploymentEntity> result = findList(DeploymentEntityImpl.class, "name = ? ORDER BY deploymentTime", deploymentName);
        return result.size() > 0 ? result.get(0) : null;
    }

    public long findDeploymentCountByQueryCriteria(DeploymentQueryImpl deploymentQuery) {
        QueryBuilder queryBuilder = getQueryBuilder(deploymentQuery);
        String queryString = "SELECT COUNT(*) " + queryBuilder.getFromClause() + " WHERE " + queryBuilder.getConditions();
        SqlFieldsQuery qry = new SqlFieldsQuery(queryString).setArgs(queryBuilder.getArgs().toArray());
        Collection<List<?>> res = getCache().query(qry).getAll();

        return (Long) res.iterator().next().get(0);
    }

    public List<Deployment> findDeploymentsByQueryCriteria(DeploymentQueryImpl deploymentQuery, Page page) {
        throw new UnsupportedOperationException();
    }

    private QueryBuilder getQueryBuilder(DeploymentQueryImpl deploymentQuery) {
        QueryBuilder result = new QueryBuilder();
        if(deploymentQuery.getProcessDefinitionKey() != null || deploymentQuery.getProcessDefinitionKeyLike() != null) {
            result.setFromClause("FROM DeploymentEntityImpl d, \"processDefinitionEntityCache\".ProcessDefinitionEntityImpl pd");
            result.appendCondition("d.id = pd.deploymentId");
        }
        else {
            result.setFromClause("FROM DeploymentEntityImpl d");
        }
        if (deploymentQuery.getDeploymentId() != null) {
            result.appendCondition("d.id = ?");
            result.appendArgs(deploymentQuery.getDeploymentId());
        }
        if (deploymentQuery.getName() != null) {
            result.appendCondition("d.name = ?");
            result.appendArgs(deploymentQuery.getName());
        }
        if (deploymentQuery.getNameLike() != null) {
            result.appendCondition("d.name = %" + deploymentQuery.getNameLike() + "%");
        }
        if (deploymentQuery.getCategory() != null) {
            result.appendCondition("d.category = ?");
            result.appendArgs(deploymentQuery.getCategory());
        }
        if (deploymentQuery.getCategoryNotEquals() != null) {
            result.appendCondition("d.category <> ?");
            result.appendArgs(deploymentQuery.getCategory());
        }
        if (deploymentQuery.getProcessDefinitionKey() != null) {
            result.appendCondition("pd.key = ?");
            result.appendArgs(deploymentQuery.getProcessDefinitionKey());
        }
        if (deploymentQuery.getProcessDefinitionKeyLike() != null) {
            result.appendCondition("pd.key = %" + deploymentQuery.getProcessDefinitionKeyLike() + "%");
        }

        return result;
    }

    public List<String> getDeploymentResourceNames(String deploymentId) {
        throw new UnsupportedOperationException();
    }

    public List<Deployment> findDeploymentsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
        throw new UnsupportedOperationException();
    }

    public long findDeploymentCountByNativeQuery(Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

    public DeploymentEntity create() {
        return new DeploymentEntityImpl();
    }

}
