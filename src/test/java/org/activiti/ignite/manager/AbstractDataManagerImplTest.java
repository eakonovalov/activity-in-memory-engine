package org.activiti.ignite.manager;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.ignite.IgniteProcessEngineConfiguration;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ekonovalov on 31.05.2017.
 */
public class AbstractDataManagerImplTest {

    protected static ApplicationContext context = new ClassPathXmlApplicationContext("ignite-config.xml");
    protected static Ignite ignite;
    protected static IgniteProcessEngineConfiguration config;
    protected static ProcessEngine processEngine;

    static {
        ignite = Ignition.start("examples/config/example-ignite.xml");
        config = (IgniteProcessEngineConfiguration) context.getBean("config");
        config.setIgnite(ignite);
        processEngine = config.buildProcessEngine();
        Context.setCommandContext(new CommandContext(null, config));
    }

}
