package graduate.userservice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import graduate.dao.cluster.AttributeDao;
import graduate.dao.cluster.ClusterDao;
import graduate.domain.Tag;
import graduate.domain.TagWithCount;
import graduate.domain.cluster.Attribute;
import graduate.domain.cluster.Cluster;
import graduate.platformdataservice.PlatFormDataService;

public class BasicUserService implements UserService {

   private PlatFormDataService platFormDataService;
   private ClusterDao clusterDao;
   private AttributeDao attributeDao;

   public void setPlatFormDataService(PlatFormDataService platFormDataService) {
      this.platFormDataService = platFormDataService;
   }

   public void setClusterDao(ClusterDao clusterDao) {
      this.clusterDao = clusterDao;
   }

   public void setAttributeDao(AttributeDao attributeDao) {
      this.attributeDao = attributeDao;
   }

   public List<TagWithCount> getTagCount() {
      List<Tag> tagList = platFormDataService.getAllTags();
      HashMap<String, Integer> tagCount = new HashMap<String, Integer>();
      List<TagWithCount> tagWithCountList = new ArrayList<TagWithCount>();

      // System.out.println(tagList.size());
      for (Tag tagObject1 : tagList) {
         int count = 0;
         if (tagCount.get(tagObject1.getTag()) == null) {
            for (Tag tagObject2 : tagList) {
               if (tagObject1.getTag().equals(tagObject2.getTag()))
                  count++;
            }
            tagCount.put(tagObject1.getTag(), count);
         }
         String tagName = tagObject1.getTag();
         tagWithCountList.add(new TagWithCount(tagName, tagCount.get(tagName)));
      }
      return tagWithCountList;
   }

   //TF점수가 적용된 태그리스트를 받아옴. 
   //size를 넘기면 상위랭크부터 size개수만큼 가져옴. size에 음수값을 넘기면 정렬만해서 넘김.
   public List<TagWithCount> getTfTags(List<TagWithCount> twcList, int size) {
      List<TagWithCount> twcs = new ArrayList<TagWithCount>();

      // 사용자 해시태그 TF - 사용자가 많이 사용한 태그 순으로 정렬
      twcList.sort(new Comparator<TagWithCount>() {
         @Override
         public int compare(TagWithCount o1, TagWithCount o2) {
            if (o1.getCount() > o2.getCount())
               return -1;
            else if (o1.getCount() == o2.getCount())
               return 0;
            else
               return 1;
         }
      });

      if (size < 0) {
         return twcList;
      } else {
         for (int i = 0; i < size; i++) {
            twcs.add(twcList.get(i));
         }
      }
      return twcs;
   }

   @Override
   public List<Cluster> getRecommandedClusters(List<TagWithCount> twcs) {
      // 사용자가 사용한 태그와 빈도수의 리스트
      List<TagWithCount> twcList = getTagCount();

      // 사용자 해시태그 TF - 사용자가 많이 사용한 태그 순으로 정렬
      twcList = getTfTags(twcList, -1);

      double mean = getMeans(twcList);

      // 지역 클러스터들
      List<Cluster> clusterList = clusterDao.getAll();
      // 추천 클러스터 리스트
      List<Cluster> recommandedClusterList = new ArrayList<Cluster>();

      List<Attribute> attrList; // for문 돌면서 여기에 속성리스트 계속 업데이트.
      for (int i = 0; i < clusterList.size(); i++) { // 지역클러스터 전부에 대해서 매칭시킬거 있는지 조사
         attrList = attributeDao.getAll(clusterList.get(i).getCluster_id()); // 클러스터아이디로 그 안에 속한 태그들을 받아옴
         for (int j = 0; j < twcList.size(); j++) { // 사용자 해시태그 전체에 대해 조사
            for (Attribute attr : attrList) {
               if (attr.getTag().equals(twcList.get(j).getTag())) {
                  if (twcList.get(j).getCount() > mean) {
                     if (!recommandedClusterList.contains(clusterList.get(i))) {
                        recommandedClusterList.add(clusterList.get(i));
                     }
                  }
               }
            }
         }
      }
      return recommandedClusterList;
   }

   public double getMeans(List<TagWithCount> twcs) {
      int sum = 0;
      for (TagWithCount twc : twcs) {
         sum += twc.getCount();
      }
      return sum / (twcs.size());
   }

   @Override
   public List<Attribute> getAttributes(int cluster_id) {
      List<Attribute> attrs = attributeDao.getAll(cluster_id); 
      return attrs;
   }
}