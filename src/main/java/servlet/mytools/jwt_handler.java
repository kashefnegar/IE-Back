package servlet.mytools;


import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import io.jsonwebtoken.*;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

public class jwt_handler {

    private static String SECRET_KEY = "joboonja";

    //Sample method to construct a JWT
    public  String createJWT(String id, String issuer, String subject, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//
//        long nowMillis = System.currentTimeMillis();
//        Date now = new Date(nowMillis);
//
//        //We will sign our JWT with our ApiKey secret
//        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
//        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
//
//        //Let's set the JWT Claims
//        JwtBuilder builder = Jwts.builder().setId(id)
//                .setIssuedAt(now)
//                .setSubject(subject)
//                .setIssuer(issuer)
//                .signWith(signatureAlgorithm, signingKey);
//
//        //if it has been specified, let's add the expiration
//        if (ttlMillis >= 0) {
//            long expMillis = nowMillis + ttlMillis;
//            Date exp = new Date(expMillis);
//            builder.setExpiration(exp);
//        }
//
//        //Builds the JWT and serializes it to a compact, URL-safe string
//        return builder.compact();
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            String token = JWT.create().
                    withExpiresAt(new Date(ttlMillis+System.currentTimeMillis()))
                    .withIssuedAt( new Date(System.currentTimeMillis()))
                    .withSubject(subject)
                    .withJWTId(id)
                    .withIssuer(issuer)
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            return "";
        }

    }

    public static Claims decodeJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }


}

