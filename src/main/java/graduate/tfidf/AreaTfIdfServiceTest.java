package graduate.tfidf;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import graduate.domain.cluster.Attribute;
import graduate.platformdataservice.PlatFormDataService;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class AreaTfIdfServiceTest {
	
	@Autowired
	AreaTfIdfService areaTfIdfService;
	@Autowired
	PlatFormDataService platFormDataService;
	
	
	public void areaTfIdfServicetest(){
		List<String> areas = new ArrayList<String>();
		areas.add("gunsan");
	    areas.add("boseong");
	      areas.add("jeonju");
	      areas.add("gangneung");
	      areas.add("seoul");
	      areas.add("busan");
	      areas.add("daegu");
	      areas.add("danyang");
	      areas.add("gyeongju");
	      areas.add("suncheon");
	      areas.add("jeongdongjin");
	      areas.add("andong");
		
		areaTfIdfService.tfIdf(areas, "busan");
		
		
		//areaTfIdfService.settingTf("gangneung");
	}
	
	@Test
	public void getUpdateQuery(){
		List<Attribute> attributes = this.platFormDataService.getAttribute(7);
		assertThat(attributes.size() , is(1090));
		
		String sql ="";
		for(Attribute attribute : attributes){
			System.out.println("update attribute set tag ='"+attribute.getTag()
			+"',count="+attribute.getCount()+",cluster_id="+attribute.getCluster_id()
			+",tf_score="+attribute.getTf_score()+",idf_score="+attribute.getIdf_score()
			+",tf_idf_score="+attribute.getTf_idf_score()+" where id="+attribute.getId()+";");
		}
	}
}
