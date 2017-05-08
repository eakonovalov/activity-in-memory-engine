package org.activiti.ignite;

import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.interceptor.CommandInterceptor;
import org.activiti.engine.impl.persistence.StrongUuidGenerator;
import org.activiti.engine.impl.persistence.entity.DeadLetterJobEntityManager;
import org.activiti.engine.impl.persistence.entity.SuspendedJobEntityManager;
import org.activiti.engine.impl.persistence.entity.TimerJobEntityManager;
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
    public ProcessEngineConfigurationImpl setTimerJobEntityManager(TimerJobEntityManager timerJobEntityManager) {
        return super.setTimerJobEntityManager(timerJobEntityManager);
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
    public ProcessEngineConfigurationImpl setSuspendedJobEntityManager(SuspendedJobEntityManager suspendedJobEntityManager) {
        return super.setSuspendedJobEntityManager(suspendedJobEntityManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setDeadLetterJobEntityManager(DeadLetterJobEntityManager deadLetterJobEntityManager) {
        return super.setDeadLetterJobEntityManager(deadLetterJobEntityManager);
    }

    @Override
    @Autowired
    public ProcessEngineConfigurationImpl setEventSubscriptionDataManager(EventSubscriptionDataManager eventSubscriptionDataManager) {
        return super.setEventSubscriptionDataManager(eventSubscriptionDataManager);
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

}
