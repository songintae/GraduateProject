package graduate.userservice;

import java.util.List;

import graduate.domain.TagWithCount;
import graduate.domain.cluster.Attribute;
import graduate.domain.cluster.Cluster;

public interface UserService {	
	public List<TagWithCount> getTagCount();
	public List<Cluster> getRecommandedClusters(List<TagWithCount> twcs);
	public List<Attribute> getAttributes(int cluster_id);
}
