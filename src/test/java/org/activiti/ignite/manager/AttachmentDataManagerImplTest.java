package org.activiti.ignite.manager;

import org.activiti.engine.impl.persistence.entity.AttachmentEntity;
import org.activiti.engine.impl.persistence.entity.AttachmentEntityImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ekonovalov on 07.06.2017.
 */
public class AttachmentDataManagerImplTest extends AbstractDataManagerImplTest {

    @Test
    public void findAttachmentsByProcessInstanceId() throws Exception {
        AttachmentEntity entity1 = new AttachmentEntityImpl();
        AttachmentEntity entity2 = new AttachmentEntityImpl();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setProcessInstanceId(id1);
            config.getAttachmentDataManager().insert(entity1);

            String id2 = config.getIdGenerator().getNextId();
            entity2.setProcessInstanceId(id2);
            config.getAttachmentDataManager().insert(entity2);

            List<AttachmentEntity> events = config.getAttachmentDataManager().findAttachmentsByProcessInstanceId(id2);
            assertEquals(1, events.size());
        }
        finally {
            config.getAttachmentDataManager().delete(entity1);
            config.getAttachmentDataManager().delete(entity2);
        }
    }

    @Test
    public void findAttachmentsByTaskId() throws Exception {
        AttachmentEntity entity1 = new AttachmentEntityImpl();
        AttachmentEntity entity2 = new AttachmentEntityImpl();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setTaskId(id1);
            config.getAttachmentDataManager().insert(entity1);

            String id2 = config.getIdGenerator().getNextId();
            entity2.setTaskId(id2);
            config.getAttachmentDataManager().insert(entity2);

            List<AttachmentEntity> events = config.getAttachmentDataManager().findAttachmentsByTaskId(id2);
            assertEquals(1, events.size());
        }
        finally {
            config.getAttachmentDataManager().delete(entity1);
            config.getAttachmentDataManager().delete(entity2);
        }
    }

}