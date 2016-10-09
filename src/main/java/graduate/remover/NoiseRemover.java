package graduate.remover;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.codec.CharEncoding;
import org.apache.http.util.CharsetUtils;

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
				for(Tag tag : tagList)
				{
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
		String Sbvalidation = new String(validation.getBytes("utf-8"), "utf-8");
		String SbExpected = new String(expected.getBytes("utf-8"), "utf-8");
//		System.out.println(Sbvalidation + " " + SbExpected + " " +Sbvalidation.equals(SbExpected));

		if(validation.equals(expected)){
			return true;
		}else{
			return false;
		}
	}
	
	
	

}
