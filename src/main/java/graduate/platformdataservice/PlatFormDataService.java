package graduate.platformdataservice;

import java.util.List;

import graduate.domain.Content;
import graduate.domain.Tag;

public interface PlatFormDataService {
	
	public List<Content> getAllContents();
	public List<Tag> getAllTags();
}
