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
			Map<Integer,Tag> tagHash = new HashMap<Integer,Tag>();
			
			for(;i<tags.size(); i++){
				Tag tag = tags.get(i);
				tag.setContent(content);
				if(tag.getContent_id() == content.getId())
					tagHash.put(tag.getTag_id(),tag);
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
	public List<Content> getAllContents() {
		// TODO Auto-generated method stub
		return this.contents;
	}

	public List<Tag> getAllTags() {
		// TODO Auto-generated method stub
		return this.tags;
	}

}
