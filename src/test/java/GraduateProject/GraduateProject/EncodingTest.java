package GraduateProject.GraduateProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class EncodingTest {

	@Before
	public void setup() {
		
	}

	@Test
	public void encodinTest() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("hyper.txt"));
			String str;
			while (true) {
				str = in.readLine();
				if (str == null)
					break;

				if (str.equals("한글아이디") || str.equals("hanium123"))
					System.out.println(str);

			}
			in.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
