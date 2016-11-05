package graduate.tfidf;

import java.util.ArrayList;
import java.util.List;

import graduate.dao.cluster.AttributeDao;
import graduate.dao.cluster.ClusterDao;
import graduate.domain.cluster.Area;
import graduate.domain.cluster.Attribute;
import graduate.domain.cluster.Cluster;
import graduate.platformdataservice.PlatFormDataService;

public class BasicAreaTfIdf implements AreaTfIdfService {

	private PlatFormDataService platFormDataService;
	private ClusterDao clusterDao;
	private AttributeDao attributeDao;

	public void setClusterDao(ClusterDao clusterDao) {
		this.clusterDao = clusterDao;
	}

	public void setAttributeDao(AttributeDao attributeDao) {
		this.attributeDao = attributeDao;
	}

	public void setPlatFormDataService(PlatFormDataService platFormDataService) {
		this.platFormDataService = platFormDataService;
	}

	private double getSumOfTagCount(List<Cluster> clusters) {
		double sum = 0.0;
		for (Cluster cluster : clusters) {
			sum += cluster.getCount();
		}
		return sum;
	}

	private double countTagInArea(List<Area> areas, String tag) {
		List<Attribute> attributes;
		double count = 0.0;
		for (Area area : areas) {
			attributes = platFormDataService.getAttribute(area.getIntCode());
			for (Attribute attribute : attributes) {
				if (attribute.getTag().equals(tag)) {
					count++;
					break;
				}
				System.out.println(attribute.getTag() + "/count : "+count);
			}
		}
		
		return count;
	}

	@Override
	public void settingTf(String name) {
		Area area = Area.getStringToArea(name);

		List<Cluster> clusters = platFormDataService.getClusters(area.getIntCode());
		double sum = getSumOfTagCount(clusters);
		for (Cluster cluster : clusters) {
			List<Attribute> attributes = cluster.getAttributes();
			for (Attribute attribute : attributes) {
				attribute.setTf_score(attribute.getCount() / sum);
				// platFormDataService.attributeUpdate(attribute);
				System.out.println(attribute.getTag() + " : " + attribute.getTf_score());
			}
		}
	}

	@Override
	public void settingIdf(List<String> strAreas, String strArea) {
		List<Area> areas = new ArrayList<Area>();
		// areas 초기화
		for (String area : strAreas) {
			areas.add(Area.getStringToArea(area));
		}

		// 점수 매길 area의 태그들
		Area area = Area.getStringToArea(strArea);
		List<Attribute> attributes = platFormDataService.getAttribute(area.getIntCode());

		Attribute tag = null;
		double idfCount = 0.0;
		for (Attribute attribute : attributes) {
			tag = attribute;
			idfCount = countTagInArea(areas, attribute.getTag());
			if (idfCount > 0) {
				tag.setIdf_score(Math.log10((double) strAreas.size() / idfCount));
				platFormDataService.attributeUpdate(tag);
			}
		}
	}

	@Override
	public void settingTfIdf(List<String> strAreas) {
		List<Area> areas = new ArrayList<Area>();
		for (String strArea : strAreas) {
			areas.add(Area.getStringToArea(strArea));
		}

		for (Area area : areas) {
			List<Attribute> attributes = platFormDataService.getAttribute(area.getIntCode());
			for (Attribute attr : attributes) {
				attr.setTf_idf_score(attr.getTf_score() * attr.getIdf_score());
				platFormDataService.attributeUpdate(attr);
			}
		}
	}

	// private double countTagInArea(List<Area> areas, String tag) {
	// List<Attribute> attributes;
	// double count = 0.0;
	//
	// List<Cluster> clusters = clusterDao.getAll();
	//
	// for(Area area : areas){
	// for(Cluster cluster : clusters){
	// attributes = attributeDao.getAll(cluster.getCluster_id());
	// if(cluster.getArea().getArea().equals(area.getArea())){
	// for (Attribute attribute : attributes) {
	// if (attribute.getTag().equals(tag)){
	// count++;
	// break;
	// }
	// }
	// }
	// }
	// }
	// System.out.println(tag + " count : " + count);
	// return count;
	// }

	@Override
	public void tfIdf(List<String> strAreas, String strArea) {
		List<Cluster> clusters = platFormDataService.getAllCluster();

		List<Area> areas = new ArrayList<Area>();
		// areas 초기화
		for (String area : strAreas) {
			areas.add(Area.getStringToArea(area));
		}

		// 점수 매길 area의 태그들
		Area area = Area.getStringToArea(strArea);
		List<Attribute> attributes = null;

		double sum = getSumOfTagCount(clusters);
		for (Cluster cluster : clusters) {
			if (cluster.getArea().getArea().equals(area.getArea())) { // 지역 클러스터 같으면
				attributes = platFormDataService.getAttribute(cluster.getCluster_id());
				double idfCount = 0.0;
				for (Attribute attribute : attributes) {
					attribute.setTf_score(attribute.getCount() / sum);
					idfCount = countTagInArea(areas, attribute.getTag());
					if (idfCount > 0) {
						attribute.setIdf_score(Math.log10(strAreas.size() / idfCount));
						attribute.setTf_idf_score(attribute.getTf_score() * attribute.getIdf_score());
					}
					System.out.println(attribute.getTag() + " : " + attribute.getTf_idf_score());
				}
			}
		}
	}

}
