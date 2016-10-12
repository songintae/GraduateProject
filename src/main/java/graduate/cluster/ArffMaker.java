package graduate.cluster;

import java.util.List;

import graduate.domain.TagWithCount;

public interface ArffMaker {
	public void make(int[][] matrix) throws Exception;
	public void setTags(List<TagWithCount> noDupTags);
}
