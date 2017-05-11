package org.activiti.ignite.manager;

import org.activiti.engine.impl.TaskQueryImpl;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.TaskDataManager;
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
 * Created by ekonovalov on 26.04.2017.
 */
public class TaskDataManagerImpl extends AbstractDataManager<TaskEntity, TaskEntityImpl> implements TaskDataManager {

    @Autowired
    @Qualifier("taskEntityCache")
    private CacheConfiguration<String, TaskEntity> config;

    public TaskDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, TaskEntity> getConfig() {
        return config;
    }

    public TaskEntity create() {
        return new TaskEntityImpl();
    }

    public List<TaskEntity> findTasksByExecutionId(String executionId) {
        return findList(TaskEntityImpl.class, "executionId = ?", executionId);
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

        List<TaskEntity> list = findList(TaskEntityImpl.class, query, args.toArray());

        return new ArrayList<>(list);
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
        return new ArrayList<>(findList(TaskEntityImpl.class, "parentTaskId = ?", parentTaskId));
    }

    public void updateTaskTenantIdForDeployment(String deploymentId, String newTenantId) {
        throw new UnsupportedOperationException();
    }

}
