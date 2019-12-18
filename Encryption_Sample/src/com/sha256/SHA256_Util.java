package com.sha256;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256_Util {
	/*
	 * sha-256 ��ȣȭ ���
	 * 
	 * ����
	 *  SHA-256 �ؽ� �Լ��� � ������ ���� �Է��ϴ��� 256��Ʈ�� ������ ������� ����Ѵ�.[2] �Ϲ������� �Է°��� ���ݸ�
	 * �����Ͽ��� ��°��� ������ �޶����� ������ ��°��� ���� �Է°��� �����ϴ� ���� ���� �Ұ����ϴ�. ���� ���� Ȯ���� �Է°��� �ٸ�����
	 * �ұ��ϰ� ��°��� ���� ��찡 �߻��ϴµ� �̰��� �浹�̶�� �Ѵ�. �̷��� �浹�� �߻� Ȯ���� �������� ���� �Լ���� �򰡵ȴ�. SHA-1��
	 * 
	 * Ư¡
	 *  SHA-256�� ���� ���ü�ο��� ���� ���� ä���Ͽ� ���ǰ� �ִ� ��ȣ ����̴�. ��� �ӵ��� �����ٴ� ������ ���� �ִ�. ����
	 * �ܹ��⼺�� ������ ��� �ִ� ��ȣȭ ������� ��ȣȭ�� �Ұ����ϴ�. 	 * 
	 * SHA �Լ��� �� ���� ���� ���̸�, TLS, SSL, PGP, SSH,
	 * IPSec �� ���� ���� �������ݰ� ���α׷����� ���ǰ� �ִ�. SHA-1�� ������ �θ� ���Ǵ� MD5�� ����ؼ� ���̱⵵ �Ѵ�. Ȥ�ڴ�
	 * �� �� �߿��� ������� SHA-256�̳� �� �̻��� �˰����� ����� ���� �����Ѵ�.
	 * 
	 * 
	 * ����ȹ...
	 * ȸ�� ���� �� - ����ڰ� ���� pw�� sha-256 �� ���� ��ȣȭ �Ͽ� DB�� �����Ѵ�.
	 * �α��� �� - ����ڷκ��� �Էµ� pw ���� ��ȣȭ �Ͽ� DB�� �����ϴ� ��ȣ���� ���Ѵ�.
	 * 		   DB�� �����ϴ� ��ȣ���� ����ڰ� �Է��� ��й�ȣ�� ��ȣ���� ��ġ�ϸ� �α��� ó��
	 * 
	 * 
	 */
	public String encryptSHA256(String str) {
		String sha = "";

		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");

			sh.update(str.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			sha = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			// e.printStackTrace();
			System.out.println("Encrypt Error : No Such Algorithm Exception");
			sha = null;
		}

		return sha;
	}
}
