package graduate.cluster;

import java.util.List;

import graduate.domain.Content;
import graduate.domain.Tag;
import graduate.domain.TagWithCount;
import graduate.platformdataservice.PlatFormDataService;

public class BasicContentsBasedMatrixMaker implements ContentsBasedMatrixMaker{
	private PlatFormDataService platFormDataService;
	private TagDupRemover tagDupRemover;	
	
	
	public void setTagDupRemover(TagDupRemover tagDupRemover) {
		this.tagDupRemover = tagDupRemover;
	}

	public void setPlatFormDataService(PlatFormDataService platFormDataService){
		this.platFormDataService = platFormDataService;
	}

	public int[][] make(List<TagWithCount> tagList) {
		List<TagWithCount> allTags = tagDupRemover.getTagSet();
		List<Content> contents = platFormDataService.getAllContents();
		
		int[][] contentsBasedMatrix = new int[contents.size()][allTags.size()];
		//0으로 초기화
		
		for(int i = 0; i < contents.size(); i++){
			
			Content content = contents.get(i);
			List<Tag> tags = content.getTags();
			
			for(int j = 0; j < allTags.size(); j++){
				
				contentsBasedMatrix[i][j] = 0;
				
				for(Tag tag : tags){
					if(tag.getTag().equals(allTags.get(j).getTag()))
						contentsBasedMatrix[i][j]++;
				}
			}
		}
		return contentsBasedMatrix;
	}

}
