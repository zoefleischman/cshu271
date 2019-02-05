import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestUserAccount {
	
	public static void main(String[] args) throws IOException {
		testIsPasswordValid();
	}

	public static void testIsPasswordValid() throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("passwords.csv"));
		int errorCount = 0;	
		String str;
		while ((str = in.readLine()) != null) { 
			if (str.charAt(0) == '/') {}
			else {
				String[] passwordExpect = str.split(",");
				Boolean actual = UserAccount.isPasswordValid(passwordExpect[0]);
				Boolean expect = Boolean.parseBoolean(passwordExpect[1]);
				if (actual == expect) {
					System.out.println(passwordExpect[0] + " is a(n) " 
							+ (expect ? "valid" : "invalid") 
							+ " password: OK");
				} else {
					System.out.println(passwordExpect[0] + " is a(n) " 
						+ (expect ? "invalid" : "valid") 
						+ " password: NG");
					errorCount++;
				}
			}
		}
		in.close();
		System.out.println(errorCount + " tests were NG");
	}
}

