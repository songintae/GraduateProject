package graduate.sqlservice;



public class SqlRetrievalFailureException extends RuntimeException {
	public SqlRetrievalFailureException(String message){
		super(message);
	}

	public SqlRetrievalFailureException(SqlRetrievalFailureException e) {
		// TODO Auto-generated constructor stub
		super(e);
	}

}
