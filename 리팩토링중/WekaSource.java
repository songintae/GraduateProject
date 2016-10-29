import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import weka.clusterers.ClusterEvaluation;
import weka.clusterers.EM;
import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class SortResult {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost/instadata?autoReconnect=true&useSSL=false","root","song9207108");
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		stmt = conn.createStatement();
		String sql = "select * from (select tag , count(*) as count from busan where content_id in(select content_id from busan where tag = '부산') group by tag) as test1 where count >10;";
		rs = stmt.executeQuery(sql);
		rs.last();
		Tag tags[] = new Tag[rs.getRow()];
		System.out.println(rs.getRow());
		Integer clusterCount = (int)Math.sqrt(Double.valueOf((double)rs.getRow()/2));
		rs.first();
		int i = 0;
		do
		{
			tags[i] = new Tag(rs.getString(1),rs.getInt(2));
			i++;
		}while(rs.next());
		
//		Writer out = new FileWriter("tagData.txt");
//		BufferedWriter bufOut = new BufferedWriter(out);
//		for(i =0; i<tags.length;i++)
//		{
//			bufOut.write(tags[i].getTagName());
//			bufOut.newLine();
//			String tagNum = ""+tags[i].getTagNum();
//			bufOut.write(tagNum);
//			bufOut.newLine();
//		}
//		
//		bufOut.close();
//		
//
//		Reader in = new FileReader("tagData.txt");
//		BufferedReader bufIn = new BufferedReader(in);
//		
//		for(i = 0; i<tags.length;i++)
//		{	String tagName = null;
//			tagName = bufIn.readLine();
//			String tagNum= null;
//			tagNum = bufIn.readLine();
//			
//			System.out.println(tagName + " "+tagNum);
//		}
		
		//DB에서 바로 가져오기
//		Attribute[] attributes = new Attribute[tags.length];
//		
//		for(i = 0 ; i<attributes.length;i++)
//		{
//			String attributeString = (i+1)+"."+tags[i].getTagName();
//			attributes[i] = new Attribute(attributeString);
//		}
//		
//		FastVector finalAttribute = new FastVector(attributes.length);
//		for(i = 0; i<attributes.length;i++)
//		{
//			finalAttribute.addElement(attributes[i]);
//		}
//		
//		Instances testSet = new Instances("TestTen",finalAttribute,tags.length);
//		
//		for(i = 0 ; i<attributes.length; i++)
//		{
//			System.out.println(i);
//			Instance instance = new Instance(attributes.length);
//			
//		
//			sql = "select jeon.tag , CASE WHEN test.count IS NULL THEN 0 ELSE test.count END as count from (select * from (select tag , count(*) as count from busan where content_id in(select content_id from busan where tag = '부산') group by tag) as test1 where count >10) as jeon  LEFT outer join (select * from (select tag , count(*) as count from busan where content_id in(select content_id from busan where tag = '"+tags[i].getTagName()+"') group by tag) as test1 where count >10) as test ON jeon.tag = test.tag order by tag ;";
//			rs = stmt.executeQuery(sql);
//			for(int j = 0; j<attributes.length; j++)
//			{
//				rs.next();
//				double num = ((double)rs.getInt(2)/(double)tags[i].getTagNum())*100;
//				
//				instance.setValue((Attribute)attributes[j],(int)num );
//				
//			}
//			testSet.add(instance);
//		}
//		BufferedWriter bufOut = new BufferedWriter(new OutputStreamWriter(
//				new FileOutputStream("busanIs10.arff"), "UTF-8"));
//		
//		bufOut.write(testSet.toString());
//		bufOut.close();
		
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
		for(i = 0; i<eval.getNumClusters() ; i++)
		{
			Collections.sort(clusters[i],sort);
		}
		
		System.out.println(eval.clusterResultsToString());
		BufferedWriter writer = new BufferedWriter(new FileWriter("center.txt"));
		writer.write(cluster.getClusterCentroids().toString());
	
		writer.close();
		
		for(i = 0; i<eval.getNumClusters() ; i++)
		{
			System.out.print("cluster"+(i+1)+"(size:"+clusters[i].size()+")" +" : ");
			for(int j =0; j<clusters[i].size() ; j++)
			{
				System.out.print(clusters[i].get(j).getTagName()+"("+clusters[i].get(j).getTagNum()+")"+"  ");
			}
			System.out.println("");
		}
		

	}

}