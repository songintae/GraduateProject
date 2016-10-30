package graduate.cluster;

public interface ArffToDatabase {
	
	//arff 파일 경로 및 지역이름(영어로(뷰이름이랑 동일하게) 대소문자 구분 x)
	public void arffToDatabase(String filePath, String region);
}
