package graduate.sqlservice;

import java.util.HashMap;
import java.util.Map;

public class HashMapSqlRegistry implements SqlRegistry {

	private Map<String,String> sqlMap = new HashMap<String , String>();
	
	@Override
	public void sqlRegister(String key, String value) {
		// TODO Auto-generated method stub
		sqlMap.put(key, value);
		
	}

	@Override
	public String findSql(String key) {
		// TODO Auto-generated method stub
		String sql = sqlMap.get(key);
		if(sql == null)
			throw new SqlNotFoundException(key+"를 이용해서 SQL을 찾을 수 없습니다.");
		else
			return sql;
	}

}
