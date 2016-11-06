package graduate.dao.cluster;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import graduate.domain.cluster.Attribute;
import graduate.domain.cluster.Cluster;
import graduate.sqlservice.SqlService;

public class JdbcAttributeDao implements AttributeDao {
	
	private JdbcTemplate jdbcTemplate; 
	private SqlService sqlService;

	
	private RowMapper<Attribute> attributeMapper = new RowMapper<Attribute>(){

		public Attribute mapRow(ResultSet rs, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			Attribute attribute = new Attribute();
			
			attribute.setId(rs.getInt("id"));
			attribute.setCount(rs.getInt("count"));
			attribute.setTag(rs.getString("tag"));
			attribute.setCluster_id(rs.getInt("cluster_id"));		
			attribute.setTf_score(rs.getDouble("tf_score"));
			attribute.setIdf_score(rs.getDouble("idf_score"));
			attribute.setTf_idf_score(rs.getDouble("tf_idf_score"));
			
			return attribute;
		}
		
	};
	
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public void setSqlService(SqlService sqlService){
		this.sqlService = sqlService;
	}
	
	public void add(Attribute attribute) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update(this.sqlService.getSql("attributeAdd"), attribute.getCount(),attribute.getTag()
				,attribute.getCluster_id());
		
	}

	@Override
	public List<Attribute> getAll() {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.query(this.sqlService.getSql("attributeGetAll") , this.attributeMapper);
		
	}

	@Override
	public List<Attribute> get(int area_id) {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.query(this.sqlService.getSql("attributeGetByArea")+area_id,this.attributeMapper);
	}

	@Override
	public void update(Attribute attribute) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update(this.sqlService.getSql("attributeUpdate"),
				attribute.getTag(), attribute.getCount(),
				attribute.getCluster_id(), attribute.getTf_score() , attribute.getIdf_score()
				,attribute.getTf_idf_score(), attribute.getId());
		
	}

}
