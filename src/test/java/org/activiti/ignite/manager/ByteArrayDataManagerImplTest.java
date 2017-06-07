package org.activiti.ignite.manager;

import org.activiti.engine.impl.persistence.entity.ByteArrayEntity;
import org.activiti.engine.impl.persistence.entity.ByteArrayEntityImpl;
import org.activiti.engine.task.Comment;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ekonovalov on 07.06.2017.
 */
public class ByteArrayDataManagerImplTest extends AbstractDataManagerImplTest {

    @Test
    public void deleteByteArrayNoRevisionCheck() throws Exception {
        ByteArrayEntity entity1 = config.getByteArrayDataManager().create();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setId(id1);
            config.getByteArrayDataManager().insert(entity1);

            ByteArrayEntity entity = config.getByteArrayDataManager().findById(id1);
            assertNotNull(entity);

            config.getByteArrayDataManager().deleteByteArrayNoRevisionCheck(id1);

            entity = config.getByteArrayDataManager().findById(id1);
            assertNull(entity);
        }
        finally {
            config.getByteArrayDataManager().delete(entity1);
        }
    }

}