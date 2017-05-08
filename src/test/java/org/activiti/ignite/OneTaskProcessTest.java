package org.activiti.ignite;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by ekonovalov on 09.05.2017.
 */
public class OneTaskProcessTest {

    private static ApplicationContext context = new ClassPathXmlApplicationContext("ignite-config.xml");
    private static Ignite ignite;
    private static ProcessEngine processEngine;

    @BeforeClass
    public static void setup() {
        ignite = Ignition.start("examples/config/example-ignite.xml");
        IgniteProcessEngineConfiguration config = (IgniteProcessEngineConfiguration) context.getBean("config");
        config.setIgnite(ignite);
        processEngine = config.buildProcessEngine();
    }

    @AfterClass
    public static void after() {
        ignite.close();
    }

    @Test
    public void testOneTaskProcessUserTask() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        TaskService taskService = processEngine.getTaskService();
        HistoryService historyService = processEngine.getHistoryService();

        Deployment deployment = repositoryService.createDeployment().addClasspathResource("oneTaskProcess.bpmn20.xml").deploy();
        System.out.println("Process deployed! Deployment id is " + deployment.getId());

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("oneTaskProcess");
        System.out.println("Process started key = " + processInstance.getBusinessKey());
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
        System.out.println("Got " + tasks.size() + " tasks!");

        taskService.complete(tasks.get(0).getId());
        System.out.println("Number of process instances = " + historyService.createHistoricProcessInstanceQuery().count());
        System.out.println("Number of active process instances = " + historyService.createHistoricProcessInstanceQuery().finished().count());
        System.out.println("Number of finished process instances = " + historyService.createHistoricProcessInstanceQuery().unfinished().count());
    }

    @Test
    public void testOneTaskProcessServiceTask() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        HistoryService historyService = processEngine.getHistoryService();

        Deployment deployment = repositoryService.createDeployment().addClasspathResource("oneTaskProcess2.bpmn20.xml").deploy();
        System.out.println("Process deployed! Deployment id is " + deployment.getId());

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("oneTaskProcess2");
        System.out.println("Process started key = " + processInstance.getBusinessKey());

        System.out.println("Number of process instances = " + historyService.createHistoricProcessInstanceQuery().count());
        System.out.println("Number of active process instances = " + historyService.createHistoricProcessInstanceQuery().finished().count());
        System.out.println("Number of finished process instances = " + historyService.createHistoricProcessInstanceQuery().unfinished().count());
    }

}
