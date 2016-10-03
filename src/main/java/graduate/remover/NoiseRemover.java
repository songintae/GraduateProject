package graduate.remover;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import graduate.dao.TagDao;
import graduate.domain.Content;
import graduate.domain.Tag;

public class NoiseRemover implements Remover {
	TagDao tDao;

	public void setTagDao(TagDao tDao) {
		this.tDao = tDao;
	}

	public void remove(String noiseDictionary) {
		// TODO Auto-generated method stub
		ArrayList<Tag> tagList = new ArrayList<Tag>();
		Tag tag;
		BufferedReader in;
		try {
			tagList = tDao.getAll();
			Iterator itr = tagList.iterator();
			in = new BufferedReader(new FileReader(noiseDictionary));
			String noise_var;

			while ((noise_var = in.readLine()) != null) {
				while (itr.hasNext()) {
					tag = (Tag) itr.next();
					if (tag.getTag() == noise_var) {
						tagList.remove(tag);
						tDao.delete(tag.getTag_id());
					}
				}
			}
			in.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
