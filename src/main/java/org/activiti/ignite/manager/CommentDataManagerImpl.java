package org.activiti.ignite.manager;

import org.activiti.engine.impl.persistence.entity.CommentEntity;
import org.activiti.engine.impl.persistence.entity.CommentEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.CommentDataManager;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Event;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

import static org.activiti.engine.impl.persistence.entity.CommentEntity.TYPE_EVENT;

/**
 * Created by ekonovalov on 15.05.2017.
 */
public class CommentDataManagerImpl extends AbstractDataManager<CommentEntity, CommentEntityImpl> implements CommentDataManager {

    @Autowired
    @Qualifier("commentEntityCache")
    private CacheConfiguration<String, CommentEntity> config;

    public CommentDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, CommentEntity> getConfig() {
        return config;
    }

    @Override
    public CommentEntity create() {
        return new CommentEntityImpl();
    }

    @Override
    public List<Comment> findCommentsByTaskId(String taskId) {
        return new ArrayList<>(findList(CommentEntityImpl.class, "taskId = ?", taskId));
    }

    @Override
    public List<Comment> findCommentsByTaskIdAndType(String taskId, String type) {
        return new ArrayList<>(findList(CommentEntityImpl.class, "taskId = ? and type = ?", taskId, type));
    }

    @Override
    public List<Comment> findCommentsByType(String type) {
        return new ArrayList<>(findList(CommentEntityImpl.class, "type = ?", type));
    }

    @Override
    public List<Event> findEventsByTaskId(String taskId) {
        return new ArrayList<>(findList(CommentEntityImpl.class, "taskId = ? AND type = '" + TYPE_EVENT + "'", taskId));
    }

    @Override
    public List<Event> findEventsByProcessInstanceId(String processInstanceId) {
        return new ArrayList<>(findList(CommentEntityImpl.class, "processInstanceId = ? AND type = '" + TYPE_EVENT + "'", processInstanceId));
    }

    @Override
    public void deleteCommentsByTaskId(String taskId) {
        removeList(CommentEntityImpl.class, "taskId = ?", taskId);
    }

    @Override
    public void deleteCommentsByProcessInstanceId(String processInstanceId) {
        removeList(CommentEntityImpl.class, "processInstanceId = ?", processInstanceId);
    }

    @Override
    public List<Comment> findCommentsByProcessInstanceId(String processInstanceId) {
        return new ArrayList<>(findList(CommentEntityImpl.class, "processInstanceId = ?", processInstanceId));
    }

    @Override
    public List<Comment> findCommentsByProcessInstanceId(String processInstanceId, String type) {
        return new ArrayList<>(findList(CommentEntityImpl.class, "processInstanceId = ? and type = ?", processInstanceId, type));
    }

    @Override
    public Comment findComment(String commentId) {
        return findById(commentId);
    }

    @Override
    public Event findEvent(String commentId) {
        return findById(commentId);
    }

}
