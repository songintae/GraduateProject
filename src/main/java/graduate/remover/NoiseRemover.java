package graduate.remover;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import graduate.dao.TagDao;

public class NoiseRemover implements Remover{
	TagDao tDao;
	
	public void setTagDao(TagDao tDao){
		this.tDao = tDao;
	}

	public void remove(String noiseDictionary) {
		// TODO Auto-generated method stub
		BufferedReader in = null;
		try{
			 in = new BufferedReader(new FileReader(noiseDictionary));

			String noise_var;
			while (true) 
			{
				noise_var = in.readLine();
				if (noise_var == null)
					break;

				tDao.tagDelete(noise_var);
			}
			
			in.close();
			
		}catch(IOException e){
			throw new RuntimeException(e);
		}


		
	}

}
