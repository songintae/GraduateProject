package graduate.remover;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import graduate.dao.ContentDao;
import graduate.domain.Content;

public class AdRemover implements Remover {

	ContentDao cDao;
	String adRemoverPath;

	public void setContentDao(ContentDao cDao) {
		this.cDao = cDao;
	}
	public void setAdRemoverPath(String adRemoverPath){
		this.adRemoverPath = adRemoverPath;
	}

	public void remove(){
		// TODO Auto-generated method stub
		List<Content> contentList;
		Content content;
		BufferedReader in;
		try{
			contentList = cDao.getAll();
			List<Content> deleteContent = new ArrayList<Content>();
			Iterator itr;
			in = new BufferedReader(new FileReader(adRemoverPath));
			String ad_var;
			
			while((ad_var = in.readLine()) != null){
				itr = contentList.iterator();
				while(itr.hasNext()){
					content = (Content)itr.next();
					if(content.getText().contains(ad_var)){
						cDao.delete(content.getId());
						deleteContent.add(content);
					}
				}
				for(Content delContent : deleteContent){
					contentList.remove(delContent);
				}
				deleteContent.clear();
			}
			in.close();
		}catch(IOException e){
			throw new RuntimeException(e);
		}

	}
	
}
