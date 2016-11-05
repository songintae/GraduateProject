package graduate.dao.cluster;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import graduate.domain.cluster.Attribute;
import graduate.domain.cluster.Cluster;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class JdbcClusterDaoTest {
	@Autowired
	ClusterDao clusterDao;
	@Autowired
	AttributeDao attributeDao;
	
//	@Test
	public void getClusterTest(){
		List<Cluster> clusters = clusterDao.getAll();
		System.out.println(clusters.size());
		System.out.println(clusters.get(0).getCluster_id());
		System.out.println(clusters.get(0).getId());
		System.out.println(clusters.get(0).getArea().getArea());
	}
	
	@Test
	public void getAttributeTest(){
		List<Attribute> attributes = attributeDao.getAll();
		System.out.println(attributes.get(0).getTag());
	}
}
