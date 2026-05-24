
public class Encrypt {

	public static void main(String[] args) throws Exception {
	    String key = "MySecretKey12345";  // same key used in decrypt
	    System.out.println("Encrypted client_id: " + encrypt("your_client_id_here", key));
	    System.out.println("Encrypted client_secret: " + encrypt("your_client_secret_here", key));
	

	}

	private static String encrypt(String string, String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
