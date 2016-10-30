package graduate.instagram;

import graduate.domain.Content;
import graduate.domain.Tag;
import graduate.domain.User;

public interface InstaService {
	
	//Content DB에 저장.
	
	public int getContentLastId();
	public int getTagLastId();
	public void registryContent(Content content);
	public void registryUser(User user);
	
	//Tag DB에 저장.
	public void registryTag(Tag tag);
	/*
	 *url을 parameter로 전달(사용자/지역 구분 없음).
	 *함수기능
	 *1.api를 통해 인스타 데이터 파싱후 데이터 DB에 저장까지 동작.
	 */
	public void GetAndRegistryData(String url, String user_id);
	
}
