package org.activiti.ignite.manager;

import org.activiti.engine.impl.DeploymentQueryImpl;
import org.activiti.engine.impl.persistence.entity.DeploymentEntity;
import org.activiti.engine.impl.persistence.entity.DeploymentEntityImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntityImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ekonovalov on 31.05.2017.
 */
public class DeploymentDataManagerImplTest extends AbstractDataManagerImplTest {

    @Test
    public void findDeploymentByIdQueryCriteria() throws Exception {
        DeploymentEntity entity1 = new DeploymentEntityImpl();
        String id1 = config.getIdGenerator().getNextId();
        entity1.setId(id1);
        config.getDeploymentDataManager().insert(entity1);

        long count = config.getDeploymentDataManager().findDeploymentCountByQueryCriteria((DeploymentQueryImpl) processEngine.getRepositoryService().createDeploymentQuery().deploymentId(id1));
        assertTrue(count == 1);

        config.getDeploymentDataManager().delete(entity1);
    }

    @Test
    public void findDeploymentByNameQueryCriteria() throws Exception {
        DeploymentEntity entity1 = new DeploymentEntityImpl();
        String id1 = config.getIdGenerator().getNextId();
        entity1.setName(id1);
        config.getDeploymentDataManager().insert(entity1);

        DeploymentEntity entity2 = new DeploymentEntityImpl();
        String id2 = config.getIdGenerator().getNextId();
        entity1.setName(id2);
        config.getDeploymentDataManager().insert(entity2);

        long count = config.getDeploymentDataManager().findDeploymentCountByQueryCriteria((DeploymentQueryImpl) processEngine.getRepositoryService().createDeploymentQuery().deploymentName(id1));
        assertTrue(count == 1);

        config.getDeploymentDataManager().delete(entity1);
        config.getDeploymentDataManager().delete(entity2);
    }

    @Test
    public void findDeploymentByNameLikeQueryCriteria() throws Exception {
        DeploymentEntity entity1 = new DeploymentEntityImpl();
        String id1 = config.getIdGenerator().getNextId();
        entity1.setName(id1);
        config.getDeploymentDataManager().insert(entity1);

        DeploymentEntity entity2 = new DeploymentEntityImpl();
        String id2 = config.getIdGenerator().getNextId();
        entity1.setName(id2);
        config.getDeploymentDataManager().insert(entity2);

        long count = config.getDeploymentDataManager().findDeploymentCountByQueryCriteria((DeploymentQueryImpl) processEngine.getRepositoryService().createDeploymentQuery().deploymentNameLike(id1.substring(3)));
        assertTrue(count == 1);

        config.getDeploymentDataManager().delete(entity1);
        config.getDeploymentDataManager().delete(entity2);
    }

    @Test
    public void findDeploymentByProcessDefinitionKeyQueryCriteria() throws Exception {
        DeploymentEntity entity1 = new DeploymentEntityImpl();
        String id1 = config.getIdGenerator().getNextId();
        entity1.setId(id1);
        config.getDeploymentDataManager().insert(entity1);

        ProcessDefinitionEntity entity2 = new ProcessDefinitionEntityImpl();
        String id2 = config.getIdGenerator().getNextId();
        entity2.setKey(id2);
        entity2.setDeploymentId(id1);
        config.getProcessDefinitionDataManager().insert(entity2);

        long count = config.getDeploymentDataManager().findDeploymentCountByQueryCriteria((DeploymentQueryImpl) processEngine.getRepositoryService().createDeploymentQuery().processDefinitionKey(id2));
        assertTrue(count == 1);

        config.getProcessDefinitionDataManager().delete(entity2);
        config.getDeploymentDataManager().delete(entity1);
    }

}
