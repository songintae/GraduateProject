package graduate.daoTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import graduate.instagram.InstaService;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class GetDataTest {
	
	@Autowired
	InstaService service;
	
	@Test
	public void httpdaoTest(){
		service.GetAndRegistryData("https://api.instagram.com/v1/users/self/media/recent/?access_token=2286401760.52879a7.2fcf17a37942461da015116ed66b2e5d");
	}
}
