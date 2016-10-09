package graduate.platformdataservice;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import graduate.dao.ContentDao;
import graduate.dao.TagDao;
import graduate.domain.Content;
import graduate.domain.Tag;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class PlatFormDataServiceTest {
	
	@Autowired
	PlatFormDataService platFormDataService;
	@Autowired
	ContentDao contentDao;
	@Autowired
	TagDao tagDao;
	List<Content> contents;
	List<Tag> tags;
	
	
	
	
	
	@Before
	public void setUp(){
		contentDao.deleteAll();
		tagDao.deleteAll();
		contents = Arrays.asList(
					new Content(1,1,1,"testContent1"),
					new Content(2,2,2,"testContent2")
				);
		tags = Arrays.asList(
					new Tag(1,1,"testTag1"),
					new Tag(2,1,"testTag2"),
					new Tag(3,2,"testTag3"),
					new Tag(4,2,"testTag4")
				);
		
		for(Content content : contents){
			contentDao.add(content);
		}
		for(Tag tag : tags){
			tagDao.add(tag);
		}
	}
	
	
	@Test
	@Rollback
	public void getAllContent(){
	
		List<Content> getContents = this.platFormDataService.getAllContents();
		assertThat(getContents.size(),is(2));
		checkContents(getContents.get(0),contents.get(0));
		checkContents(getContents.get(1),contents.get(1));
		
		assertThat(getContents.get(0).getTags().size(),is(2));
		checkSameTag(getContents.get(0).getTags().get(tags.get(0).getTag_id()),tags.get(0));
		checkSameTag(getContents.get(0).getTags().get(tags.get(1).getTag_id()),tags.get(1));
		
		assertThat(getContents.get(1).getTags().size(),is(2));
		checkSameTag(getContents.get(1).getTags().get(tags.get(2).getTag_id()),tags.get(2));
		checkSameTag(getContents.get(1).getTags().get(tags.get(3).getTag_id()),tags.get(3));
		
		
	}
	
	public void getAllTag(){
		List<Tag> getTags = this.platFormDataService.getAllTags();
		assertThat(getTags.size(),is(4));
		
		checkContents(getTags.get(0).getContent(),contents.get(0));
		checkContents(getTags.get(2).getContent(),contents.get(1));
	}	
	private void checkContents(Content validation , Content expected){
		
		assertThat(validation.getId(), is(expected.getId()));
		assertThat(validation.getNum_like(), is(expected.getNum_like()));
		assertThat(validation.getNum_tag(), is(expected.getNum_tag()));
		assertThat(validation.getText() , is(expected.getText()));
	}	
	public void checkSameTag(Tag validation , Tag expected){
		assertThat(validation.getTag_id() , is(expected.getTag_id()));
		assertThat(validation.getContent_id() , is(expected.getContent_id()));
		assertThat(validation.getTag() , is(expected.getTag()));
	}
}
