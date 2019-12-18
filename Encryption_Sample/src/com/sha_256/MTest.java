package com.sha_256;

public class MTest {

	public static void main(String[] args) {

		SHA_Util sha = new SHA_Util();

		// 123456은 평문(암호화 하지 않은 문자열)이다. 
		// 평문을 sha-256의 객체에 encryptSHA256() 의 아규먼트로 전달해 암호문을 반환받는다.
		// sha암호화 방식은 단방향 암호화 방식으로 복호화 방법이 존재하지 않기 때문에 복호화 메서드는 없다.
		String encrypedPW01 = sha.encryptSHA256("123456");
		System.out.println(" > " + encrypedPW01);

		String encrypedPW02 = sha.encryptSHA256("123456");
		System.out.println(" > " + encrypedPW02);

	}
}
