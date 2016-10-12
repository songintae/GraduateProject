package graduate.wekaTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import graduate.cluster.ArffMaker;
import graduate.cluster.ContentsBasedMatrixMaker;
import graduate.cluster.TagDupRemover;
import graduate.domain.TagWithCount;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class ArffMakerTest {
	@Autowired
	private ArffMaker arffMaker;
	@Autowired
	private ContentsBasedMatrixMaker contentsBasedMatrixMaker;
	@Autowired
	private TagDupRemover tagDupRemover;
	
	@Test
	public void test(){
		List<TagWithCount> noDupTags = tagDupRemover.getTagSet();
		int[][] contentsBasedMatrix = contentsBasedMatrixMaker.make(noDupTags);
		try {
			arffMaker.setTags(noDupTags);
			arffMaker.make(contentsBasedMatrix);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
