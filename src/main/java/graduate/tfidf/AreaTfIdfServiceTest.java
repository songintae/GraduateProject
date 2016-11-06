package graduate.tfidf;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class AreaTfIdfServiceTest {
	
	@Autowired
	AreaTfIdfService areaTfIdfService;
	
	@Test
	public void areaTfIdfServicetest(){
		List<String> areas = new ArrayList<String>();
		areas.add("gunsan");
		areas.add("boseong");
		areas.add("jeonju");
		areas.add("gangneung");
		
		areaTfIdfService.tfIdf(areas, "gangneung");
		
		
		//areaTfIdfService.settingTf("gangneung");
	}
}
