package graduate.daoTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import graduate.domain.Content;
import graduate.platformdataservice.PlatFormDataService;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class GetDataTest {
	
	@Autowired
	PlatFormDataService service;
	
	@Test
	public void httpdaoTest(){
		List<Content> cList = service.getAllContents();
		System.out.println(cList.get(0));
	}
}
