package graduate.platformdataservice;

import java.util.List;

import graduate.domain.Content;
import graduate.domain.Tag;

public interface PlatFormDataService {
	
	//전체컨텐츠 받아오
	public List<Content> getAllContents();
	
	//User별로 컨텐츠받아오
	public List<Content> getUserContents(String user_id);
	
	public List<Tag> getAllTags();
	
	public List<Tag> getUserTags(String user_id);
	
	
}
