package graduate.dao;

import graduate.domain.Content;

public interface ContentDao {
	
	public void add(Content content);
	public Content get(int id);
	public void delete(int id);
}
