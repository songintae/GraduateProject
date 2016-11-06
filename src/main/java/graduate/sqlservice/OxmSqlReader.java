package graduate.sqlservice;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.Unmarshaller;

import graduate.sqlservice.jaxb.SqlType;
import graduate.sqlservice.jaxb.Sqlmap;

public class OxmSqlReader implements SqlReader {
	
	
	private Unmarshaller unmarshaller;
	private SqlRegistry SqlRegistry;
	
	private Resource sqlmap = new ClassPathResource("/sqlmap.xml");
	
	public void setUnmarshaller(Unmarshaller unmarshaller){
		this.unmarshaller = unmarshaller;
	}

	
//	public void setSqlmap(Resource sqlmap){
//		this.sqlmap = sqlmap;
//	}
	

	@Override
	public void read(SqlRegistry sqlRegistry) {
		// TODO Auto-generated method stub
		
		try{
			Source source = new StreamSource(sqlmap.getInputStream());
			Sqlmap sqlmap = (Sqlmap)this.unmarshaller.unmarshal(source);
			
			for(SqlType sql : sqlmap.getSql()){
				sqlRegistry.sqlRegister(sql.getKey(), sql.getValue());
			}
		}catch(IOException e){
			throw new IllegalArgumentException(this.sqlmap.getFilename() + "을 가져올 수 없습니다.");
		}
		
	}
	
	
	


}
