package com.rsa2048;

import java.security.Key;
import java.util.Map;

public class MTest {
	public static void main(String[] args) {
		
		RSA2048_Util rsa = new RSA2048_Util();
		
		Map<String, Key> keyMap = rsa.generateKey();
		
		Key publicKey = keyMap.get("publicKey");
		Key privateKey = keyMap.get("privateKey");
		
		String plainStr = "123456";
		System.out.println("�� : " + plainStr);
		
		System.out.println("\n===========================");		
		byte[] cryptogramByte = rsa.Encryption(plainStr, publicKey);
		String cryptogram = new String(cryptogramByte);		
		System.out.println("��ȣ��(byte) : " + cryptogramByte);
		System.out.println("��ȣ��(String) : " + cryptogram);
		
		System.out.println("\n===========================");
		byte[] plainByte = rsa.Decryption(cryptogramByte, privateKey);
		String plain = new String(plainByte);
		System.out.println("��ȣ��(byte) : " + plainByte);
		System.out.println("��ȣ��(String) : " + plain);
		
	}
}