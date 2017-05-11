package org.activiti.ignite.manager;

import org.activiti.engine.impl.persistence.AbstractManager;
import org.activiti.engine.impl.persistence.entity.*;
import org.activiti.engine.impl.persistence.entity.data.DataManager;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.CacheConfiguration;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public abstract class AbstractDataManager<E extends Entity, I extends E> extends AbstractManager implements DataManager<E> {

    private IgniteProcessEngineConfiguration processEngineConfiguration;
    private IgniteCache<String, E> cache;

    public AbstractDataManager(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
        this.processEngineConfiguration = processEngineConfiguration;
    }

    public E findById(String entityId) {
        if (entityId == null) {
            return null;
        }

        return getCache().get(entityId);
    }

    public void insert(E entity) {
        if (entity.getId() == null) {
            entity.setId(getProcessEngineConfiguration().getIdGenerator().getNextId());
        }
        getCache().put(entity.getId(), entity);
    }

    public void insert(E entity, boolean fireCreateEvent) {
        insert(entity);
    }

    public E update(E entity) {
        E matchingEntity = getCache().get(entity.getId());
        if (matchingEntity != null) {
            getCache().put(entity.getId(), entity);
            return entity;
        } else {
            return null;
        }
    }

    public E update(E entity, boolean fireUpdateEvent) {
        return update(entity);
    }

    public void delete(String id) {
        cache.remove(id);
    }

    public void delete(E entity) {
        delete(entity.getId());
    }

    public void delete(E entity, boolean fireDeleteEvent) {
        delete(entity);
    }

    public IgniteCache<String, E> getCache() {
        if (cache == null) {
            cache = processEngineConfiguration.getIgnite().getOrCreateCache(getConfig());
        }
        return cache;
    }

    protected abstract CacheConfiguration<String, E> getConfig();

    public List<E> findList(Class<I> clazz, String query, Object... args) {
        List<Cache.Entry<String, I>> list = getCache().query(new SqlQuery<String, I>(clazz, query).setArgs(args)).getAll();
        List<E> result = new ArrayList<>();
        for (Cache.Entry<String, I> entry : list) {
            result.add(entry.getValue());
        }

        return result;
    }

    public E findOne(Class<I> clazz, String query, Object... args) {
        List<Cache.Entry<String, I>> list = getCache().query(new SqlQuery<String, I>(clazz, query).setArgs(args)).getAll();
        if(list.size() > 0) throw new RuntimeException("Query fetched more than one object");
        return list.size() > 0 ? list.get(0).getValue() : null;
    }

    public List<E> findAll() {
        List<E> result = new ArrayList<>();
        getCache().forEach(e -> result.add(e.getValue()));

        return result;
    }

}
