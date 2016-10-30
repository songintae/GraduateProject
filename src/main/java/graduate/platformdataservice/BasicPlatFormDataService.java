package graduate.platformdataservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import graduate.dao.ContentDao;
import graduate.dao.TagDao;
import graduate.domain.Content;
import graduate.domain.Tag;

public class BasicPlatFormDataService implements PlatFormDataService {
	public ContentDao contentDao;
	public TagDao tagDao;
	
	private List<Content> contents;
	private List<Tag> tags;

	@PostConstruct
	public void setUp(){
		
		contents = contentDao.getAll();
		tags = tagDao.getAll();
		
		int i = 0;
		for(Content content : contents){
			List<Tag> tagHash = new ArrayList<Tag>();
			
			for(;i<tags.size(); i++){
				Tag tag = tags.get(i);
				tag.setContent(content);
				if(tag.getContent_id() == content.getId())
					tagHash.add(tag);
				else
					break;
			}
			content.setTags(tagHash);
		}
		
		
	}
	public void setContentDao(ContentDao contentDao){
		this.contentDao = contentDao;
	}
	public void setTagDao(TagDao tagDao){
		this.tagDao = tagDao;
	}
	public List<Content> getAllContents(){
		return this.contents;
	}
	public List<Content> getUserContents(String user_id) {
		// TODO Auto-generated method stub
		List<Content> userContents = new ArrayList<Content>();
		for(Content content : this.contents ){
			if(content.getUser_id().equals(user_id))
			{
				userContents.add(content);
			}
		}
		return userContents;
	}

	public List<Tag> getAllTags() {
		// TODO Auto-generated method stub
		List tags = new ArrayList<Tag>();
		for(Content content : this.contents)
		{
			for(Tag tag : content.getTags())
			{
				tags.add(tag);
			}
		}
		return tags;
	}
	
	public List<Tag> getUserTags(String user_id){
		
		List tags = new ArrayList<Tag>();
		for(Content content : this.getUserContents(user_id))
		{
			for(Tag tag : content.getTags())
			{
				tags.add(tag);
			}
		}
		
		return tags;
	}
	

}
