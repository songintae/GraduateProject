package graduate.instagram;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import graduate.dao.ContentDao;
import graduate.dao.TagDao;
import graduate.dao.UserDao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class instagramTest {
	
	@Autowired
	ApplicationContext context;
	@Autowired
	ContentDao contentDao;
	@Autowired
	UserDao userDao;
	
	@Autowired
	TagDao tagDao;
	
	@Autowired
	InstaService instaService;
	@Autowired
	GetData getData;
	@Autowired
	JsonParseData jsonParseData;
	
	
	@Test
	public void instaServiceTest(){
		tagDao.deleteAll();
		contentDao.deleteAll();
		userDao.deleteAll();
		instaService.GetAndRegistryData("https://api.instagram.com/v1/users/self/media/recent/?access_token=2286401760.52879a7.2fcf17a37942461da015116ed66b2e5d","choahbom");
		
	}
	
}
