package graduate.userTest;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import graduate.domain.Tag;
import graduate.platformdataservice.PlatFormDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class UserTest {

	@Autowired
	PlatFormDataService platformDataService;

	public void setPlatformDataService(PlatFormDataService platformDataService) {
		this.platformDataService = platformDataService;
	}

	@Test
	public void userTest() {
		List<Tag> tagList = platformDataService.getAllTags();
		HashMap<String, Integer> tagCount = new HashMap<String, Integer>();

		for (Tag tagObject1 : tagList) {
			int count = 0;
			if (tagCount.get(tagObject1.getTag()) == null) {
				for (Tag tagObject2 : tagList) {
					if (tagObject1.getTag().equals(tagObject2.getTag()))
						count++;
				}
				tagCount.put(tagObject1.getTag(), count);
			}
			String tagName = tagObject1.getTag();
			System.out.println(tagName + " : " + tagCount.get(tagName));
		}
	}
}
