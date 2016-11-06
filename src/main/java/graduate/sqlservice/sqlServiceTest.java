package graduate.sqlservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import graduate.dao.ContentDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class sqlServiceTest {

	@Autowired
	SqlReader sqlReader;
	@Autowired
	SqlRegistry sqlRegistry;
	@Autowired
	SqlService sqlService;
	@Autowired
	ContentDao contentDao;
	
	@Test
	public void test(){
		System.out.println("test : "+sqlService.getSql("contentGetAll"));
		
	}
}
