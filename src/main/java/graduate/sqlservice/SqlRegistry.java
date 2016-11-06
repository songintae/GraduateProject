package graduate.sqlservice;

public interface SqlRegistry {
	public void sqlRegister(String key , String value);
	public String findSql(String key);
}
