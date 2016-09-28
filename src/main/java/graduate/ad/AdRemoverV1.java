package graduate.ad;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import graduate.dao.ContentDao;

public class AdRemoverV1 implements AdRemover {

	ContentDao cDao;  
	 
	 public AdRemoverV1(ContentDao cDao){  
	 	this.cDao = cDao;  
	 }  

	
	
	public boolean removeAd(String AdDictionary) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new FileReader(AdDictionary));  
			ArrayList<Integer> idList = new ArrayList<Integer>();
		 	String ad_var;
		 	
		 	while (true)   
		 	{  
		 		ad_var = in.readLine();  
		 		if (ad_var == null)  
		 			break;  
		  
		 		 idList = cDao.getAdList(ad_var);
		 		 Iterator<Integer> itr = idList.iterator();
		 		 while(itr.hasNext()){
		 			 cDao.delete(itr.next());
		 		 }
		 	}  
		 	in.close();  
		return false;
	}

	
}
