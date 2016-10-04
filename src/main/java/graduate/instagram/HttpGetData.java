package graduate.instagram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpGetData implements GetData{
	private ParseData parseData;
	
	public void setParseData(ParseData parseData){
		this.parseData = parseData;
	}
	
	
	
	public String getData(String url)  {
		// TODO Auto-generated method stub
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(url);
		getRequest.addHeader("accept","application/json");
		
		
		try{
			HttpResponse response  = httpClient.execute(getRequest);
			BufferedReader br = new BufferedReader( new InputStreamReader((response.getEntity().getContent())));
			String output = null;
			String json = null;
			while((output = br.readLine())!= null){
				json = output;
				
			}
			
		
			return parseData.registryData(json);
			
			
		}catch(ClientProtocolException e){
			throw new RuntimeException(e);
		}catch(IOException e){
			throw new RuntimeException(e);
		}
		finally{
			httpClient.getConnectionManager().shutdown();
		}
		
		
	}

}
