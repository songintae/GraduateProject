package graduate.dao.cluster;

import java.util.List;

import graduate.domain.cluster.Attribute;
public interface AttributeDao {
	
	public void add(Attribute attribute);
	public List<Attribute> getAll(int cluster_id); //임시
}
