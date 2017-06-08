package org.activiti.ignite.manager;

import org.activiti.engine.event.EventLogEntry;
import org.activiti.engine.impl.persistence.entity.EventLogEntryEntity;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ekonovalov on 08.06.2017.
 */
public class EventLogEntryDataManagerImplTest extends AbstractDataManagerImplTest {

    @Test
    public void findAllEventLogEntries() throws Exception {
        EventLogEntryEntity entity1 = config.getEventLogEntryDataManager().create();
        EventLogEntryEntity entity2 = config.getEventLogEntryDataManager().create();
        try {
            config.getEventLogEntryDataManager().insert(entity1);
            config.getEventLogEntryDataManager().insert(entity2);

            List<EventLogEntry> entries = config.getEventLogEntryDataManager().findAllEventLogEntries();
            assertTrue(entries.size() == 2);
        } finally {
            config.getEventLogEntryDataManager().delete(entity1);
            config.getEventLogEntryDataManager().delete(entity2);
        }
    }

    @Test
    public void findEventLogEntries() throws Exception {
        try {
            for(int i = 0; i < 10; i++) {
                EventLogEntryEntity entity = config.getEventLogEntryDataManager().create();
                entity.setLogNumber(i);
                config.getEventLogEntryDataManager().insert(entity);
            }

            List<EventLogEntry> entries = config.getEventLogEntryDataManager().findEventLogEntries(3, 5);
            assertTrue(entries.size() == 5);

            for(int i = 0; i < entries.size(); i++) {
                assertEquals(i + 3, entries.get(i).getLogNumber());
            }
        } finally {
            config.getEventLogEntryDataManager().findAllEventLogEntries().forEach(e -> config.getEventLogEntryDataManager().deleteEventLogEntry(e.getLogNumber()));
        }
    }

    @Test
    public void findEventLogEntriesByProcessInstanceId() throws Exception {
        EventLogEntryEntity entity1 = config.getEventLogEntryDataManager().create();
        EventLogEntryEntity entity2 = config.getEventLogEntryDataManager().create();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setProcessInstanceId(id1);
            config.getEventLogEntryDataManager().insert(entity1);

            String id2 = config.getIdGenerator().getNextId();
            entity2.setProcessInstanceId(id2);
            config.getEventLogEntryDataManager().insert(entity2);

            List<EventLogEntry> entries = config.getEventLogEntryDataManager().findEventLogEntriesByProcessInstanceId(id1);
            assertTrue(entries.size() == 1);
        } finally {
            config.getEventLogEntryDataManager().delete(entity1);
            config.getEventLogEntryDataManager().delete(entity2);
        }
    }

    @Test
    public void deleteEventLogEntry() throws Exception {
        EventLogEntryEntity entity1 = config.getEventLogEntryDataManager().create();
        EventLogEntryEntity entity2 = config.getEventLogEntryDataManager().create();
        try {
            entity1.setLogNumber(1);
            config.getEventLogEntryDataManager().insert(entity1);

            entity2.setLogNumber(2);
            config.getEventLogEntryDataManager().insert(entity2);

            List<EventLogEntry> entries = config.getEventLogEntryDataManager().findAllEventLogEntries();
            assertTrue(entries.size() == 2);

            config.getEventLogEntryDataManager().deleteEventLogEntry(2);

            entries = config.getEventLogEntryDataManager().findAllEventLogEntries();
            assertTrue(entries.size() == 1);
            assertEquals(1, entries.get(0).getLogNumber());
        } finally {
            config.getEventLogEntryDataManager().delete(entity1);
            config.getEventLogEntryDataManager().delete(entity2);
        }
    }

}