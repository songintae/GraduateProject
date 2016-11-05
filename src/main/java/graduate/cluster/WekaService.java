package graduate.cluster;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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


//망한코드!
public class WekaService {
//  WekaDao dao;
  String[] options = new String[2];

  public void setOptions(String op1, String op2){
    options[0] = op1;
    options[1] = op2;
  }
//
//  public void setWekaDao(WekaDao dao){
//    this.dao = dao;
//  }
//
//  public void makeArff(){
//    Instances testSet = dao.getInstances();
//
//    BufferedWriter bufOut = new BufferedWriter(new OutputStreamWriter(
//      new FileOutputStream("busan.arff"), "UTF-8"));
//
//    bufOut.write(testSet.toString());
//    bufOut.close();
//  }

  public ClusterEvaluation analyze(String fileName) throws Exception{
    //파일에서 바로가져오기
    ArffLoader loder = new ArffLoader();
    loder.setFile(new File(fileName));
    Instances testSet = loder.getDataSet();

    SimpleKMeans cluster = new SimpleKMeans();
    cluster.setOptions(options);
    cluster.buildClusterer(testSet);
    
    ClusterEvaluation eval = new ClusterEvaluation();
    eval.setClusterer(cluster);
    eval.evaluateClusterer(testSet);
    return eval;
  }

  public static void main(String[] args) throws Exception {
    WekaService wekaService = new WekaService();
    wekaService.setOptions("-N", "30");
    ClusterEvaluation eval = wekaService.analyze("nonjeonju.arff");
    
    System.out.println(eval.clusterResultsToString());
    ArrayList<Tag> clusters[] = new ArrayList[eval.getNumClusters()];
    for(int i = 0; i<eval.getNumClusters() ; i++)
    {
      clusters[i] = new ArrayList<Tag>();
    }
    
    double[] result = eval.getClusterAssignments();
    for(int i = 0; i<result.length; i++)
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
    for(int i = 0; i<eval.getNumClusters() ; i++)
    {
      Collections.sort(clusters[i],sort);
    }
    
    System.out.println(eval.clusterResultsToString());
	BufferedWriter writer = new BufferedWriter(new FileWriter("center.txt"));

	writer.close();
	
    
    for(int i = 0; i<eval.getNumClusters() ; i++)
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
