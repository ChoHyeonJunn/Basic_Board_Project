package com.utility.rsa2048;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSA2048_Util {
	/*
	 * RSA-2048 암호화
	 * 
	 * 현재 SSL/TLS에 가장 많이 사용되는 공개키 암호화 알고리즘이다. 현재 나무위키도 HTTPS에 RSA-2048 인증서를 사용하고
	 * 있으며, 전세계 대부분의 인터넷 뱅킹(대한민국 포함)이 이 RSA-2048 암호화를 사용한다.
	 * 
	 * 암호화할 때에는 마음대로였겠지만 해독할 때에는 아니란다를 기본 모토로 한 공개키 암호체계 중 하나이다. 공개키와 개인키가 한 쌍을 이루며,
	 * 공개키로 암호화한 내용은 개인키로만, 개인키로 암호화한 내용은 공개키로만 해독할 수 있다.[2] 엄청 큰 숫자는 소인수분해하기가 힘들다는
	 * 것을 이용한다.
	 * 
	 * 사용계획...
	 * DB에 저장은 바람직 하지 않음 - 
	 * 	RSA암호화 기법은 같은 평문을 넣어도 출력되는 암호문의 값이 모두 제각각이다. 따라서 이를 복호화 할 필요가 있다.
	 * 그런데 복호화를 위해서는 개인키가 필요하다. 즉 DB에 암호문과 함께 개인키 또한 저장할 필요성이 있다.
	 * 하지만 이러한 방법은 보안상에 치명적인 오류가 될 수 있다. DB테이블의 데이터를 해킹당한다면 DB에 저장된 개인키를 통해
	 * 암호문을 해독할 수 있기 때문이다. 따라서 DB에 저장하는 용도가 아닌 따른 용도로 사용할 필요가 있다.
	 * 
	 * clinet -> server 로 갈 때 사용 -
	 *  web 서비스에서 보안상 가장 취약한 부분은 사실 서버나 DB부분이 아닌 클라이언트단 이다.(project안에서는 jsp <-> controller 와 data를 주고 받을 때)
	 * 사용자의 pw는 보안상 꼭 암호화 해야할 필요성이 있으므로 jsp에서 controller로 pw를 넘겨줄 때 rsa암호문을 사용하도록 한다.
	 * 이 때 중요한 것은 복호화를 위한 개인키를 서버와 클라이언트 사이에서 주고받지 않는 것이다.
	 * 이를 위해 회원가임,로그인 시 먼저 서버단에서 rsa를 위한 키쌍(개인키,공개키를) 먼저 생성한다. 후에 페이지에 암호화를 위한 공개키를 전달한다.
	 * 그러면 복호화를 위한 개인키는 client와 server 사이에서 이동하지 않았기 때문에 안전하게 보관할 수 있다.
	 * 뷰에서는 전달받은 공개키를 이용해 사용자로부터 입력받은 pw를 암호화해 서버로 전달한다.
	 * 서버에서는 미리 가지고있던 개인키를 이용해 암호화된pw를 복호화 한다.
	 * 
	 * 이후에는 DB에 저장하기 위해 단방향 암호화 방식인 sha-256암호화 방식을 사용한다.
	 */

	private Key publicKey; // 공개키 - 암호화에 사용되는 키
	private Key privateKey; // 개인키 - 복호화에 사용되는 키

	// 키 생성 매서드
	public Map<String, Key> generateKey() {
		Map<String, Key> keyMap = new HashMap<String, Key>();

		KeyPairGenerator keyPairGenerator;
		KeyPair keyPair;
		try {
			keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);

			keyPair = keyPairGenerator.generateKeyPair();
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();

			keyMap.put("publicKey", publicKey);
			keyMap.put("privateKey", privateKey);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return keyMap;
	}

	// 암호화 매서드(평문String, 공개키) : return 암호문(byte)
	public byte[] Encryption(String plainStr, Key publicKey) {
		byte[] cryptogram = null; // 리턴될 암호문

		try {

			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);

			cryptogram = cipher.doFinal(plainStr.getBytes());

		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cryptogram;
	}

	// 복호화 메서드(암호문byte, 개인키) : return 암호문 byte
	public byte[] Decryption(byte[] cryptogram, Key privateKey) {
		byte[] plainByte = null; // 리턴될 복호화된 평문
		try {

			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);

			plainByte = cipher.doFinal(cryptogram);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return plainByte;
	}
}
