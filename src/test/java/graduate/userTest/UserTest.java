package graduate.userTest;

import java.util.List;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import graduate.dao.cluster.AttributeDao;
import graduate.domain.TagWithCount;
import graduate.domain.cluster.Attribute;
import graduate.domain.cluster.Cluster;
import graduate.userservice.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class UserTest {

	@Autowired
	UserService userService;
	@Autowired
	AttributeDao attributeDao;

	@Test
	public void userTest() {
		
		// 사용자가 사용한 태그와 빈도수의 리스트
		List<TagWithCount> twcList = userService.getTagCount();
		
		List<Cluster> recommandedClusterList = userService.getRecommandedClusters(twcList);

		// 이걸 json 형태로 바꿔서 지혜한테 넘겨야 함.
		List<Attribute> attrs;
		for (int i = 0; i < recommandedClusterList.size(); i++) {
			attrs = userService.getAttributes(recommandedClusterList.get(i).getCluster_id());
			System.out.println("cluster" + i + " : ");
			for (Attribute attr : attrs) {
				if (attr.getCount() > 10)
					System.out.print(attr.getTag() + ",");
			}
			System.out.println("");
		}
	}
}
