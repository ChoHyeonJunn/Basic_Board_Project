package com.sha256;

public class MTest {

	public static void main(String[] args) {

		SHA256_Util sha = new SHA256_Util();

		// 123456�� ��(��ȣȭ ���� ���� ���ڿ�)�̴�. 
		// ���� sha-256�� ��ü�� encryptSHA256() �� �ƱԸ�Ʈ�� ������ ��ȣ���� ��ȯ�޴´�.
		// sha��ȣȭ ����� �ܹ��� ��ȣȭ ������� ��ȣȭ ����� �������� �ʱ� ������ ��ȣȭ �޼���� ����.
		String encrypedPW01 = sha.encryptSHA256("123456");
		System.out.println(" > " + encrypedPW01);

		String encrypedPW02 = sha.encryptSHA256("123456");
		System.out.println(" > " + encrypedPW02);

	}
}
