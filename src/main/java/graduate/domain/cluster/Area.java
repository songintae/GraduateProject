package graduate.domain.cluster;

public enum Area {
	
	GANGNEUNG(1,"강릉"),GYEONGJU(2,"경주"),GUNSAN(3,"군산"),DANYANG(4,"단양"),DAEGU(5,"대구")
	,BOSEONG(6,"보성"),BUSAN(7,"부산"),SEOUL(8,"서울"),SUNCHEON(9,"순천"),ANDONG(10,"안동")
	,JEONJU(11,"전주"),JEONGDONGJIN(12,"정동진");
	
	
	private final int intCode;
	private final String area;
	
	Area(int code , String area)
	{
		this.intCode = code;
		this.area = area;
	}

	public int getIntCode() {
		return intCode;
	}

	public String getArea() {
		return area;
	}
	
	public static Area valueOf(int code){
		switch(code){
		case 1 : return GANGNEUNG;
		case 2 : return GYEONGJU; 
		case 3 : return GUNSAN;
		case 4 : return DANYANG;
		case 5 : return DAEGU;
		case 6 : return BOSEONG;
		case 7 : return BUSAN;
		case 8 : return SEOUL;
		case 9 : return SUNCHEON;
		case 10 : return ANDONG;
		case 11 : return JEONJU;
		case 12 : return JEONGDONGJIN;
		default: throw new AssertionError("Unknown value : "+ code);
		}
	}
	
	public static Area getStringToArea(String region){
		region = region.toLowerCase();
		if(region.equals("gangneung"))
			return GANGNEUNG;
		else if(region.equals("gyeongju"))
				return GYEONGJU;
		else if(region.equals("gUNSAN"))
			return GUNSAN;
		else if(region.equals("danyang"))
			return DANYANG;
		else if(region.equals("daegu"))
			return DAEGU;
		else if(region.equals("boseong"))
			return BOSEONG;
		else if(region.equals("busan"))
			return BUSAN;
		else if(region.equals("seoul"))
			return SEOUL;
		else if(region.equals("suncheon"))
			return SUNCHEON;
		else if(region.equals("andong"))
			return ANDONG;
		else if(region.equals("jeonju"))
			return JEONJU;
		else if(region.equals("jeongdongjin"))
			return JEONGDONGJIN;
		else
			throw new AssertionError("Unknown value : "+ region);
	
	}
	
	
}
