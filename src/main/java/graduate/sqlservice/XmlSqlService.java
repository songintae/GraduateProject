package graduate.sqlservice;

import javax.annotation.PostConstruct;

public class XmlSqlService implements SqlService {
	
	
	private SqlRegistry sqlRegistry;
	private SqlReader sqlReader;
	
	
	
	
	public void setSqlRegistry(SqlRegistry sqlRegistry) {
		this.sqlRegistry = sqlRegistry;
	}

	public void setSqlReader(SqlReader sqlReader) {
		this.sqlReader = sqlReader;
	}

	@PostConstruct
	public void loadSql(){
		this.sqlReader.read(sqlRegistry);
	}
	
	@Override
	public String getSql(String key) {
		// TODO Auto-generated method stub
		try{
			String sql = sqlRegistry.findSql(key);
			return sql;
		}catch(SqlRetrievalFailureException e){
			throw new SqlRetrievalFailureException(e);
		}
		
	}

}
