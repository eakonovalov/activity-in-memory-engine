package org.activiti.ignite;

import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.interceptor.CommandInterceptor;
import org.activiti.engine.impl.persistence.StrongUuidGenerator;
import org.activiti.ignite.manager.*;
import org.activiti.transaction.NoopTransactionContextFactory;
import org.apache.ignite.Ignite;
import org.springframework.beans.factory.annotation.Autowired;

public class IgniteProcessEngineConfiguration extends ProcessEngineConfigurationImpl {

    @Autowired
    private ExecutionDataManager localExecutionDataManager;
    @Autowired
    private TaskDataManager localTaskDataManager;
    @Autowired
    private IdentityLinkDataManager localIdentityLinkDataManager;
    @Autowired
    private HistoricProcessInstanceDataManager localHistoricProcessInstanceDataManager;
    @Autowired
    private HistoricActivityInstanceDataManager localHistoricActivityInstanceDataManager;
    @Autowired
    private HistoricTaskInstanceDataManager localHistoricTaskInstanceDataManager;
    @Autowired
    private ProcessDefinitionDataManager localProcessDefinitionDataManager;
    @Autowired
    private ProcessDefinitionInfoDataManager localProcessDefinitionInfoDataManager;
    @Autowired
    private TimerJobEntityManager localTimerJobEntityManager;
    @Autowired
    private VariableInstanceDataManager localVariableInstanceDataManager;
    @Autowired
    private JobDataManager localJobDataManager;
    @Autowired
    private SuspendedJobEntityManager localSuspendedJobEntityManager;
    @Autowired
    private DeadLetterJobEntityManager localDeadLetterJobEntityManager;
    @Autowired
    private EventSubscriptionDataManager localEventSubscriptionDataManager;
    @Autowired
    private DeploymentDataManager localDeploymentDataManager;
    @Autowired
    private ResourceDataManager localResourceDataManager;
    @Autowired
    private PropertyDataManager localPropertyDataManager;

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

    @Override
    public void initDataManagers() {
        this.deploymentDataManager = localDeploymentDataManager;
        this.resourceDataManager = localResourceDataManager;
        this.processDefinitionDataManager = localProcessDefinitionDataManager;
        this.jobDataManager = localJobDataManager;
        this.executionDataManager = localExecutionDataManager;
        this.historicProcessInstanceDataManager = localHistoricProcessInstanceDataManager;
        this.historicActivityInstanceDataManager = localHistoricActivityInstanceDataManager;
        this.taskDataManager = localTaskDataManager;
        this.historicTaskInstanceDataManager = localHistoricTaskInstanceDataManager;
        this.identityLinkDataManager = localIdentityLinkDataManager;
        this.variableInstanceDataManager = localVariableInstanceDataManager;
        this.eventSubscriptionDataManager = localEventSubscriptionDataManager;
        this.propertyDataManager = localPropertyDataManager;
        this.timerJobEntityManager = localTimerJobEntityManager;
        this.processDefinitionInfoDataManager = localProcessDefinitionInfoDataManager;
        this.suspendedJobEntityManager = localSuspendedJobEntityManager;
        this.deadLetterJobEntityManager = localDeadLetterJobEntityManager;
    }

    public Ignite getIgnite() {
        return ignite;
    }

    public void setIgnite(Ignite ignite) {
        this.ignite = ignite;
    }

}
