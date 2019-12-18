package com.rsa;

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

public class RSA_Util {
	/*
	 * RSA-2048 ��ȣȭ
	 * 
	 * ���� SSL/TLS�� ���� ���� ���Ǵ� ����Ű ��ȣȭ �˰����̴�. ���� ������Ű�� HTTPS�� RSA-2048 �������� ����ϰ�
	 * ������, ������ ��κ��� ���ͳ� ��ŷ(���ѹα� ����)�� �� RSA-2048 ��ȣȭ�� ����Ѵ�.
	 * 
	 * ��ȣȭ�� ������ ������ο������� �ص��� ������ �ƴ϶��ٸ� �⺻ ����� �� ����Ű ��ȣü�� �� �ϳ��̴�. ����Ű�� ����Ű�� �� ���� �̷��,
	 * ����Ű�� ��ȣȭ�� ������ ����Ű�θ�, ����Ű�� ��ȣȭ�� ������ ����Ű�θ� �ص��� �� �ִ�.[2] ��û ū ���ڴ� ���μ������ϱⰡ ����ٴ�
	 * ���� �̿��Ѵ�.
	 * 
	 * ����ȹ...
	 * DB�� ������ �ٶ��� ���� ���� - 
	 * 	RSA��ȣȭ ����� ���� ���� �־ ��µǴ� ��ȣ���� ���� ��� �������̴�. ���� �̸� ��ȣȭ �� �ʿ䰡 �ִ�.
	 * �׷��� ��ȣȭ�� ���ؼ��� ����Ű�� �ʿ��ϴ�. �� DB�� ��ȣ���� �Բ� ����Ű ���� ������ �ʿ伺�� �ִ�.
	 * ������ �̷��� ����� ���Ȼ� ġ������ ������ �� �� �ִ�. DB���̺��� �����͸� ��ŷ���Ѵٸ� DB�� ����� ����Ű�� ����
	 * ��ȣ���� �ص��� �� �ֱ� �����̴�. ���� DB�� �����ϴ� �뵵�� �ƴ� ���� �뵵�� ����� �ʿ䰡 �ִ�.
	 * 
	 * clinet -> server �� �� �� ��� -
	 *  web ���񽺿��� ���Ȼ� ���� ����� �κ��� ��� ������ DB�κ��� �ƴ� Ŭ���̾�Ʈ�� �̴�.(project�ȿ����� jsp <-> controller �� data�� �ְ� ���� ��)
	 * ������� pw�� ���Ȼ� �� ��ȣȭ �ؾ��� �ʿ伺�� �����Ƿ� jsp���� controller�� pw�� �Ѱ��� �� rsa��ȣ���� ����ϵ��� �Ѵ�.
	 * �� �� �߿��� ���� Ű�� ������ Ŭ���̾�Ʈ���� �ְ���� �ʴ� ���̴�.
	 */

	private Key publicKey; // ����Ű - ��ȣȭ�� ���Ǵ� Ű
	private Key privateKey; // ����Ű - ��ȣȭ�� ���Ǵ� Ű

	// Ű ���� �ż���
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

	// ��ȣȭ �ż���(��String, ����Ű) : return ��ȣ��(byte)
	public byte[] Encryption(String plainStr, Key publicKey) {
		byte[] cryptogram = null; // ���ϵ� ��ȣ��

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

	// ��ȣȭ �޼���(��ȣ��byte, ����Ű) : return ��ȣ�� byte
	public byte[] Decryption(byte[] cryptogram, Key privateKey) {
		byte[] plainByte = null; // ���ϵ� ��ȣȭ�� ��
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
