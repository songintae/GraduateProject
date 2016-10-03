package graduate.remover;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import graduate.dao.ContentDao;
import graduate.domain.Content;

public class AdRemover implements Remover {

	ContentDao cDao;

	public void setContentDao(ContentDao cDao) {
		this.cDao = cDao;
	}

	public void remove(String AdDictionary){
		// TODO Auto-generated method stub
		List<Content> contentList;
		Content content;
		BufferedReader in;
		try{
			contentList = cDao.getAll();
			Iterator itr = contentList.iterator();
			in = new BufferedReader(new FileReader(AdDictionary));
			String ad_var;
			
			while((ad_var = in.readLine()) != null){
				while(itr.hasNext()){
					content = (Content)itr.next();
					if(content.getText().contains(ad_var)){
						contentList.remove(content);
						cDao.delete(content.getId());
					}
				}
			}
			in.close();
		}catch(IOException e){
			throw new RuntimeException(e);
		}

	}
	
}
