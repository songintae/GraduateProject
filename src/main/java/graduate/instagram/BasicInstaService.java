package graduate.instagram;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import graduate.dao.ContentDao;
import graduate.dao.TagDao;
import graduate.domain.Content;
import graduate.domain.Tag;

public class BasicInstaService implements InstaService {
	
	List<Content> contents = new ArrayList<Content>();
	List<Tag> tags = new ArrayList<Tag>();
	
	private ContentDao contentDao;
	private TagDao tagDao;
	
	private GetData getData;
	
	public void setGetData(GetData getData){
		this.getData = getData;
	}
	
	
	public void setContentDao(ContentDao contentDao){
		this.contentDao = contentDao;
	}
	
	public void setTagDao(TagDao tagDao){
		this.tagDao = tagDao;
	}

	public void registryContent(Content content) {
		// TODO Auto-generated method stub
		
		this.contentDao.add(content);
		
	}

	public void registryTag(Tag tag) {
		// TODO Auto-generated method stub
		
		this.tagDao.add(tag);
	}

	public void GetAndRegistryData(String url) {
		// TODO Auto-generated method stub
		String next_url = url;
		while(next_url != null){
			next_url = this.getData.getData(next_url);
		}
		
	}
	
	

	
}
