package graduate.userTest;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import weka.clusterers.ClusterEvaluation;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class ClusterDaoTest {
	
	@Test
	public void test(){
		//파일에서 바로가져오기
		ArffLoader loder = new ArffLoader();
		loder.setFile(new File("busanIs10.arff"));
		Instances testSet = loder.getDataSet();
		
		String[] options = new String[2];
		options[0] = "-N";
		options[1] = String.valueOf(clusterCount);
		
		System.out.println(clusterCount);
		
//		EM cluster = new EM();
//		cluster.setOptions(options);
//		cluster.buildClusterer(testSet);
		
		SimpleKMeans cluster = new SimpleKMeans();
		cluster.setOptions(options);
		cluster.buildClusterer(testSet);

		ClusterEvaluation eval = new ClusterEvaluation();
		eval.setClusterer(cluster);
		eval.evaluateClusterer(testSet);
//		System.out.println(eval.clusterResultsToString());
//		System.out.println(cluster.getClusterCentroids().checkForStringAttributes());
		ArrayList<Tag> clusters[] = new ArrayList[eval.getNumClusters()];
		for(i = 0; i<eval.getNumClusters() ; i++)
		{
			clusters[i] = new ArrayList<Tag>();
		}
		
		double[] result = eval.getClusterAssignments();
		for(i = 0; i<result.length; i++)
		{
			clusters[(int)result[i]].add(tags[i]);
			
		}
		Comparator<Tag> sort = new Comparator<Tag>()
		{

			@Override
			public int compare(Tag o1, Tag o2) {
				// TODO Auto-generated method stub
				if(o1.getTagNum() > o2.getTagNum())
					return -1;
				else if(o1.getTagNum() == o2.getTagNum())
					return 0;
				else
					return 1;
				
			}
			
		};
		
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost/instadata?autoReconnect=true&useSSL=false","root","song9207108");
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		stmt = conn.createStatement();
		String sql = "insert into Cluster values (?,?,?,?,?);";
		for(int i = 0; i<eval.getNumClusters() ; i++)
		{
			Collections.sort(clusters[i],sort);
			//1:tag, 2:count, 3:location_code, 4:cluster_code, 5:id_num
			stmt.setString(1, clusters[i].getTag());
			stmt.setInt(2, clusters[i].getCount());
			stmt.setInt(3, clusters[i].getLocation_code());
			stmt.setInt(4, clusters[i].getCluster_code());
			stmt.setInt(5, clusters[i].getId_num());
		}
		
		
	}
}
