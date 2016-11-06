package graduate.sqlservice;

public class SqlNotFoundException extends RuntimeException {

		public SqlNotFoundException(SqlNotFoundException e){
			super(e);
		}
		public SqlNotFoundException(String message){
			super(message);
		}
}
