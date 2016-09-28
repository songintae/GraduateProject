package graduate.ad;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import graduate.dao.ContentDao;

public class AdRemoverV2 implements AdRemover {

	ContentDao cDao;

	public void setAdRemoverV2(ContentDao cDao) {
		this.cDao = cDao;
	}

	public boolean removeAd(String AdDictionary) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new FileReader(AdDictionary));
		String ad_var;

		cDao.contentDeleteByTag(ad_var);
		in.close();
		return false;
	}
	/*
	 * 인태야 contentDeleteByTag 만들어 줘. 이게 뭐냐면
	 * 들어오는 태그가 포함된 컨텐츠를 DB에서 삭제하는거야. like함수 써서 부탁함~
	 * 지혜가 힘들대ㅋㅋ
	 */
}
