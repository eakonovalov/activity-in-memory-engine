package org.activiti.ignite.test;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class TestServiceTask implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) {
		System.out.println("Simple service task!!!");
	}

}
