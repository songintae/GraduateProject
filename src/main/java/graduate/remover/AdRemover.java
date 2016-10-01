package graduate.remover;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import graduate.dao.ContentDao;

public class AdRemover implements Remover {

	ContentDao cDao;

	public void setContentDao(ContentDao cDao) {
		this.cDao = cDao;
	}

	public void remove(String AdDictionary){
		// TODO Auto-generated method stub
		BufferedReader in;
		try{
			 
			in = new BufferedReader(new FileReader(AdDictionary));
			String ad_var;
			
			while((ad_var = in.readLine()) != null){
			cDao.contentDeleteByText(ad_var);
			}
			in.close();
			
		}catch(IOException e){
			throw new RuntimeException(e);
		}

	}
	
}
