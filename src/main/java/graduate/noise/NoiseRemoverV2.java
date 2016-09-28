package graduate.noise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import graduate.dao.TagDao;

public class NoiseRemoverV2 implements NoiseRemover{
	TagDao tDao;
	
	public void setNoiseRemoverV2(TagDao tDao){
		this.tDao = tDao;
	}

	public boolean removeNoise(String noiseDictionary) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new FileReader(noiseDictionary));

		String noise_var;
		while (true) 
		{
			noise_var = in.readLine();
			if (noise_var == null)
				break;

			tDao.tagDelete(noise_var);
		}
		in.close();
		return false;
	}

}
