package graduate.noise;

import java.io.IOException;

public interface NoiseRemover {
	public boolean removeNoise(String noiseDictionary) throws IOException;
}
