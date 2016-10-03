package graduate.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import graduate.domain.Content;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class ContentDaoTest {
	
	@Autowired
	ContentDao contentDao;
	
	List<Content> contents;
	@Before
	public void setUp(){
	contents = Arrays.asList(
			new Content(1 , 1, 1, "test1"),
			new Content(2, 2, 2 ,"test2"),
			new Content(3, 3, 3 ,"test3"),
			new Content(4, 4, 4 ,"test3")
		);
	}
	
	@Test
	public void add(){
		
		contentDao.deleteAll();
		assertThat(contentDao.getCount(), is(0));
		
		for(Content content : contents){
			contentDao.add(content);
		}
		
		assertThat(contentDao.getCount(),is(4));
	}
	@Test
	public void get(){
		contentDao.deleteAll();
		assertThat(contentDao.getCount(),is(0));
		
		contentDao.add(contents.get(0));
		contentDao.add(contents.get(1));
		
		assertThat(contentDao.getCount(),is(2));
		
		Content content1 = contentDao.get(contents.get(0).getId());
		Content content2 = contentDao.get(contents.get(1).getId());
		
		checkContents(content1 , contents.get(0));
		checkContents(content2 , contents.get(1));
	}
	
	private void checkContents(Content validation , Content expected){
		
		assertThat(validation.getId(), is(expected.getId()));
		assertThat(validation.getNum_like(), is(expected.getNum_like()));
		assertThat(validation.getNum_tag(), is(expected.getNum_tag()));
		assertThat(validation.getText() , is(expected.getText()));
	}
	
	@Test
	public void getAll(){
		contentDao.deleteAll();
		assertThat(contentDao.getCount(),is(0));
		
		for(Content content : contents){
			contentDao.add(content);
		}
		assertThat(contentDao.getCount(),is(4));
		
		List<Content> getContents =  contentDao.getAll();
		int i;
		for(i = 0; i<getContents.size() ; i++){
			checkContents(getContents.get(i),contents.get(i));
		}
		
	}
	
	@Test
	public void delete(){
		contentDao.deleteAll();
		
		for(Content content : contents){
			contentDao.add(content);
		}
		
		assertThat(contentDao.getCount() , is(4));
		
		contentDao.delete(contents.get(0).getId());
		contentDao.delete(contents.get(2).getId());
		
		assertThat(contentDao.getCount() , is(2));
		
		List<Content> getContents = contentDao.getAll();
		
		checkContents(getContents.get(0),contents.get(1));
		checkContents(getContents.get(1),contents.get(3));
	}
	
	
}
