package graduate.instagram;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import graduate.domain.Content;
import graduate.domain.Tag;



public class JsonParseData implements ParseData {
	
	private String next_url;
	private InstaService instaService;
	
	public void setInstaService(InstaService instaService){
		this.instaService = instaService;
	}
	public String registryData(String jsonData) {
		// TODO Auto-generated method stub
		int tag_id = 0;
		try{
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject)parser.parse(jsonData);
			JSONObject pagination = (JSONObject)obj.get("pagination");
			next_url = (String)pagination.get("next_url");
			JSONArray data = (JSONArray)obj.get("data");
			
			for(int i = 0; i<data.size(); i++){
				Content content = new Content();
				JSONObject content_data = (JSONObject)data.get(i);
				
				JSONObject likes = (JSONObject)content_data.get("likes");
				
//				int num_like = Integer.parseInt(likes.get("count").toString());
//				content.setId(i);
//				content.setNum_like(num_like);
//				
//				JSONArray tags = (JSONArray)content_data.get("tags");
//				int num_tag = tags.size();
//				content.setNum_tag(num_tag);
//				
//				JSONObject caption = (JSONObject)content_data.get("caption");
//				String text = " ";
//				if(caption !=null)
//				 text = (String)caption.get("text");
//				text = (((text.replace("\"", "")).replace("\'", "")).replace("\n", "")).replace("?","");
//				content.setText(text);
//				
//				
//				Set<Tag> newTags = new HashSet<Tag>();
//				
//				for(int j = 0 ; j<tags.size() ; j++,tag_id++)
//				{
//					Tag newTag = new Tag();
//					newTag.setContent(content);
//					
//					String tag = " ";
//					if((String)tags.get(j)!=null)
//						tag = (String)tags.get(j);
//					
//					newTag.setContent_id(i);
//					newTag.setTag_id(tag_id);
//					newTag.setTag(tag);
//					
//					newTags.add(newTag);
//					
//					instaService.registryTag(newTag);
//					
//					
//				}
//				content.setTags(newTags);
//				instaService.registryContent(content);
//				
//			}
//			return next_url;
//		}catch(ParseException e){
//			throw new RuntimeException(e);
//		}
		
		
	}

}
