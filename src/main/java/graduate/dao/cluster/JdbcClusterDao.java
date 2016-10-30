package graduate.dao.cluster;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import graduate.domain.Content;
import graduate.domain.cluster.Cluster;

public class JdbcClusterDao implements ClusterDao {
	
	private JdbcTemplate jdbcTemplate; 
	
	private RowMapper<Cluster> contentMapper = new RowMapper<Cluster>(){

		public Cluster mapRow(ResultSet rs, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			
			Cluster cluster = new Cluster();
			cluster.setId(rs.getInt("id"));
			cluster.setCluster_id(rs.getInt("cluster_id"));
			cluster.setCount(rs.getInt("count"));
			cluster.setArea_id(rs.getInt("area_id"));
			
			return cluster;
		}
		
	};
	
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void add(Cluster cluster) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update("insert into cluster(cluster_id,count,area_id) "
				+"values(?,?,?)",cluster.getCluster_id(),cluster.getCount()
				,cluster.getArea_id());
		
	}

}
