package org.activiti.inmemory;

import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.interceptor.CommandInterceptor;
import org.activiti.engine.impl.persistence.StrongUuidGenerator;
import org.activiti.inmemory.manager.*;
import org.activiti.transaction.NoopTransactionContextFactory;

public class InMemoryProcessEngineConfiguration extends ProcessEngineConfigurationImpl {

    public InMemoryProcessEngineConfiguration() {
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

    @Override
    public void initDataManagers() {
        this.deploymentDataManager = new DeploymentDataManager(this);
        this.resourceDataManager = new ResourceDataManager(this);
        this.processDefinitionDataManager = new ProcessDefinitionDataManager(this);
        this.jobDataManager = new JobDataManager(this);
        this.executionDataManager = new ExecutionDataManager(this);
        this.historicProcessInstanceDataManager = new HistoricProcessInstanceDataManager(this);
        this.historicActivityInstanceDataManager = new HistoricActivityInstanceDataManager(this);
        this.taskDataManager = new TaskDataManager(this);
        this.historicTaskInstanceDataManager = new HistoricTaskInstanceDataManager(this);
        this.identityLinkDataManager = new IdentityLinkDataManager(this);
        this.variableInstanceDataManager = new VariableInstanceDataManager(this);
        this.eventSubscriptionDataManager = new EventSubscriptionDataManager(this);
        this.propertyDataManager = new PropertyDataManager(this);
        this.timerJobEntityManager = new TimerJobEntityManager(this);
        this.processDefinitionInfoDataManager = new ProcessDefinitionInfoDataManager(this);
        this.suspendedJobEntityManager = new SuspendedJobEntityManager(this);
        this.deadLetterJobEntityManager = new DeadLetterJobEntityManager(this);
    }

}
