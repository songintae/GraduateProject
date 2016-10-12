package graduate.cluster;

import java.util.List;

import graduate.domain.TagWithCount;

public interface ContentsBasedMatrixMaker {
	public int[][] make(List<TagWithCount> tagList);
}
