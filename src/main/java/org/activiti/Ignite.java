package org.activiti;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.Ignition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by ekonovalov on 04.05.2017.
 */
public class Ignite {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ignite-config.xml");

        try (org.apache.ignite.Ignite ignite = Ignition.start("examples/config/example-ignite.xml")) {
            IgniteProcessEngineConfiguration config = (IgniteProcessEngineConfiguration) context.getBean("config");
            config.setIgnite(ignite);
            ProcessEngine processEngine = config.buildProcessEngine();

            RepositoryService repositoryService = processEngine.getRepositoryService();
            RuntimeService runtimeService = processEngine.getRuntimeService();
            TaskService taskService = processEngine.getTaskService();
            HistoryService historyService = processEngine.getHistoryService();

            Deployment deployment = repositoryService.createDeployment().addClasspathResource("oneTaskProcess.bpmn20.xml").deploy();
            System.out.println("Process deployed! Deployment id is " + deployment.getId());

            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("oneTaskProcess");
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
            System.out.println("Got " + tasks.size() + " tasks!");

            taskService.complete(tasks.get(0).getId());
            System.out.println("Number of process instances = " + historyService.createHistoricProcessInstanceQuery().count());
            System.out.println("Number of active process instances = " + historyService.createHistoricProcessInstanceQuery().finished().count());
            System.out.println("Number of finished process instances = " + historyService.createHistoricProcessInstanceQuery().unfinished().count());
        }

    }

}
