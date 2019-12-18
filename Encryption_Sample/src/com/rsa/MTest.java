package com.rsa;

import java.security.Key;
import java.util.Map;

public class MTest {
	public static void main(String[] args) {
		
		RSA_Util rsa = new RSA_Util();
		
		Map<String, Key> keyMap = rsa.generateKey();
		
		Key publicKey = keyMap.get("publicKey");
		Key privateKey = keyMap.get("privateKey");
		
		String plainStr = "123456";
		System.out.println("평문 : " + plainStr);
		
		System.out.println("\n===========================");		
		byte[] cryptogramByte = rsa.Encryption(plainStr, publicKey);
		String cryptogram = new String(cryptogramByte);		
		System.out.println("암호문(byte) : " + cryptogramByte);
		System.out.println("암호문(String) : " + cryptogram);
		
		System.out.println("\n===========================");
		byte[] plainByte = rsa.Decryption(cryptogramByte, privateKey);
		String plain = new String(plainByte);
		System.out.println("복호문(byte) : " + plainByte);
		System.out.println("복호문(String) : " + plain);
		
	}
}
