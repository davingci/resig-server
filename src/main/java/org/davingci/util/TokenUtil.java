/**
 *@author huangdongxu
 *@Date Nov 17, 2017
*/

package org.davingci.util;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenUtil {

	public static String getJWTString(String username, Integer userId,  Date expires, Key key) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
        if (username == null) {
            throw new NullPointerException("null username is illegal");
        }
        if (userId == null) {
            throw new NullPointerException("null roles are illegal");
        }
        if (expires == null) {
            throw new NullPointerException("null expires is illegal");
        }
        if (key == null) {
            throw new NullPointerException("null key is illegal");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        String jwtString = Jwts
                .builder()
                .setIssuer("org.davingci")
                .setSubject(username)
                .setExpiration(expires)
                .setIssuedAt(new Date())
                .setId(String.valueOf(userId))
                .signWith(signatureAlgorithm, key)
                .compact();
        return jwtString;
    }

    public static boolean isValid(String token, Key key) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token.trim());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getUserName(String jwsToken, Key key) {
        if (isValid(jwsToken, key)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
            return claimsJws.getBody().getSubject();
        }
        return null;
    }
    
    public static int getUserId(String jwsToken, Key key) {
        if (isValid(jwsToken, key)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
            return Integer.parseInt(claimsJws.getBody().getId());
        }
        return -1;
    }

}
