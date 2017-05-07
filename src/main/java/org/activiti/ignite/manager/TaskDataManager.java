package org.activiti.ignite.manager;

import org.activiti.engine.impl.TaskQueryImpl;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntityImpl;
import org.activiti.engine.task.Task;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Joram Barrez
 */
public class TaskDataManager extends AbstractDataManager<TaskEntity> implements org.activiti.engine.impl.persistence.entity.data.TaskDataManager {

    @Autowired
    @Qualifier("taskEntityCache")
    private CacheConfiguration<String, TaskEntity> config;

    public TaskDataManager(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, TaskEntity> getConfig() {
        return config;
    }

    public TaskEntity create() {
        return new TaskEntityImpl();
    }

    public TaskEntity findById(String taskId, boolean checkCache) {
        return findById(taskId);
    }

    public List<TaskEntity> findTasksByExecutionId(String executionId) {
        String query = "executionId = ?";

        List<Cache.Entry<String, TaskEntityImpl>> list = getCache().query(new SqlQuery<String, TaskEntityImpl>(TaskEntityImpl.class, query).setArgs(executionId)).getAll();
        List<TaskEntity> results = new ArrayList<>();
        for (Cache.Entry<String, TaskEntityImpl> entry : list) {
            results.add(entry.getValue());
        }

        return results;
    }

    public List<TaskEntity> findTasksByProcessInstanceId(String processInstanceId) {
        throw new UnsupportedOperationException();
    }

    public List<Task> findTasksByQueryCriteria(TaskQueryImpl taskQuery) {
        String query = "";
        List<Object> args = new ArrayList<>();
        if (taskQuery.getName() != null) {
            query += "name = ?";
            args.add(taskQuery.getName());
        }
        if (taskQuery.getProcessInstanceId() != null) {
            query += query.length() == 0 ? "" : " or ";
            query += "processInstanceId = ?";
            args.add(taskQuery.getProcessInstanceId());
        }

        List<Cache.Entry<String, TaskEntityImpl>> list = getCache().query(new SqlQuery<String, TaskEntityImpl>(TaskEntityImpl.class, query).setArgs(args.toArray())).getAll();
        List<Task> results = new ArrayList<>();
        for (Cache.Entry<String, TaskEntityImpl> entry : list) {
            results.add(entry.getValue());
        }

        return results;
    }

    public List<Task> findTasksAndVariablesByQueryCriteria(TaskQueryImpl taskQuery) {
        throw new UnsupportedOperationException();
    }

    public long findTaskCountByQueryCriteria(TaskQueryImpl taskQuery) {
        throw new UnsupportedOperationException();
    }

    public List<Task> findTasksByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
        throw new UnsupportedOperationException();
    }

    public long findTaskCountByNativeQuery(Map<String, Object> parameterMap) {
        throw new UnsupportedOperationException();
    }

    public List<Task> findTasksByParentTaskId(String parentTaskId) {
        String query = "parentTaskId = ?";

        List<Cache.Entry<String, TaskEntityImpl>> list = getCache().query(new SqlQuery<String, TaskEntityImpl>(TaskEntityImpl.class, query).setArgs(parentTaskId)).getAll();
        List<Task> results = new ArrayList<>();
        for (Cache.Entry<String, TaskEntityImpl> entry : list) {
            results.add(entry.getValue());
        }

        return results;
    }

    public void updateTaskTenantIdForDeployment(String deploymentId, String newTenantId) {
        throw new UnsupportedOperationException();
    }

}
