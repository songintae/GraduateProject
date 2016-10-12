package graduate.wekaTest;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import graduate.cluster.TagDupRemover;
import graduate.domain.TagWithCount;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TagDupRemoverTest {
	
	@Autowired
	TagDupRemover tagDupRemover;
	
	@Test
	public void test(){
		List<TagWithCount> tagWithCntList = tagDupRemover.getTagSet();
		for (TagWithCount twc : tagWithCntList){
			System.out.println(twc.getTag() +"/"+twc.getCount());
		}
	}
}