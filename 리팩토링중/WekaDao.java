public class WekaTagDao {
	Connection c;
	Statement stmt;
	ResultSet rs;
	String sql;
	PreparedStatement pstmt;

	public void setUp() {
		this.c = DriverManager.getConnection("jdbc:mysql://localhost/instadata?autoReconnect=true&useSSL=false", "root",
				"song9207108");
		stmt = conn.createStatement();
	}

	public Instances getInstances() {
		sql = "select * from (select tag , count(*) as count from busan where content_id in(select content_id from busan where tag = '내일로') group by tag) as test1 where count >2;";
		rs = stmt.executeQuery(sql);
		rs.last();
		Tag tags[] = new Tag[rs.getRow()]; // getRow() : Retrieves the current
											// row number.
		rs.first();

		int i = 0;
		do {
			tags[i] = new Tag(rs.getString(1), rs.getInt(2));
			i++;
		} while (rs.next());

		// DB에서 바로 가져오기
		Attribute[] attributes = new Attribute[tags.length];

		for (i = 0; i < attributes.length; i++) {
			String attributeString = (i + 1) + "." + tags[i].getTagName(); // 태그이름들을
																			// 1.태그
																			// 이런식으로
																			// 다시
																			// 저장
			attributes[i] = new Attribute(attributeString);
		}

		// 웨카 라이브러리에서 제공하는 클래스.
		FastVector finalAttribute = new FastVector(attributes.length); // 숫자를 주면
																		// 이만큼
																		// 용량이
																		// 생김.
		for (i = 0; i < attributes.length; i++) {
			finalAttribute.addElement(attributes[i]); // 태그들을 다 포함시킴.
		}

		Instances testSet = new Instances("TestTen", finalAttribute, tags.length);

		for (i = 0; i < attributes.length; i++) {
			System.out.println(i);
			Instance instance = new Instance(attributes.length);

			sql = "select jeon.tag , CASE WHEN test.count IS NULL THEN 0 ELSE test.count END as count from (select * from (select tag , count(*) as count from busan where content_id in(select content_id from busan where tag = '내일로') group by tag) as test1 where count >2) as jeon  LEFT outer join (select * from (select tag , count(*) as count from busan where content_id in(select content_id from busan where tag = '"
					+ tags[i].getTagName()
					+ "') group by tag) as test1 where count >2) as test ON jeon.tag = test.tag order by tag ;";
			rs = stmt.executeQuery(sql);
			for (int j = 0; j < attributes.length; j++) {
				rs.next();
				double num = ((double) rs.getInt(2) / (double) tags[i].getTagNum()) * 100;

				instance.setValue((Attribute) attributes[j], (int) num);

			}
			testSet.add(instance);
		}

		return testSet;
	}
}
