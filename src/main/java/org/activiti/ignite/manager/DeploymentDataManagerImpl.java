package org.activiti.ignite.manager;

import org.activiti.engine.impl.DeploymentQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.DeploymentEntity;
import org.activiti.engine.impl.persistence.entity.DeploymentEntityImpl;
import org.activiti.engine.impl.persistence.entity.ResourceEntity;
import org.activiti.engine.impl.persistence.entity.data.DeploymentDataManager;
import org.activiti.engine.repository.Deployment;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;

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
        queryBuilder.setSelectClause("COUNT(*)");
        SqlFieldsQuery qry = new SqlFieldsQuery(queryBuilder.getQuery()).setArgs(queryBuilder.getArgs().toArray());
        Collection<List<?>> res = getCache().query(qry).getAll();

        return (Long) res.iterator().next().get(0);
    }

    public List<Deployment> findDeploymentsByQueryCriteria(DeploymentQueryImpl deploymentQuery, Page page) {
        QueryBuilder queryBuilder = getQueryBuilder(deploymentQuery);
        queryBuilder.setSelectClause("*");
        SqlFieldsQuery qry = new SqlFieldsQuery(queryBuilder.getQuery()).setArgs(queryBuilder.getArgs().toArray());
        Iterator<List<?>> itr = getCache().query(qry).iterator();
        List<Deployment> result = new ArrayList<>();
        while (itr.hasNext()) {
            List<?> o = itr.next();
            result.add((Deployment) o.get(1));
        }

        return result;
    }

    private QueryBuilder getQueryBuilder(DeploymentQueryImpl deploymentQuery) {
        QueryBuilder result = new QueryBuilder();
        if(deploymentQuery.getProcessDefinitionKey() != null || deploymentQuery.getProcessDefinitionKeyLike() != null) {
            result.setFromClause("DeploymentEntityImpl d, \"processDefinitionEntityCache\".ProcessDefinitionEntityImpl pd");
            result.appendCondition("d.id = pd.deploymentId");
        }
        else {
            result.setFromClause("DeploymentEntityImpl d");
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
            result.appendCondition("d.name LIKE '%" + deploymentQuery.getNameLike() + "%'");
        }
        if (deploymentQuery.getCategory() != null) {
            result.appendCondition("d.category = ?");
            result.appendArgs(deploymentQuery.getCategory());
        }
        if (deploymentQuery.getCategoryNotEquals() != null) {
            result.appendCondition("d.category <> ?");
            result.appendArgs(deploymentQuery.getCategoryNotEquals());
        }
        if (deploymentQuery.getProcessDefinitionKey() != null) {
            result.appendCondition("pd.key = ?");
            result.appendArgs(deploymentQuery.getProcessDefinitionKey());
        }
        if (deploymentQuery.getProcessDefinitionKeyLike() != null) {
            result.appendCondition("pd.key LIKE '%" + deploymentQuery.getProcessDefinitionKeyLike() + "%'");
        }
        if (deploymentQuery.getTenantId() != null) {
            result.appendCondition("d.tenantId = ?");
            result.appendArgs(deploymentQuery.getTenantId());
        }
        if (deploymentQuery.getTenantIdLike() != null) {
            result.appendCondition("d.tenantId = %" + deploymentQuery.getTenantIdLike() + "%");
        }
        if (deploymentQuery.isWithoutTenantId()) {
            result.appendCondition("(d.tenantId = '' OR d.tenantId IS NULL)");
        }

        return result;
    }

    public List<String> getDeploymentResourceNames(String deploymentId) {
        List<ResourceEntity> resources = getProcessEngineConfiguration().getResourceDataManager().findResourcesByDeploymentId(deploymentId);
        List<String> result = new ArrayList<>();
        resources.forEach(r -> result.add(r.getName()));

        return result;
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
