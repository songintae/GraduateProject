package graduate.dao;

import java.util.List;

import graduate.domain.Tag;

public interface TagDao {
	
	public void add(Tag tag);
	public Tag get(int id);
	public List<Tag> getAll();
	public void delete(int id);
	public void deleteAll();
	public int getLastId();
	
	
	
	//테스트용
	public int getCount();
	
	
	
	
	/*
	 	//////////////////////////////////
	 	   1.add  //함수명
	 	   2.     //파라미터
	 	   3.     //동작 내용
	 	   4.     //return
	 	 /////////////////////////////////
	 	  
	 	  //////////////////////////////////
	 	   1.add  //함수명
	 	   2.     //파라미터
	 	   3.     //동작 내용
	 	   4.     //return
	 	 /////////////////////////////////
	 */
}
