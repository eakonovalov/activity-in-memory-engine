package org.activiti.ignite.manager;

import org.activiti.engine.impl.DeadLetterJobQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.DeadLetterJobEntity;
import org.activiti.engine.impl.persistence.entity.DeadLetterJobEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.DeadLetterJobDataManager;
import org.activiti.engine.runtime.Job;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.cache.CacheEntry;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public class DeadLetterJobDataManagerImpl extends AbstractDataManager<DeadLetterJobEntity, DeadLetterJobEntityImpl> implements DeadLetterJobDataManager {

    @Autowired
    @Qualifier("deadLetterJobEntityCache")
    private CacheConfiguration<String, DeadLetterJobEntity> config;

    public DeadLetterJobDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, DeadLetterJobEntity> getConfig() {
        return config;
    }

    @Override
    public DeadLetterJobEntity create() {
        return new DeadLetterJobEntityImpl();
    }

    @Override
    public List<DeadLetterJobEntity> findJobsByExecutionId(String executionId) {
        return findList(DeadLetterJobEntityImpl.class, "executionId = ?", executionId);
    }

    @Override
    public List<Job> findJobsByQueryCriteria(DeadLetterJobQueryImpl jobQuery, Page page) {
        QueryBuilder queryBuilder = getQueryBuilder(jobQuery);
        queryBuilder.setSelectClause("*");
        queryBuilder.setFromClause("DeadLetterJobEntityImpl d");
        SqlFieldsQuery qry = new SqlFieldsQuery(queryBuilder.getQuery()).setArgs(queryBuilder.getArgs().toArray());
        Iterator<List<?>> itr = getCache().query(qry).iterator();
        List<Job> result = new ArrayList<>();
        while (itr.hasNext()) {
            List<?> o = itr.next();
            result.add((Job) o.get(1));
        }

        return result;
    }

    @Override
    public long findJobCountByQueryCriteria(DeadLetterJobQueryImpl jobQuery) {
        QueryBuilder queryBuilder = getQueryBuilder(jobQuery);
        queryBuilder.setSelectClause("COUNT(*)");
        queryBuilder.setFromClause("DeadLetterJobEntityImpl d");
        SqlFieldsQuery qry = new SqlFieldsQuery(queryBuilder.getQuery()).setArgs(queryBuilder.getArgs().toArray());
        Collection<List<?>> res = getCache().query(qry).getAll();

        return (Long) res.iterator().next().get(0);
    }

    private QueryBuilder getQueryBuilder(DeadLetterJobQueryImpl jobQuery) {
        QueryBuilder result = new QueryBuilder();
        if (jobQuery.getId() != null) {
            result.appendCondition("d.id = ?");
            result.appendArgs(jobQuery.getId());
        }
        if (jobQuery.getProcessInstanceId() != null) {
            result.appendCondition("d.processInstanceId = ?");
            result.appendArgs(jobQuery.getProcessInstanceId());
        }
        if (jobQuery.getExecutionId() != null) {
            result.appendCondition("d.executionId = ?");
            result.appendArgs(jobQuery.getExecutionId());
        }
        if (jobQuery.getProcessDefinitionId() != null) {
            result.appendCondition("d.processDefinitionId = ?");
            result.appendArgs(jobQuery.getProcessDefinitionId());
        }
        if (jobQuery.getExecutable()) {
            result.appendCondition("(d.duedate IS NULL OR d.duedate <= NOW())");
        }
        if (jobQuery.isOnlyTimers()) {
            result.appendCondition("d.jobType = ?");
            result.appendArgs("timer");
        }
        if (jobQuery.isOnlyMessages()) {
            result.appendCondition("d.jobType = ?");
            result.appendArgs("message");
        }
        if (jobQuery.getDuedateHigherThan() != null) {
            result.appendCondition("d.duedate > ?");
            result.appendArgs(jobQuery.getDuedateHigherThan());
        }
        if (jobQuery.getDuedateLowerThan() != null) {
            result.appendCondition("d.duedate < ?");
            result.appendArgs(jobQuery.getDuedateLowerThan());
        }
        if (jobQuery.getDuedateHigherThanOrEqual() != null) {
            result.appendCondition("d.duedate >= ?");
            result.appendArgs(jobQuery.getDuedateHigherThanOrEqual());
        }
        if (jobQuery.getDuedateLowerThanOrEqual() != null) {
            result.appendCondition("d.duedate <= ?");
            result.appendArgs(jobQuery.getDuedateLowerThanOrEqual());
        }
        if (jobQuery.isWithException()) {
            result.appendCondition("(d.exceptionMessage IS NOT NULL OR d.exceptionByteArrayRef IS NOT NULL)");
        }
        if (jobQuery.getExceptionMessage() != null) {
            result.appendCondition("d.exceptionMessage = ?");
            result.appendArgs(jobQuery.getExceptionMessage());
        }
        if (jobQuery.getTenantId() != null) {
            result.appendCondition("d.tenantId = ?");
            result.appendArgs(jobQuery.getTenantId());
        }
        if (jobQuery.getTenantIdLike() != null) {
            result.appendCondition("d.tenantId LIKE '%" + jobQuery.getTenantIdLike() + "%'");
        }
        if (jobQuery.isWithoutTenantId()) {
            result.appendCondition("(d.tenantId = '' OR d.tenantId IS NULL)");
        }

        return result;
    }

    @Override
    public void updateJobTenantIdForDeployment(String deploymentId, String newTenantId) {
        throw new UnsupportedOperationException();
    }

}
