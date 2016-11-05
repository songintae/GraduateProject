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

public class JdbcTagDao implements TagDao  {
	
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
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
		
		this.jdbcTemplate.update("insert into tags(tag_id,content_id,tag) values(?,?,?)"
				,tag.getTag_id(),tag.getContent_id(),tag.getTag());
		
	}
	public void delete(int id) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update("delete from tags where tag_id = ?", id);
		
	}
	
	public Tag get(int id) {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.queryForObject("select * from tags where tag_id = "+id,this.tagMapper);
		
	}
	public List<Tag> getAll() {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.query("select * from tags", this.tagMapper);
	}
	public List<Tag> getAll(int content_id) {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.query("select * from tags where content_id ="+content_id, this.tagMapper);
	}
	public void deleteAll() {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update("delete from tags ");
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.query(new PreparedStatementCreator(){

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				return con.prepareStatement("select count(*) from tags");
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
					return con.prepareStatement("select * from tags");
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
