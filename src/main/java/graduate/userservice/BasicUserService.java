package graduate.userservice;

import java.util.HashMap;
import java.util.List;
import graduate.domain.Tag;
import graduate.platformdataservice.PlatFormDataService;

public class BasicUserService implements UserService{

	private PlatFormDataService platformDataService;
	
	public void setPlatformDataService(PlatFormDataService platformDataService) {
		this.platformDataService = platformDataService;
	}

	public HashMap<String, Integer> getTagCount() {
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
		return tagCount;
	}

}
