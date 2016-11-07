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

   public void setPlatFormDataService(PlatFormDataService platFormDataService) {
      this.platFormDataService = platFormDataService;
   }

   private double getSumOfTagCount(Area area) {
      List<Attribute> attributes = platFormDataService.getAttribute(area.getIntCode());
      double sum = 0.0;
      for (Attribute attribute : attributes) {
         sum += attribute.getCount();
      }
      return sum;
   }



   @Override
   public void settingTf(String name) {
      Area area = Area.getStringToArea(name);

      List<Attribute> attributes = platFormDataService.getAttribute(area.getIntCode());
      double sum = getSumOfTagCount(area);
      for (Attribute attribute : attributes) {
         attribute.setTf_score(attribute.getCount() / sum);
         // platFormDataService.attributeUpdate(attribute);
//         System.out.println(attribute.getTag() + " : " + attribute.getTf_score());
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

   private double countTagInArea(List<Area> areas, String tag) {
      List<Attribute> attributes;
      double count = 0.0;
      for (Area area : areas) {
         attributes = platFormDataService.getAttribute(area.getIntCode());
//         System.out.println(area.getArea()+"의 어트리뷰트개수 : "+attributes.size());
         for (Attribute attribute : attributes) {
//            System.out.println(attribute.getTag() +"/"+ tag);
            if (attribute.getTag().equals(tag)) {
//               System.out.println("들어옴");
               count++;
               break;
            }
         }
      }

      return count;
   }
   
   @Override
   public void tfIdf(List<String> strAreas, String strArea) {
      double idfCount = 0.0;

      List<Cluster> clusters;

      List<Area> areas = new ArrayList<Area>();
      // areas 초기화
      for (String area : strAreas) {
         areas.add(Area.getStringToArea(area));
      }

      // 점수 매길 area의 태그들
      Area area = Area.getStringToArea(strArea);
      List<Attribute> attributes = platFormDataService.getAttribute(area.getIntCode());

      
      
      //tf 구하기
      double sum = getSumOfTagCount(area);
      System.out.println("sum : " + sum);
      for (Attribute attribute : attributes) {
         attribute.setTf_score(attribute.getCount() / sum);
         //idf구하기
         idfCount = countTagInArea(areas, attribute.getTag());
         attribute.setIdf_score(idfCount);
//         System.out.println(attribute.getTag() + " : " + attribute.getTf_score());
//         System.out.println(attribute.getTag() + " : " + idfCount);
         if (idfCount > 0.0) {
            //attribute.setIdf_score(Math.log10(((double) strAreas.size()) / (idfCount*10)));
            attribute.setIdf_score((double) strAreas.size() / (idfCount*10));
         } else {
            //System.out.println("들어옴");
            attribute.setIdf_score(1.0);
         }
         attribute.setTf_idf_score(attribute.getTf_score() * attribute.getIdf_score() *1000);
         //System.out.println(attribute.getTag() + " : " + attribute.getTf_idf_score());      
         platFormDataService.attributeUpdate(attribute);      
      }
      

   }
}