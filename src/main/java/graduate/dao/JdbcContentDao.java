package graduate.dao;

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

public class JdbcContentDao implements ContentDao {
	
	private JdbcTemplate jdbcTemplate; 
	
	private RowMapper<Content> contentMapper = new RowMapper<Content>(){

		public Content mapRow(ResultSet rs, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			
			Content content = new Content();
			content.setId(rs.getInt("content_id"));
			content.setNum_like(rs.getInt("num_like"));
			content.setNum_tag(rs.getInt("num_tag"));
			content.setText(rs.getString("text"));
			return content;
		}
		
	};
	
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public void add(Content content) {
		// TODO Auto-generated method stub
		
		this.jdbcTemplate.update("insert into content(content_id,num_like,num_tag,text) values(?,?,?,?)",
				content.getId(),content.getNum_like(),content.getNum_tag(),content.getText());
		
	}

	public Content get(int id) {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.queryForObject("select * from content where content_id ="+id, this.contentMapper);
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update("delete from content where content_id = ?",id);
	}
	
	public void deleteAll() {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update("delete from content");
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.query(new PreparedStatementCreator(){

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				return con.prepareStatement("select count(*) from content");
			}
			
		}, new ResultSetExtractor<Integer>(){

			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				rs.next();
				
				return rs.getInt(1);
			}});
	}
	public List<Content> getAll() {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.query("select * from content",this.contentMapper);
	}
	public int getLastId() {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.query(new PreparedStatementCreator(){

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				return con.prepareStatement("select * from content");
			}
			
		}, new ResultSetExtractor<Integer>(){

			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				rs.last();
				
				return rs.getInt("content_id");
			}});
	}
	

}
