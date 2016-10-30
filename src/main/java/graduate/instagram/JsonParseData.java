package graduate.instagram;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.codec.CharEncoding;
import org.apache.http.util.CharsetUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import graduate.dao.ContentDao;
import graduate.dao.TagDao;
import graduate.domain.Content;
import graduate.domain.Tag;
import graduate.domain.User;



public class JsonParseData implements ParseData {
	
	private String next_url;
	private InstaService instaService;
	private RemoveSurrogateArea surrogate;
	public void setInstaService(InstaService instaService){
		this.instaService = instaService;
	}
	public void setRemoveSurrogateArea(RemoveSurrogateArea surrogate){
		this.surrogate = surrogate;
	}
	
	
	public String registryData(String jsonData , String user_id) {
		// TODO Auto-generated method stub
		
		int tag_id = instaService.getTagLastId()+ 1;
		int content_id = instaService.getContentLastId()+ 1;
		User user = new User();
		user.setUser_id(user_id);
		instaService.registryUser(user);
		try{
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject)parser.parse(jsonData);
			JSONObject pagination = (JSONObject)obj.get("pagination");
			next_url = (String)pagination.get("next_url");
			JSONArray data = (JSONArray)obj.get("data");
			
			for(int i = 0; i<data.size(); i++,content_id++){
				Content content = new Content();
				content.setUser_id(user_id);
				JSONObject content_data = (JSONObject)data.get(i);
				
				JSONObject likes = (JSONObject)content_data.get("likes");
				
				int num_like = Integer.parseInt(likes.get("count").toString());
				content.setId(content_id);
				content.setNum_like(num_like);
				
				JSONArray tags = (JSONArray)content_data.get("tags");
				int num_tag = tags.size();
				content.setNum_tag(num_tag);
				
				JSONObject caption = (JSONObject)content_data.get("caption");
				String text = " ";
				if(caption !=null)
				 text = (String)caption.get("text");
				text = (((text.replace("\"", "")).replace("\'", "")).replace("\n", "")).replace("?","");
				
				
				content.setText(this.surrogate.getRemoveSurrogateArea(text));

				
			
				instaService.registryContent(content);
				
				for(int j = 0 ; j<tags.size() ; j++,tag_id++)
				{
					Tag newTag = new Tag();
					newTag.setContent(content);
					
					String tag = " ";
					if((String)tags.get(j)!=null)
						tag = (String)tags.get(j);
					
					
					newTag.setContent_id(content.getId());
					newTag.setTag_id(tag_id);
					newTag.setTag(this.surrogate.getRemoveSurrogateArea(tag));
					
			
					
					instaService.registryTag(newTag);
					
					
				}
			
				
				
			}
			return next_url;
		}catch(ParseException e){
			throw new RuntimeException(e);
		}
		
		
	}

}
