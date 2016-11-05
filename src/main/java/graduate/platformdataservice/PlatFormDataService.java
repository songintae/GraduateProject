package graduate.platformdataservice;

import java.util.List;

import graduate.domain.Content;
import graduate.domain.Tag;
import graduate.domain.cluster.Cluster;
import graduate.domain.cluster.Attribute;

public interface PlatFormDataService {
	
	//전체컨텐츠 받아오
	public List<Content> getAllContents();
	
	//User별로 컨텐츠받아오
	public List<Content> getUserContents(String user_id);
	
	//지역별로 받아오
	public List<Cluster> getClusters(int area_id);
	public List<Attribute> getAttribute(int area_id);
	
	//전체 클러스터 받아오기.
	public List<Cluster> getAllCluster();
	public List<Attribute> getAllAttribute();
	
	
	public List<Tag> getAllTags();
	
	public List<Tag> getUserTags(String user_id);
	
	public void attributeUpdate(Attribute attribute);
	
	
}
