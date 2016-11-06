package graduate.dao.cluster;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import graduate.domain.cluster.Area;
import graduate.domain.cluster.Cluster;
import graduate.domain.cluster.Attribute;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class ClusterDaoTest {
	
	
	
	@Autowired
	ClusterDao clusterDao;
	
	@Autowired
	AttributeDao attributeDao;
	
	
	@Test
	public void clusterGetAllTest(){
		
		List<Cluster> clusters = clusterDao.getAll();
		assertThat(clusters.size() , is(63));
		
		
	}
	
	@Test
	public void attributeGetAllTest(){
		
		List<Attribute> attributes = attributeDao.getAll();
		assertThat(attributes.size() , is(1854));
		
	}
	
	@Test
	public void clusterGetTest(){
		List<Cluster> clusters = clusterDao.get(12);
		assertThat(clusters.size() , is(0));
		
		clusters = clusterDao.get(11);
		assertThat(clusters.size() , is(20));
	}
	
	@Test
	public void attributeGetTest(){

		List<Attribute> attributes = attributeDao.get(12);
		assertThat(attributes.size() , is(0));
		
		attributes = attributeDao.get(11);
		assertThat(attributes.size(),is(833));
	}
	
	
	

}
