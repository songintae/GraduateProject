package graduate.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Content {
	private int id;
	private int num_like;
	private int num_tag;
	private	String text;
	
	private List<Tag> tags;
	
	
	
	public Content(){
		
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public Content(int id, int num_like , int num_tag , String text){
		this.id = id;
		this.num_like = num_like;
		this.num_tag = num_tag;
		this.text = text;
	};
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNum_like() {
		return num_like;
	}
	public void setNum_like(int num_like) {
		this.num_like = num_like;
	}
	public int getNum_tag() {
		return num_tag;
	}
	public void setNum_tag(int num_tag) {
		this.num_tag = num_tag;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	
}
