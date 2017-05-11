package org.activiti.ignite.manager;

import org.activiti.engine.impl.persistence.entity.AttachmentEntity;
import org.activiti.engine.impl.persistence.entity.AttachmentEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.AttachmentDataManager;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by ekonovalov on 11.05.2017.
 */
public class AttachmentDataManagerImpl extends AbstractDataManager<AttachmentEntity, AttachmentEntityImpl> implements AttachmentDataManager {

    @Autowired
    @Qualifier("attachmentEntityCache")
    private CacheConfiguration<String, AttachmentEntity> config;

    public AttachmentDataManagerImpl(IgniteProcessEngineConfiguration processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    protected CacheConfiguration<String, AttachmentEntity> getConfig() {
        return config;
    }

    @Override
    public AttachmentEntity create() {
        return new AttachmentEntityImpl();
    }

    @Override
    public List<AttachmentEntity> findAttachmentsByProcessInstanceId(String processInstanceId) {
        return findList(AttachmentEntityImpl.class, "processInstanceId = ?", processInstanceId);
    }

    @Override
    public List<AttachmentEntity> findAttachmentsByTaskId(String taskId) {
        return findList(AttachmentEntityImpl.class, "taskId = ?", taskId);
    }

}
