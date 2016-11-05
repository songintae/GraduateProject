package graduate.domain;

public class TagWithCount implements Comparable<TagWithCount> {
	String tag;
	int count;
	
	public TagWithCount(){
		
	}
	
	public TagWithCount(String tag, int count){
		this.tag = tag;
		this.count = count;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int compareTo(TagWithCount tag) {
		// TODO Auto-generated method stub
		if (count > tag.count)
			return 1;
		else if (count < tag.count)
			return -1;
		else
			return 0;
	}

}
