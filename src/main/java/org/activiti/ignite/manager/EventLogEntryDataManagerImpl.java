package org.activiti.ignite.manager;

import org.activiti.engine.event.EventLogEntry;
import org.activiti.engine.impl.persistence.entity.EventLogEntryEntity;
import org.activiti.engine.impl.persistence.entity.EventLogEntryEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.EventLogEntryDataManager;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.Iterator;
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
        return new ArrayList<>(findAll());
    }

    @Override
    public List<EventLogEntry> findEventLogEntries(long startLogNr, long pageSize) {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setSelectClause("*");
        queryBuilder.setFromClause("EventLogEntryEntityImpl");
        queryBuilder.setOrderByClause("logNumber");
        queryBuilder.appendCondition("logNumber >= ?");
        queryBuilder.appendArgs(startLogNr);
        SqlFieldsQuery qry = new SqlFieldsQuery(queryBuilder.getQuery()).setArgs(queryBuilder.getArgs().toArray());
        Iterator<List<?>> itr = getCache().query(qry).iterator();
        List<EventLogEntry> result = new ArrayList<>();
        while (itr.hasNext() && result.size() < pageSize) {
            List<?> o = itr.next();
            result.add((EventLogEntry) o.get(1));
        }

        return result;
    }

    @Override
    public List<EventLogEntry> findEventLogEntriesByProcessInstanceId(String processInstanceId) {
        return new ArrayList<>(findList(EventLogEntryEntityImpl.class, "processInstanceId = ?", processInstanceId));
    }

    @Override
    public void deleteEventLogEntry(long logNr) {
        removeList(EventLogEntryEntityImpl.class, "logNumber = ?", logNr);
    }

}
