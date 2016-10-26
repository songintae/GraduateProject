package graduate.cluster;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import graduate.domain.TagWithCount;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class BasicArffMaker implements ArffMaker {
	
	private List<TagWithCount> noDupTags;
	
	public void setTags(List<TagWithCount> noDupTags){
		this.noDupTags = noDupTags;
	}

	public void make(int[][] matrix) throws Exception{
		int rowNum = matrix.length;
		int colNum = matrix[0].length;
		
		Attribute[] attributes = new Attribute[colNum];
		
		// 태그이름들을 1.태그 이런식으로 다시 저장
		if(noDupTags == null){
			for (int i = 0; i < colNum; i++) {
				String attributeString = (i + 1) + "." + "태그" + (i + 1);
				attributes[i] = new Attribute(attributeString);
			}
		}else{
			colNum = noDupTags.size();
			for (int i = 0; i < colNum; i++) {
				String attributeString = (i + 1) + "." + noDupTags.get(i).getTag();
				attributes[i] = new Attribute(attributeString);
			}
		}
		
		// 웨카 라이브러리에서 제공하는 클래스.
		FastVector fastVector = new FastVector(rowNum);

		for (int i = 0; i < colNum; i++) {
			fastVector.addElement(attributes[i]); 
		}
		
		Instances instances = new Instances("UserProfile", fastVector, colNum);

		for (int i = 0; i < rowNum; i++) {
			Instance instance = new Instance(colNum);
			for (int j = 0; j < colNum; j++) {
				instance.setValue((Attribute) attributes[j], matrix[i][j]);
			}
			instances.add(instance);
		}

		BufferedWriter bufOut = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("user.arff"), "UTF-8"));

		bufOut.write(instances.toString());
		bufOut.close();
	}
}
