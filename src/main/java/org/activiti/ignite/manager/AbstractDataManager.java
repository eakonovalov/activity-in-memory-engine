package org.activiti.ignite.manager;

import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.AbstractManager;
import org.activiti.engine.impl.persistence.entity.Entity;
import org.activiti.engine.impl.persistence.entity.data.DataManager;
import org.apache.ignite.IgniteCache;

public abstract class AbstractDataManager<E extends Entity> extends AbstractManager implements DataManager<E> {

    protected IgniteCache<String, E> cache;

    public AbstractDataManager(ProcessEngineConfigurationImpl processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    public E findById(String entityId) {
        if (entityId == null) {
            return null;
        }

        return cache.get(entityId);
    }

    public void insert(E entity) {
        if (entity.getId() == null) {
            entity.setId(getProcessEngineConfiguration().getIdGenerator().getNextId());
        }
        cache.put(entity.getId(), entity);
    }

    public void insert(E entity, boolean fireCreateEvent) {
        insert(entity);
    }

    public E update(E entity) {
        E matchingEntity = cache.get(entity.getId());
        if (matchingEntity != null) {
            cache.put(entity.getId(), entity);
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

}
