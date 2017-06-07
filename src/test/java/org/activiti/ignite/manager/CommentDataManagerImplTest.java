package org.activiti.ignite.manager;

import org.activiti.engine.impl.persistence.entity.CommentEntity;
import org.activiti.engine.impl.persistence.entity.CommentEntityImpl;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Event;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ekonovalov on 05.06.2017.
 */
public class CommentDataManagerImplTest extends AbstractDataManagerImplTest {

    @Test
    public void findCommentsByTaskIdAndType() throws Exception {
        CommentEntity entity1 = new CommentEntityImpl();
        CommentEntity entity2 = new CommentEntityImpl();
        CommentEntity entity3 = new CommentEntityImpl();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setTaskId(id1);
            entity1.setType("type1");
            config.getCommentDataManager().insert(entity1);

            String id2 = config.getIdGenerator().getNextId();
            entity2.setTaskId(id2);
            entity2.setType("type1");
            config.getCommentDataManager().insert(entity2);

            entity3.setTaskId(id2);
            entity3.setType("type2");
            config.getCommentDataManager().insert(entity3);

            List<Comment> comments = config.getCommentDataManager().findCommentsByTaskId(id2);
            assertEquals(2, comments.size());

            comments = config.getCommentDataManager().findCommentsByTaskIdAndType(id1, "type1");
            assertEquals(1, comments.size());

            comments = config.getCommentDataManager().findCommentsByType("type1");
            assertEquals(2, comments.size());
        }
        finally {
            config.getCommentDataManager().delete(entity1);
            config.getCommentDataManager().delete(entity2);
            config.getCommentDataManager().delete(entity3);
        }
    }

    @Test
    public void findEventsByProcessInstanceId() throws Exception {
        CommentEntity entity1 = new CommentEntityImpl();
        CommentEntity entity2 = new CommentEntityImpl();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setProcessInstanceId(id1);
            entity1.setType(CommentEntity.TYPE_EVENT);
            config.getCommentDataManager().insert(entity1);

            String id2 = config.getIdGenerator().getNextId();
            entity2.setProcessInstanceId(id2);
            entity2.setType(CommentEntity.TYPE_EVENT);
            config.getCommentDataManager().insert(entity2);

            List<Event> events = config.getCommentDataManager().findEventsByProcessInstanceId(id2);
            assertEquals(1, events.size());
        }
        finally {
            config.getCommentDataManager().delete(entity1);
            config.getCommentDataManager().delete(entity2);
        }
    }

    @Test
    public void deleteCommentsByTaskId() throws Exception {
        CommentEntity entity1 = new CommentEntityImpl();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setTaskId(id1);
            config.getCommentDataManager().insert(entity1);

            List<Comment> comments = config.getCommentDataManager().findCommentsByTaskId(id1);
            assertEquals(1, comments.size());

            config.getCommentDataManager().deleteCommentsByTaskId(id1);

            comments = config.getCommentDataManager().findCommentsByTaskId(id1);
            assertEquals(0, comments.size());
        }
        finally {
            config.getCommentDataManager().delete(entity1);
        }
    }

    @Test
    public void deleteCommentsByProcessInstanceId() throws Exception {
        CommentEntity entity1 = new CommentEntityImpl();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setProcessInstanceId(id1);
            config.getCommentDataManager().insert(entity1);

            List<Comment> comments = config.getCommentDataManager().findCommentsByProcessInstanceId(id1);
            assertEquals(1, comments.size());

            config.getCommentDataManager().deleteCommentsByProcessInstanceId(id1);

            comments = config.getCommentDataManager().findCommentsByProcessInstanceId(id1);
            assertEquals(0, comments.size());
        }
        finally {
            config.getCommentDataManager().delete(entity1);
        }
    }

    @Test
    public void findCommentsByProcessInstanceId() throws Exception {
        CommentEntity entity1 = new CommentEntityImpl();
        CommentEntity entity2 = new CommentEntityImpl();
        CommentEntity entity3 = new CommentEntityImpl();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setProcessInstanceId(id1);
            entity1.setType("type1");
            config.getCommentDataManager().insert(entity1);

            String id2 = config.getIdGenerator().getNextId();
            entity2.setProcessInstanceId(id2);
            entity2.setType("type1");
            config.getCommentDataManager().insert(entity2);

            entity3.setProcessInstanceId(id2);
            entity3.setType("type2");
            config.getCommentDataManager().insert(entity3);

            List<Comment> comments = config.getCommentDataManager().findCommentsByProcessInstanceId(id2);
            assertEquals(2, comments.size());

            comments = config.getCommentDataManager().findCommentsByProcessInstanceId(id1, "type1");
            assertEquals(1, comments.size());
        }
        finally {
            config.getCommentDataManager().delete(entity1);
            config.getCommentDataManager().delete(entity2);
            config.getCommentDataManager().delete(entity3);
        }
    }

    @Test
    public void findComment() throws Exception {
        CommentEntity entity1 = new CommentEntityImpl();
        CommentEntity entity2 = new CommentEntityImpl();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setId(id1);
            entity1.setType(CommentEntity.TYPE_COMMENT);
            config.getCommentDataManager().insert(entity1);

            String id2 = config.getIdGenerator().getNextId();
            entity2.setId(id2);
            entity2.setType(CommentEntity.TYPE_COMMENT);
            config.getCommentDataManager().insert(entity2);

            Comment comment = config.getCommentDataManager().findComment(id2);
            assertNotNull(comment);
            assertEquals(id2, comment.getId());
        }
        finally {
            config.getCommentDataManager().delete(entity1);
            config.getCommentDataManager().delete(entity2);
        }
    }

    @Test
    public void findEvent() throws Exception {
        CommentEntity entity1 = new CommentEntityImpl();
        CommentEntity entity2 = new CommentEntityImpl();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setId(id1);
            entity1.setType(CommentEntity.TYPE_EVENT);
            config.getCommentDataManager().insert(entity1);

            String id2 = config.getIdGenerator().getNextId();
            entity2.setId(id2);
            entity2.setType(CommentEntity.TYPE_EVENT);
            config.getCommentDataManager().insert(entity2);

            Event event = config.getCommentDataManager().findEvent(id2);
            assertNotNull(event);
            assertEquals(id2, event.getId());
        }
        finally {
            config.getCommentDataManager().delete(entity1);
            config.getCommentDataManager().delete(entity2);
        }
    }

}