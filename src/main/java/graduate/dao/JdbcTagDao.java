package graduate.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import graduate.domain.Tag;

public class JdbcTagDao implements TagDao  {
	
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public void add(Tag tag) {
		// TODO Auto-generated method stub
		
		this.jdbcTemplate.update("insert into tags(tag_id,content_id,tag) values(?,?,?)"
				,tag.getTag_id(),tag.getContent_id(),tag.getTag());
		
	}
	public void delete(int id) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update("delete from tag where id = ?", id);
		
	}
	public void tagDelete(String tag) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update("delete from tag where tag = ?", tag);
		
	}
	
}
