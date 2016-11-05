package graduate.domain.cluster;

public class Attribute {
	int id;
	String tag;
	int count;
	int cluster_id;
	
	double tf_score;
	double idf_score;
	double tf_idf_score;
	
	
	public double getTf_score() {
		return tf_score;
	}

	public void setTf_score(double tf_score) {
		this.tf_score = tf_score;
	}

	public double getIdf_score() {
		return idf_score;
	}

	public void setIdf_score(double idf_score) {
		this.idf_score = idf_score;
	}

	public double getTf_idf_score() {
		return tf_idf_score;
	}

	public void setTf_idf_score(double tf_idf_score) {
		this.tf_idf_score = tf_idf_score;
	}

	Cluster cluster;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCluster_id() {
		return cluster_id;
	}

	public void setCluster_id(int cluster_id) {
		this.cluster_id = cluster_id;
	}

	public Cluster getCluster() {
		return cluster;
	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}
}
