package graduate.aplicationcontext;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.mysql.jdbc.Driver;

import graduate.dao.ContentDao;
import graduate.dao.JdbcContentDao;
import graduate.dao.JdbcTagDao;
import graduate.dao.TagDao;
import graduate.instagram.BasicInstaService;
import graduate.instagram.GetData;
import graduate.instagram.HttpGetData;
import graduate.instagram.InstaService;
import graduate.instagram.JsonParseData;
import graduate.instagram.ParseData;

@Configuration
public class AppContext {





	@Bean
	public ContentDao contentDao(){
		JdbcContentDao contentDao = new JdbcContentDao();
		contentDao.setDataSource(dataSource());
		return contentDao();
	}

	@Bean
	public TagDao tagDao(){
		JdbcTagDao tagDao = new JdbcTagDao();
		tagDao.setDataSource(dataSource());
		return tagDao();
	}

	@Bean
	public InstaService instaService(){
		BasicInstaService instaService = new BasicInstaService();
		instaService.setContentDao(contentDao());
		instaService.setTagDao(tagDao());
		instaService.setGetData(getData());
		return instaService;
	}

	@Bean
	public GetData getData(){
		HttpGetData getData = new HttpGetData();
		getData.setParseData(parseData());
		return getData;
	}

	@Bean
	public ParseData parseData(){
		JsonParseData parseData = new JsonParseData();
		parseData.setInstaService(instaService());
		return parseData;
	}

	@Bean
	public DataSource dataSource(){


		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
	    dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost/instadata");

		dataSource.setUsername("root");
		dataSource.setPassword("song9207108");


		return dataSource;
	}


}
