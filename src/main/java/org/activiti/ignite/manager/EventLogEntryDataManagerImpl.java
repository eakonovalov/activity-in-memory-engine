package org.activiti.ignite.manager;

import org.activiti.engine.event.EventLogEntry;
import org.activiti.engine.impl.persistence.entity.EventLogEntryEntity;
import org.activiti.engine.impl.persistence.entity.EventLogEntryEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.EventLogEntryDataManager;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by ekonovalov on 07.06.2017.
 */
public class EventLogEntryDataManagerImpl extends AbstractDataManager<EventLogEntryEntity, EventLogEntryEntityImpl> implements EventLogEntryDataManager {

    @Autowired
    @Qualifier("eventLogEntryEntityCache")
    private CacheConfiguration<String, EventLogEntryEntity> config;

    public EventLogEntryDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, EventLogEntryEntity> getConfig() {
        return config;
    }

    @Override
    public EventLogEntryEntity create() {
        return new EventLogEntryEntityImpl();
    }

    @Override
    public List<EventLogEntry> findAllEventLogEntries() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<EventLogEntry> findEventLogEntries(long startLogNr, long pageSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<EventLogEntry> findEventLogEntriesByProcessInstanceId(String processInstanceId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteEventLogEntry(long logNr) {
        throw new UnsupportedOperationException();
    }

}
