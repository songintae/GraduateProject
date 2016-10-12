package graduate.cluster;

import java.util.List;

import graduate.domain.TagWithCount;

public interface TagDupRemover {
	
	public List<TagWithCount> getTagSet();
}
