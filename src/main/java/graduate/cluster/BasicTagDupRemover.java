package graduate.cluster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import graduate.domain.Content;
import graduate.domain.Tag;
import graduate.domain.TagWithCount;
import graduate.platformdataservice.PlatFormDataService;

public class BasicTagDupRemover implements TagDupRemover {
	private PlatFormDataService platFormDataService;
	private List<Content> contentList;
	private List<Tag> tagList;
	public void setPlatFormDataService(PlatFormDataService platFormDataService){
		this.platFormDataService = platFormDataService;
	}
	public List<TagWithCount> getTagSet() {
		// TODO Auto-generated method stub
		contentList = platFormDataService.getAllContents();
		tagList = platFormDataService.getAllTags();
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		int num = 1;
		String tag;
		Tag tagObject;
		
	
		
		for (int i = 0; i < tagList.size(); i++) {
			tagObject = tagList.get(i);
			tag = tagObject.getTag();
			num = 1;
			if (map.get(tag) != null) {
				num = map.get(tag);
				num++;
			}
			map.put(tag, num);
		}
		
		
		
		List<TagWithCount> tagWithCntList = new ArrayList<TagWithCount>();
		Set<String> keySet = map.keySet();
		TagWithCount twc;
		if(map.size() > 0){
			for(String keyTag : keySet){
				twc = new TagWithCount();
				twc.setTag(keyTag);
				twc.setCount(map.get(keyTag));
				tagWithCntList.add(twc);
			}
		}
		
		
		return tagWithCntList;
	}
}
