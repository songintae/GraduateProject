package graduate.wekaTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import graduate.cluster.ContentsBasedMatrixMaker;
import graduate.cluster.TagDupRemover;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class ContentsBasedMatrixTest {
	
	@Autowired
	private ContentsBasedMatrixMaker cooccurrenceMatrixMaker;
	
	@Autowired
	private TagDupRemover tagDupRemover;
	
	@Test
	public void test(){
		int[][] contentsBasedMatrix = cooccurrenceMatrixMaker.make(tagDupRemover.getTagSet());
		
		for(int i = 0; i < contentsBasedMatrix.length; i++){
			for(int j = 0; j < contentsBasedMatrix[0].length; j++){
				System.out.print(contentsBasedMatrix[i][j]);	
			}
			System.out.println("");
		}
		
	}
}