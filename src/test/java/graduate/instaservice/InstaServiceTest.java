package graduate.instaservice;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import graduate.aplicationcontext.AppContext;
import graduate.dao.ContentDao;
import graduate.dao.TagDao;
import graduate.instagram.InstaService;
import graduate.remover.Remover;

public class InstaServiceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new GenericXmlApplicationContext("/applicationContext.xml");
		//한글한글
		Remover noiseRemover = context.getBean("noiseRemover",Remover.class);
		noiseRemover.remove();
	}
	
}
