package graduate.cluster;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.sql.DataSource;

import graduate.dao.cluster.AttributeDao;
import graduate.dao.cluster.ClusterDao;
import graduate.domain.cluster.Area;
import graduate.domain.cluster.Attribute;
import graduate.domain.cluster.Cluster;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

class Tag
{
	private String tagName;
	private int tagNum;
	
	
	public Tag(String name , int num)
	{
		this.tagName = name;
		this.tagNum = num;
	}
	
	public String getTagName()
	{
		return this.tagName;
	}
	public int getTagNum()
	{
		return this.tagNum;
	}
}





public class BasicArffToDatabase implements ArffToDatabase {
	
	private DataSource instaDataSource;
	private ClusterDao clusterDao;
	private AttributeDao attributeDao;
	
	public void setInstaDataSource(DataSource dataSource){
		this.instaDataSource = dataSource;
	}
	public void setClusterDao(ClusterDao clusterDao){
		this.clusterDao = clusterDao;
	}
	public void setAttributeDao(AttributeDao attributeDao){
		this.attributeDao = attributeDao;
	}
	
	
	
	public void arffToDatabase(String filePath, String region) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		Area area = Area.getStringToArea(region);
		try{
			conn = this.instaDataSource.getConnection();
			
			
			
			stmt = conn.createStatement();
			String sql = "select * from (select tag , count(*) as count from "+region+" where content_id in(select content_id from "+region+" where tag = '"+area.getArea()+"') group by tag) as test1 where count >10;";
			rs = stmt.executeQuery(sql);
			rs.last();
			Tag tags[] = new Tag[rs.getRow()];
			Integer clusterCount = (int)Math.sqrt(Double.valueOf((double)rs.getRow()/2));
			rs.first();
			int i = 0;
			do
			{
				tags[i] = new Tag(rs.getString(1),rs.getInt(2));
				i++;
			}while(rs.next());
			
			ArffLoader loder = new ArffLoader();
			loder.setFile(new File(filePath));
			Instances testSet = loder.getDataSet();
			
			String[] options = new String[2];
			options[0] = "-N";
			options[1] = String.valueOf(clusterCount);
			
			SimpleKMeans cluster = new SimpleKMeans();
			cluster.setOptions(options);
			cluster.buildClusterer(testSet);

			ClusterEvaluation eval = new ClusterEvaluation();
			eval.setClusterer(cluster);
			eval.evaluateClusterer(testSet);
			
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
			
			for(i = 0; i<eval.getNumClusters() ; i++)
			{
				Cluster newCluster = new Cluster();
				newCluster.setArea(area);
				newCluster.setCluster_id(i+1);
				newCluster.setCount(clusters[i].size());
				clusterDao.add(newCluster);
				for(int j =0; j<clusters[i].size() ; j++)
				{
					Attribute newAttribute = new Attribute();
					newAttribute.setCluster_id(clusterDao.getLastId());
					newAttribute.setCount(clusters[i].get(j).getTagNum());
					newAttribute.setTag(clusters[i].get(j).getTagName());
					
					attributeDao.add(newAttribute);
				}
				
			}
			
			
			
		}catch(SQLException e){
			try
			{
				rs.close();
				ps.close();
				conn.close();
			}catch(SQLException ex){
				throw new RuntimeException(e);
			}
			
			throw new RuntimeException(e);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
			
	}
		
	
}
