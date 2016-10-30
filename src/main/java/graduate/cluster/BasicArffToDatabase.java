package graduate.cluster;

import java.sql.Connection;

import javax.sql.DataSource;





public class BasicArffToDatabase {
	private DataSource instaDataSource;
	private DataSource clusterDataSource;
	
	public void setInstaDataSource(DataSource dataSource){
		this.instaDataSource = dataSource;
	}
	public void setClusterDataSource(DataSource dataSource){
		this.clusterDataSource = dataSource;
	}
}
