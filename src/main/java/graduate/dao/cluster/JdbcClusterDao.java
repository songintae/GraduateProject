package graduate.dao.cluster;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import graduate.domain.Content;
import graduate.domain.cluster.Area;
import graduate.domain.cluster.Cluster;

public class JdbcClusterDao implements ClusterDao {
	
	private JdbcTemplate jdbcTemplate; 
	
	private RowMapper<Cluster> clusterMapper = new RowMapper<Cluster>(){

		public Cluster mapRow(ResultSet rs, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			
			Cluster cluster = new Cluster();
			cluster.setId(rs.getInt("id"));
			cluster.setCluster_id(rs.getInt("cluster_id"));
			cluster.setCount(rs.getInt("count"));
			cluster.setArea(Area.valueOf(rs.getInt("area_id")));
			
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
				,cluster.getArea().getIntCode());
		
	}

	public int getLastId() {
		// TODO Auto-generated method stub
		try{
			return this.jdbcTemplate.query(new PreparedStatementCreator(){

				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					// TODO Auto-generated method stub
					return con.prepareStatement("select * from cluster");
				}
				
			}, new ResultSetExtractor<Integer>(){

				public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
					// TODO Auto-generated method stub
					rs.last();
					return rs.getInt("id");
					
				}});
		}catch(RuntimeException e){
			return 1;
		}
	}

	@Override
	public List<Cluster> getAll() {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.query("select * from cluster", this.clusterMapper);
	
	}

	@Override
	public List<Cluster> get(int area_id) {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.query("select * from cluster where area_id = "+area_id, this.clusterMapper);
	}

}
