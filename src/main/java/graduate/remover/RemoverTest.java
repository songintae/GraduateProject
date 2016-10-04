package graduate.remover;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import graduate.dao.ContentDao;
import graduate.dao.JdbcTagDao;
import graduate.dao.TagDao;
import graduate.domain.Content;
import graduate.domain.Tag;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class RemoverTest {
	
	
	@Autowired
	ApplicationContext context;
	@Autowired
	Remover noiseRemover;
	@Autowired
	ContentDao contentDao;
	@Autowired 
	TagDao tagDao;
	@Autowired
	AdRemover adRemover;
	
	List<Tag> tags;
	List<Content> contents;
	
	@Before
	public void setUp(){ 
		contentDao.deleteAll();
		tagDao.deleteAll();
		
		
		tags = Arrays.asList(
				new Tag(1 , 1 ,"tag1"),
				new Tag(2 , 1 ,"tag2"),
				new Tag(3 , 1 ,"tag3")
				);
		contents = Arrays.asList(
				new Content(1 , 1, 1, "test1"),
				new Content(2, 2, 2 ,"test2"),
				new Content(3, 3, 3 ,"test3"),
				new Content(4, 4, 4 ,"test4")
			);
		
		for(Content content : contents){
			contentDao.add(content);
		}
		for(Tag tag : tags){
			tagDao.add(tag);
		}
		
	}
	
	
	
	@Test
	public void noiseRemoverTest(){
		noiseRemover.remove();
		
		assertThat(tagDao.getCount(),is(1));
		List<Tag> getTag = tagDao.getAll();
		
		assertThat(getTag.get(0).getTag_id(),is(tags.get(1).getTag_id()));
		
	}
	@Test
	public void adRemoverTest(){
		adRemover.remove();
		
		assertThat(contentDao.getCount() , is(2));
		List<Content> getContent = contentDao.getAll();
		
		assertThat(getContent.get(0).getId(),is(contents.get(1).getId()));
		assertThat(getContent.get(1).getId(),is(contents.get(3).getId()));
	}
	
	

}
