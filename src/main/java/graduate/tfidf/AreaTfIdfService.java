package graduate.tfidf;

import java.util.List;

public interface AreaTfIdfService {
	
	//tf는 지역에서 태그마다 몇번나왔는지 세어놓으면 되니까 tf점수해놓을 지역만 넘겨받으면 됨. 
	public void settingTf(String area);
	
	//idf는 지역간 비교이니까, 인자로 비교할 지역들 리스트랑  비교대상 지역을 넣어줘야 함
	public void settingIdf(List<String> areas, String area);
	
	//tf-idf는 tf점수와 idf점수를 둘다 갖고 있는 태그들을 다 점수매김.
	public void settingTfIdf(List<String> areas);
	
	//tf-idf 점수 한방에 다 하는거
	public void tfIdf(List<String> areas, String area);

}
