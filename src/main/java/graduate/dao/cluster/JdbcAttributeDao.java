package graduate.dao.cluster;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import graduate.domain.cluster.Attribute;
import graduate.domain.cluster.Cluster;

public class JdbcAttributeDao implements AttributeDao {
	
private JdbcTemplate jdbcTemplate; 
	
	private RowMapper<Attribute> contentMapper = new RowMapper<Attribute>(){

		public Attribute mapRow(ResultSet rs, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			Attribute attribute = new Attribute();
			
			attribute.setId(rs.getInt("id"));
			attribute.setCount(rs.getInt("count"));
			attribute.setTag(rs.getString("tag"));
			attribute.setCluster_id(rs.getInt("cluster_id"));
			
			return attribute;
		}
		
	};
	
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void add(Attribute attribute) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update("insert into Attribute(count,tag,cluster_id) "
				+"values(?,?,?)", attribute.getCount(),attribute.getTag()
				,attribute.getCluster_id());
		
	}

}
