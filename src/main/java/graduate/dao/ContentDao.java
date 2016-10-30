package graduate.dao;

import java.util.List;

import graduate.domain.Content;

public interface ContentDao {
	
	
	//실제 사용.
	public void add(Content content);
	public Content get(int id);
	public void delete(int id);
	public List<Content> getAll(String user_id);
	public List<Content> getAll();
	public int getLastId();
	
	
	//테스트용
	public void deleteAll();
	public int getCount();
	
}
