package graduate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import graduate.domain.Tag;
import graduate.domain.User;
import graduate.sqlservice.SqlService;

public class JdbcUserDao implements UserDao {

	private JdbcTemplate jdbcTemplate;
	private SqlService sqlService;
	
	public void setSqlService(SqlService sqlService){
		this.sqlService = sqlService;
	}
	
	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate  = new JdbcTemplate(dataSource);
	}
	
	private RowMapper<User> tagMapper = new RowMapper<User>(){

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			User user = new User();
			user.setUser_id(rs.getString("user_id"));
			
			
			return user;
		}
		
	};
	
	public void add(User user) {
		// TODO Auto-generated method stub
		
		this.jdbcTemplate.update(this.sqlService.getSql("userAdd"),user.getUser_id());
		
		
	}

	public void deleteAll() {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update(this.sqlService.getSql("userDeleteAll"));
		
	}

	
		
}
