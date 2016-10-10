package graduate.cluster;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import graduate.domain.Content;
import graduate.domain.Tag;
import graduate.instagram.InstaService;
import graduate.platformdataservice.PlatFormDataService;

import weka.core.Instances;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class UserClusterMaker {
	List<Content> cList;
	List<Tag> tList;

	@Autowired
	private PlatFormDataService pService;
	@Autowired
	private InstaService iService;

	// @Test
	
	public void setup(){
		cList = pService.getAllContents();
		tList = pService.getAllTags();
	}
	
	public HashMap<String, Integer> getTagWithNum() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		int num = 1;
		String tag;
		Tag t;
		
		if(tList.size() < 1){
			map.put("태그없음", 0);
			return map;
		}
		
		for (int i = 0; i < tList.size(); i++) {
			t = tList.get(i);
			tag = t.getTag();
			num = 1;
			if (map.get(tag) != null) {
				num = map.get(tag);
				num++;
			}
			map.put(tag, num);
		}
		
		return map;
	}

	@Test
	public void tagwithnumTest() {
		HashMap<String, Integer> map = getTagWithNum();

		Set<String> tset = map.keySet();

		for (String i : tset) {
			System.out.println(i + "/ " + map.get(i));
		}
	}

	public PlatFormDataService getpService() {
		return pService;
	}

	public void setpService(PlatFormDataService pService) {
		this.pService = pService;
	}

	public InstaService getiService() {
		return iService;
	}

	public void setiService(InstaService iService) {
		this.iService = iService;
	}
}
