package graduate.dao.cluster;

import java.util.List;

import graduate.domain.cluster.Cluster;

public interface ClusterDao {
	
	public void add(Cluster cluster);
	public int getLastId();
	
	public List<Cluster> getAll();
	public List<Cluster> get(int area_id);
}
