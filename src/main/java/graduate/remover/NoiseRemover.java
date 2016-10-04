package graduate.remover;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import graduate.dao.TagDao;
import graduate.domain.Content;
import graduate.domain.Tag;

public class NoiseRemover implements Remover {
	TagDao tDao;
	String noisePath;
	

	public void setTagDao(TagDao tDao) {
		this.tDao = tDao;
	}
	
	public void setNoisePath(String noisePath){
		this.noisePath = noisePath;
	}

	public void remove() {
		// TODO Auto-generated method stub
		List<Tag> tagList;
		BufferedReader in;
		try {
			tagList = tDao.getAll();
			ArrayList<Tag> deleteTag = new ArrayList<Tag>();
			in = new BufferedReader(new FileReader(noisePath));
			String noise_var;
			
			while ((noise_var = in.readLine()) != null) {
				for(Tag tag : deleteTag){
					tagList.remove(tag);
				}
				deleteTag.clear();
				for(Tag tag : tagList){
					if(checkSameTag(tag.getTag(),noise_var)){
						tDao.delete(tag.getTag_id());
						deleteTag.add(tag);
					}
				}
			}
			in.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
	
	protected boolean checkSameTag(String validation , String expected) throws UnsupportedEncodingException{
		if(validation.equals(expected)){
			return true;
		}else{
			return false;
		}
	}
	
	
	

}
