package org.activiti.ignite.manager;

import org.activiti.engine.impl.DeploymentQueryImpl;
import org.activiti.engine.impl.persistence.entity.*;
import org.activiti.engine.repository.Deployment;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ekonovalov on 31.05.2017.
 */
public class DeploymentDataManagerImplTest extends AbstractDataManagerImplTest {

    @Test
    public void findDeploymentByIdQueryCriteria() throws Exception {
        DeploymentEntity entity1 = config.getDeploymentDataManager().create();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setId(id1);
            config.getDeploymentDataManager().insert(entity1);

            List<Deployment> deployments = config.getDeploymentDataManager().findDeploymentsByQueryCriteria((DeploymentQueryImpl) processEngine.getRepositoryService().createDeploymentQuery().deploymentId(id1), null);
            assertTrue(deployments.size() == 1);
            assertTrue(deployments.get(0) instanceof DeploymentEntityImpl);

            long count = config.getDeploymentDataManager().findDeploymentCountByQueryCriteria((DeploymentQueryImpl) processEngine.getRepositoryService().createDeploymentQuery().deploymentId(id1));
            assertTrue(count == 1);
        } finally {
            config.getDeploymentDataManager().delete(entity1);
        }
    }

    @Test
    public void findDeploymentByNameQueryCriteria() throws Exception {
        DeploymentEntity entity1 = config.getDeploymentDataManager().create();
        DeploymentEntity entity2 = config.getDeploymentDataManager().create();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setName(id1);
            config.getDeploymentDataManager().insert(entity1);

            String id2 = config.getIdGenerator().getNextId();
            entity1.setName(id2);
            config.getDeploymentDataManager().insert(entity2);

            List<Deployment> deployments = config.getDeploymentDataManager().findDeploymentsByQueryCriteria((DeploymentQueryImpl) processEngine.getRepositoryService().createDeploymentQuery().deploymentName(id1), null);
            assertTrue(deployments.size() == 1);
            assertTrue(deployments.get(0) instanceof DeploymentEntityImpl);

            long count = config.getDeploymentDataManager().findDeploymentCountByQueryCriteria((DeploymentQueryImpl) processEngine.getRepositoryService().createDeploymentQuery().deploymentName(id1));
            assertTrue(count == 1);
        } finally {
            config.getDeploymentDataManager().delete(entity1);
            config.getDeploymentDataManager().delete(entity2);
        }
    }

    @Test
    public void findDeploymentByNameLikeQueryCriteria() throws Exception {
        DeploymentEntity entity1 = config.getDeploymentDataManager().create();
        DeploymentEntity entity2 = config.getDeploymentDataManager().create();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setName(id1);
            config.getDeploymentDataManager().insert(entity1);

            String id2 = config.getIdGenerator().getNextId();
            entity1.setName(id2);
            config.getDeploymentDataManager().insert(entity2);

            List<Deployment> deployments = config.getDeploymentDataManager().findDeploymentsByQueryCriteria((DeploymentQueryImpl) processEngine.getRepositoryService().createDeploymentQuery().deploymentNameLike(id1.substring(3)), null);
            assertTrue(deployments.size() == 1);
            assertTrue(deployments.get(0) instanceof DeploymentEntityImpl);

            long count = config.getDeploymentDataManager().findDeploymentCountByQueryCriteria((DeploymentQueryImpl) processEngine.getRepositoryService().createDeploymentQuery().deploymentNameLike(id1.substring(3)));
            assertTrue(count == 1);
        } finally {
            config.getDeploymentDataManager().delete(entity1);
            config.getDeploymentDataManager().delete(entity2);
        }
    }

    @Test
    public void findDeploymentByCategoryQueryCriteria() throws Exception {
        DeploymentEntity entity1 = config.getDeploymentDataManager().create();
        DeploymentEntity entity2 = config.getDeploymentDataManager().create();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setCategory(id1);
            config.getDeploymentDataManager().insert(entity1);

            String id2 = config.getIdGenerator().getNextId();
            entity1.setCategory(id2);
            config.getDeploymentDataManager().insert(entity2);

            List<Deployment> deployments = config.getDeploymentDataManager().findDeploymentsByQueryCriteria((DeploymentQueryImpl) processEngine.getRepositoryService().createDeploymentQuery().deploymentCategory(id1), null);
            assertTrue(deployments.size() == 1);
            assertTrue(deployments.get(0) instanceof DeploymentEntityImpl);

            long count = config.getDeploymentDataManager().findDeploymentCountByQueryCriteria((DeploymentQueryImpl) processEngine.getRepositoryService().createDeploymentQuery().deploymentCategory(id1));
            assertTrue(count == 1);
        } finally {
            config.getDeploymentDataManager().delete(entity1);
            config.getDeploymentDataManager().delete(entity2);
        }
    }

    @Test
    @Ignore
    public void findDeploymentByCategoryNotEqualsQueryCriteria() throws Exception {
        DeploymentEntity entity1 = config.getDeploymentDataManager().create();
        DeploymentEntity entity2 = config.getDeploymentDataManager().create();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setCategory(id1);
            config.getDeploymentDataManager().insert(entity1);

            String id2 = config.getIdGenerator().getNextId();
            entity1.setCategory(id2);
            config.getDeploymentDataManager().insert(entity2);

            List<Deployment> deployments = config.getDeploymentDataManager().findDeploymentsByQueryCriteria((DeploymentQueryImpl) processEngine.getRepositoryService().createDeploymentQuery().deploymentCategoryNotEquals(id1), null);
            assertTrue(deployments.size() == 1);
            assertTrue(deployments.get(0) instanceof DeploymentEntityImpl);

            long count = config.getDeploymentDataManager().findDeploymentCountByQueryCriteria((DeploymentQueryImpl) processEngine.getRepositoryService().createDeploymentQuery().deploymentCategoryNotEquals(id1));
            assertTrue(count == 1);
        } finally {
            config.getDeploymentDataManager().delete(entity1);
            config.getDeploymentDataManager().delete(entity2);
        }
    }

    @Test
    public void findDeploymentByProcessDefinitionKeyQueryCriteria() throws Exception {
        DeploymentEntity entity1 = config.getDeploymentDataManager().create();
        ProcessDefinitionEntity entity2 = new ProcessDefinitionEntityImpl();
        try {
            String id1 = config.getIdGenerator().getNextId();
            entity1.setId(id1);
            config.getDeploymentDataManager().insert(entity1);

            String id2 = config.getIdGenerator().getNextId();
            entity2.setKey(id2);
            entity2.setDeploymentId(id1);
            config.getProcessDefinitionDataManager().insert(entity2);

            long count = config.getDeploymentDataManager().findDeploymentCountByQueryCriteria((DeploymentQueryImpl) processEngine.getRepositoryService().createDeploymentQuery().processDefinitionKey(id2));
            assertTrue(count == 1);
        } finally {
            config.getProcessDefinitionDataManager().delete(entity2);
            config.getDeploymentDataManager().delete(entity1);
        }
    }

    @Test
    public void getDeploymentResourceNames() throws Exception {
        DeploymentEntity deployment1 = config.getDeploymentDataManager().create();
        DeploymentEntity deployment2 = config.getDeploymentDataManager().create();
        ResourceEntity entity1 = new ResourceEntityImpl();
        ResourceEntity entity2 = new ResourceEntityImpl();
        try {
            String id1 = config.getIdGenerator().getNextId();
            deployment1.setId(id1);
            config.getDeploymentDataManager().insert(deployment1);

            String id2 = config.getIdGenerator().getNextId();
            deployment2.setId(id2);
            config.getDeploymentDataManager().insert(deployment2);

            entity1.setDeploymentId(id1);
            entity1.setName("1");
            config.getResourceDataManager().insert(entity1);
            entity2.setDeploymentId(id2);
            entity2.setName("2");
            config.getResourceDataManager().insert(entity2);

            List<String> names = config.getDeploymentDataManager().getDeploymentResourceNames(id1);
            assertEquals(1, names.size());
            assertEquals("1", names.get(0));
        } finally {
            config.getResourceDataManager().delete(entity1);
            config.getResourceDataManager().delete(entity2);
            config.getDeploymentDataManager().delete(deployment1);
            config.getDeploymentDataManager().delete(deployment2);
        }
    }

}
