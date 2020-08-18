package com.tt.utils;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtils {
	public static final String SUBJECT = "shizhenjie"; // 编写发布者
	public static final int EXPIRE = 1000 * 10; // 设置过期时间,单位为毫秒,过期时间为半个钟
	public static final String APPSECRET = "axin"; // 设置密钥

	/**
	 * 使用jwt生成token
	 */
	public static String getToken(User user) {
		// 先判断是否为空,为空返回null
		if (user == null || user.getName() == null || user.getPwd() == null) {
			return null;
		}
		String token = Jwts.builder().setSubject(SUBJECT).claim("name", user.getName()).claim("pwd", user.getPwd())
				.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
				.signWith(SignatureAlgorithm.HS256, APPSECRET).compact();
		return token;
	}

	/**
	 * 校验密钥
	 */
	public static Claims checkToken(String token) {

		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(APPSECRET).parseClaimsJws(token).getBody();
		} catch (JwtException e) {
			return null;

		}
		return claims;

	}

	public static void main(String[] args) {

	}
}
