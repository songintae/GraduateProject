package graduate.domain;

public class Tag {
	int tag_id;
	int content_id;
	String tag;
	
	Content content;
	
	public Tag(){};
	public Tag(int tag_id , int content_id, String tag){
		this.tag_id = tag_id;
		this.content_id = content_id;
		this.tag = tag;
	}
	
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
	public int getTag_id() {
		return tag_id;
	}
	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}
	public int getContent_id() {
		return content_id;
	}
	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
}
