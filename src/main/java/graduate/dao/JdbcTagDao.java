package graduate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import graduate.domain.Tag;
import graduate.sqlservice.SqlService;

public class JdbcTagDao implements TagDao  {
	
	private JdbcTemplate jdbcTemplate;
	private SqlService sqlService;
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void setSqlService(SqlService sqlService){
		this.sqlService = sqlService;
	}
	
	private RowMapper<Tag> tagMapper = new RowMapper<Tag>(){

		public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Tag newTag = new Tag();
			newTag.setContent_id(rs.getInt("content_id"));
			newTag.setTag_id(rs.getInt("tag_id"));
			newTag.setTag(rs.getString("tag"));
			return newTag;
		}
		
	};
	public void add(Tag tag) {
		// TODO Auto-generated method stub
		
		this.jdbcTemplate.update(this.sqlService.getSql("tagAdd")
				,tag.getTag_id(),tag.getContent_id(),tag.getTag());
		
	}
	public void delete(int id) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update(this.sqlService.getSql("tagDelete"), id);
		
	}
	
	public Tag get(int id) {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.queryForObject(this.sqlService.getSql("tagGet")+id,this.tagMapper);
		
	}
	public List<Tag> getAll() {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.query(this.sqlService.getSql("tagGetAll"), this.tagMapper);
	}
	public List<Tag> getAll(int content_id) {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.query(this.sqlService.getSql("tagGetAllByContent_id")+content_id, this.tagMapper);
	}
	public void deleteAll() {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update(this.sqlService.getSql("tagDeleteAll"));
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.query(new PreparedStatementCreator(){

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				return con.prepareStatement(sqlService.getSql("tagGetCount"));
			}
			
		}, new ResultSetExtractor<Integer>(){

			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				rs.next();
				
				return rs.getInt(1);
			}});
	}
	
	public int getLastId() {
		// TODO Auto-generated method stub
		try{
			return this.jdbcTemplate.query(new PreparedStatementCreator(){

				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					// TODO Auto-generated method stub
					return con.prepareStatement(sqlService.getSql("tagGetLastId"));
				}
				
			}, new ResultSetExtractor<Integer>(){

				public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
					// TODO Auto-generated method stub
					rs.last();
					return rs.getInt("tag_id");
					
				}});
		}catch(RuntimeException e){
			return 0;
		}
	}
	
	
}
