package graduate.dao.cluster;

import graduate.domain.cluster.Cluster;

public interface ClusterDao {
	
	public void add(Cluster cluster);
	public int getLastId();
}
