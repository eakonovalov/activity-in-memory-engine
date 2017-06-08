package org.activiti.ignite;

import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.interceptor.CommandInterceptor;
import org.activiti.engine.impl.persistence.StrongUuidGenerator;
import org.activiti.engine.impl.persistence.entity.data.*;
import org.activiti.transaction.NoopTransactionContextFactory;
import org.apache.ignite.Ignite;
import org.springframework.beans.factory.annotation.Autowired;

public class IgniteProcessEngineConfiguration extends ProcessEngineConfigurationImpl {

    private Ignite ignite;

    public IgniteProcessEngineConfiguration() {
        this.usingRelationalDatabase = false;
        this.idGenerator = new StrongUuidGenerator();
    }

    @Override
    public CommandInterceptor createTransactionInterceptor() {
        return null;
    }

    @Override
    public void initTransactionContextFactory() {
        if (transactionContextFactory == null) {
            transactionContextFactory = new NoopTransactionContextFactory();
        }
    }

    public Ignite getIgnite() {
        return ignite;
    }

    public void setIgnite(Ignite ignite) {
        this.ignite = ignite;
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setIdentityLinkDataManager(IdentityLinkDataManager identityLinkDataManager) {
        return super.setIdentityLinkDataManager(identityLinkDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setIdentityInfoDataManager(IdentityInfoDataManager identityInfoDataManager) {
        return super.setIdentityInfoDataManager(identityInfoDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setHistoricProcessInstanceDataManager(HistoricProcessInstanceDataManager historicProcessInstanceDataManager) {
        return super.setHistoricProcessInstanceDataManager(historicProcessInstanceDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setHistoricActivityInstanceDataManager(HistoricActivityInstanceDataManager historicActivityInstanceDataManager) {
        return super.setHistoricActivityInstanceDataManager(historicActivityInstanceDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setHistoricTaskInstanceDataManager(HistoricTaskInstanceDataManager historicTaskInstanceDataManager) {
        return super.setHistoricTaskInstanceDataManager(historicTaskInstanceDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setHistoricVariableInstanceDataManager(HistoricVariableInstanceDataManager historicVariableInstanceDataManager) {
        return super.setHistoricVariableInstanceDataManager(historicVariableInstanceDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setHistoricDetailDataManager(HistoricDetailDataManager historicDetailDataManager) {
        return super.setHistoricDetailDataManager(historicDetailDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setHistoricIdentityLinkDataManager(HistoricIdentityLinkDataManager historicIdentityLinkDataManager) {
        return super.setHistoricIdentityLinkDataManager(historicIdentityLinkDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setProcessDefinitionDataManager(ProcessDefinitionDataManager processDefinitionDataManager) {
        return super.setProcessDefinitionDataManager(processDefinitionDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setProcessDefinitionInfoDataManager(ProcessDefinitionInfoDataManager processDefinitionInfoDataManager) {
        return super.setProcessDefinitionInfoDataManager(processDefinitionInfoDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setTimerJobDataManager(TimerJobDataManager timerJobDataManager) {
        return super.setTimerJobDataManager(timerJobDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setVariableInstanceDataManager(VariableInstanceDataManager variableInstanceDataManager) {
        return super.setVariableInstanceDataManager(variableInstanceDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setJobDataManager(JobDataManager jobDataManager) {
        return super.setJobDataManager(jobDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setMembershipDataManager(MembershipDataManager membershipDataManager) {
        return super.setMembershipDataManager(membershipDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setModelDataManager(ModelDataManager modelDataManager) {
        return super.setModelDataManager(modelDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setSuspendedJobDataManager(SuspendedJobDataManager suspendedJobDataManager) {
        return super.setSuspendedJobDataManager(suspendedJobDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setDeadLetterJobDataManager(DeadLetterJobDataManager deadLetterJobDataManager) {
        return super.setDeadLetterJobDataManager(deadLetterJobDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setEventSubscriptionDataManager(EventSubscriptionDataManager eventSubscriptionDataManager) {
        return super.setEventSubscriptionDataManager(eventSubscriptionDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setEventLogEntryDataManager(EventLogEntryDataManager eventLogEntryDataManager) {
        return super.setEventLogEntryDataManager(eventLogEntryDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setResourceDataManager(ResourceDataManager resourceDataManager) {
        return super.setResourceDataManager(resourceDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setPropertyDataManager(PropertyDataManager propertyDataManager) {
        return super.setPropertyDataManager(propertyDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setDeploymentDataManager(DeploymentDataManager deploymentDataManager) {
        return super.setDeploymentDataManager(deploymentDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setExecutionDataManager(ExecutionDataManager executionDataManager) {
        return super.setExecutionDataManager(executionDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setTaskDataManager(TaskDataManager taskDataManager) {
        return super.setTaskDataManager(taskDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setAttachmentDataManager(AttachmentDataManager attachmentDataManager) {
        return super.setAttachmentDataManager(attachmentDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setByteArrayDataManager(ByteArrayDataManager byteArrayDataManager) {
        return super.setByteArrayDataManager(byteArrayDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setCommentDataManager(CommentDataManager commentDataManager) {
        return super.setCommentDataManager(commentDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setGroupDataManager(GroupDataManager groupDataManager) {
        return super.setGroupDataManager(groupDataManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setUserDataManager(UserDataManager userDataManager) {
        return super.setUserDataManager(userDataManager);
    }

}
