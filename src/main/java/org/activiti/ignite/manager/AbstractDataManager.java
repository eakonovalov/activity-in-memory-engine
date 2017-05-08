package org.activiti.ignite.manager;

import org.activiti.engine.impl.persistence.AbstractManager;
import org.activiti.engine.impl.persistence.entity.Entity;
import org.activiti.engine.impl.persistence.entity.data.DataManager;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.configuration.CacheConfiguration;

/**
 * Created by ekonovalov on 26.04.2017.
 */
public abstract class AbstractDataManager<E extends Entity> extends AbstractManager implements DataManager<E> {

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

}
