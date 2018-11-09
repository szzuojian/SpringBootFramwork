package com.sword.framework.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DecriptUtils {

	private static final String TOKEN = "abcdefgh654321";

	public static String getWeixinToken(String timestamp, String nonce) {
		// 字典序排序
		String[] tmpArr = { TOKEN, timestamp, nonce };
		Arrays.sort(tmpArr);

		// 转换成字符串
		String tmpStr = "";
		for (int i = 0; i < tmpArr.length; i++) {
			tmpStr += tmpArr[i];
		}
		tmpStr = DecriptUtils.SHA1(tmpStr);
		System.out.println(tmpStr);
		return tmpStr;
	}

	public static String getWeixinJsApisignature(String timestamp, String nonce, String jsapi_ticket, String url) {
		// 字典序排序

		String tmpStr = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce + "&timestamp=" + timestamp + "&url="
				+ url;
		tmpStr = DecriptUtils.SHA1(tmpStr);
		System.out.println(tmpStr);
		return tmpStr;
	}

	public static String SHA1(String decript) {
		return SHA(decript, "SHA-1");
	}

	public static String SHA(String decript, String type) {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance(type);
			digest.update(decript.getBytes("ISO_8859_1"));
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String SHA256(String decript) {
		return SHA(decript, "SHA-256");
	}

	public static String SHA512(String decript) {
		return SHA(decript, "SHA-512");
	}

	public static String MD5(String input) {
		try {
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(input.getBytes());
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < md.length; i++) {
				String shaHex = Integer.toHexString(md[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 加密
	 *
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static byte[] encryptAES(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 *
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	public static byte[] decryptAES(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * BASE64解密
	 *
	 * @param string
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static String decryptBASE64(String string) throws IOException {
		BASE64Decoder decoder = new BASE64Decoder();
		return new String(decoder.decodeBuffer(string));
	}

	/**
	 * BASE64加密
	 *
	 * @param key
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static String encryptBASE64(String string) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(string.getBytes());

	}

	/**
	 * BASE64加密
	 *
	 * @param key
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws Exception
	 */
	public static String encryptBASE64GBK(String string) throws UnsupportedEncodingException {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(string.getBytes("GBK"));

	}

	/**
	 * BASE64解密
	 *
	 * @param string
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static String decryptBASE64GBK(String string) throws IOException {
		BASE64Decoder decoder = new BASE64Decoder();
		return new String(decoder.decodeBuffer(string), "GBK");
	}
}
