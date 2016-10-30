package graduate.dao;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import graduate.domain.Content;
import graduate.domain.Tag;
import graduate.domain.User;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class TagDaoTest {
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	TagDao tagDao;
	
	@Autowired
	UserDao userDao;
	
	//관계 때문에 생성.
	@Autowired
	ContentDao contentDao;
	List<Tag> tags;
	
	@Before
	public void setUp(){
		contentDao.deleteAll();
		userDao.deleteAll();
		User user = new User();
		user.setUser_id("choahbom");	
		userDao.add(user);
		Content content = new Content(1,1,1,"text","choahbom");
		contentDao.add(content);
		
		tags = Arrays.asList(
				new Tag(1 , 1 ,"태그1"),
				new Tag(2 , 1 ,"태그2"),
				new Tag(3 , 1 ,"태그3")
				);
	}
	
	@Test
	public void add(){
		tagDao.deleteAll();
		
		assertThat(tagDao.getCount() , is(0));
		for(Tag tag : tags){
			tagDao.add(tag);
		}
		
		assertThat(tagDao.getCount(),is(3));
	}
	
	//add exception test
	@Test(expected = DataIntegrityViolationException.class)
	public void addExceptionTest(){
		tagDao.deleteAll();
		contentDao.deleteAll();
		
		tagDao.add(tags.get(0));
	}
	
	@Test
	public void get(){
		tagDao.deleteAll();
		assertThat(tagDao.getCount() , is(0));
		for(Tag tag : tags){
			tagDao.add(tag);
		}
		assertThat(tagDao.getCount(),is(3));
		
		for(Tag tag : tags){
			Tag newTag = tagDao.get(tag.getTag_id());
			checkSameTag(newTag , tag);
		};
		
	}
	
	public void checkSameTag(Tag validation , Tag expected){
		assertThat(validation.getTag_id() , is(expected.getTag_id()));
		assertThat(validation.getContent_id() , is(expected.getContent_id()));
		assertThat(validation.getTag() , is(expected.getTag()));
	}
	
	
	@Test
	public void deleteAll(){
		tagDao.deleteAll();
	
	}
	
	@Test
	public void getAll(){
		tagDao.deleteAll();
		assertThat(tagDao.getCount() , is(0));
		for(Tag tag : tags){
			tagDao.add(tag);
		}
		assertThat(tagDao.getCount(),is(3));
		
		List<Tag> getTags = tagDao.getAll();
		
		for(int i = 0; i<getTags.size() ; i++){
			checkSameTag(getTags.get(i),tags.get(i));
		}
	}
	
	@Test
	public void delete(){
		tagDao.deleteAll();
		assertThat(tagDao.getCount() , is(0));
		for(Tag tag : tags){
			tagDao.add(tag);
		}
		assertThat(tagDao.getCount(),is(3));
		
		tagDao.delete(tags.get(0).getTag_id());
		tagDao.delete(tags.get(2).getTag_id());
		
		List<Tag> getTags = tagDao.getAll();
		
		assertThat(getTags.size(),is(1));
		
		checkSameTag(getTags.get(0),tags.get(1));
//		
	}
	@Test
	public void getLastId(){
		tagDao.deleteAll();
		assertThat(tagDao.getCount() , is(0));
		for(Tag tag : tags){
			tagDao.add(tag);
		}
		assertThat(tagDao.getCount(),is(3));
		
		assertThat(tagDao.getLastId(),is(3));
		
	}
}
