package graduate.cluster;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import graduate.domain.cluster.Area;

import static org.hamcrest.CustomMatcher.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class CluserTest {

	@Autowired
	ArffToDatabase arffToDatabase;
	
}
