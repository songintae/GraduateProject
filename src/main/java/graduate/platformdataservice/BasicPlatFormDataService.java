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
import graduate.dao.cluster.AttributeDao;
import graduate.dao.cluster.ClusterDao;
import graduate.domain.Content;
import graduate.domain.Tag;
import graduate.domain.cluster.Area;
import graduate.domain.cluster.Attribute;
import graduate.domain.cluster.Cluster;

public class BasicPlatFormDataService implements PlatFormDataService {
	public ContentDao contentDao;
	public TagDao tagDao;
	public ClusterDao clusterDao;
	public AttributeDao attributeDao;
	
	private List<Content> contents;
	private List<Tag> tags;
	
	
	
	
	
	public void setContentDao(ContentDao contentDao){
		this.contentDao = contentDao;
	}
	public void setTagDao(TagDao tagDao){
		this.tagDao = tagDao;
	}
	public void setClusterDao(ClusterDao clusterDao){
		this.clusterDao = clusterDao;
	}
	public void setAttributeDao(AttributeDao attributeDao){
		this.attributeDao = attributeDao;
	}
	public List<Content> getAllContents(){
		return this.contents;
	}

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
	@Override
	public List<Cluster> getClusters(int area_id) {
		// TODO Auto-generated method stub
		List<Cluster> clusters = clusterDao.get(area_id);
		List<Attribute> attributes = attributeDao.get(area_id);
		
		
		return mapCluster(clusters, attributes);
	}
	
	@Override
	public List<Attribute> getAttribute(int area_id) {
		// TODO Auto-generated method stub
		List<Cluster> clusters = clusterDao.get(area_id);

		List<Attribute> attributes = attributeDao.get(area_id);
		
		clusters = mapCluster(clusters,attributes);
		return mapAttribute(clusters, attributes);
	}
	

	
	
	@Override
	public List<Cluster> getAllCluster() {
		// TODO Auto-generated method stub
		List<Cluster> clusters = clusterDao.getAll();
		List<Attribute> attributes = attributeDao.getAll();
		
		return mapCluster(clusters , attributes);
	
	}
	@Override
	public List<Attribute> getAllAttribute() {
		// TODO Auto-generated method stub
		List<Cluster> clusters = clusterDao.getAll();
		List<Attribute> attributes = attributeDao.getAll();
		
		clusters = mapCluster(clusters,attributes);
		return mapAttribute(clusters, attributes);
	}
	
	private List<Cluster> mapCluster(List<Cluster> clusters , List<Attribute> attributes){
	
		for(Cluster cluster : clusters){
			List<Attribute> rlAttribute = new ArrayList<Attribute>();
			for(Attribute attribute : attributes){
				if(cluster.getId() == attribute.getCluster_id()){
					attribute.setCluster(cluster);
					rlAttribute.add(attribute);
				}
			}
			cluster.setAttributes(rlAttribute);
		}
		return clusters;
	}
	
	private List<Attribute> mapAttribute(List<Cluster> clusters , List<Attribute> attributes){
		for(Attribute attribute : attributes){
			for(Cluster cluster : clusters){
				if(cluster.getId() == attribute.getCluster_id()){
					attribute.setCluster(cluster);
				}
			}
		}
		return attributes;
	}
	@Override
	public void attributeUpdate(Attribute attribute) {
		// TODO Auto-generated method stub
		this.attributeDao.update(attribute);
	}


}
