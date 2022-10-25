package com.boost.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.boost.exception.AuthServiceException;
import com.boost.exception.ErrorType;
import org.bouncycastle.math.ec.rfc8032.Ed25519;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {

    @Value("${myjwt.secretkey}")
    private String secretKey;
    @Value("${myjwt.audience}")
    private String audience;
    @Value("${myjwt.issuer}")
    private String issuer;

    public String createToken(Long authid){
        String token=null;
        try{
            /**
             * Token yayımcısı kim ?
             * Token Uretim tarihi?
             * Token geçerlilik zamanı
             * Token icerisinde tekrar kullanabilmek uzere claim nesneleri koyabilirsiniz
             * bu nesneler key value seklinde bilgileri tutar ve public olarak görüntülenebilir.
             */
            Algorithm algorithm=Algorithm.HMAC256(secretKey);
            token = JWT.create()
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .withIssuedAt(new java.util.Date())
                    .withExpiresAt(new Date(System.currentTimeMillis()+(1000*60*5)))
                    .withClaim("authid",authid)
                    .sign(algorithm); // en son imzalama islemi
            return token;
        }catch (Exception e){
            return null;
        }
    }

    public Optional<Long> getByIdFromToken(String token){
        try{
            Algorithm algorithm=Algorithm.HMAC256(secretKey);
            JWTVerifier jwtVerifier= JWT.require(algorithm)
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .build();
            DecodedJWT decodedToken = jwtVerifier.verify(token);
            if(decodedToken==null) throw new AuthServiceException(ErrorType.LOGIN_ERROR_001);
            Long authid = decodedToken.getClaim("authid").asLong();
            return Optional.of(authid);

        }catch (Exception e){
            return null;
        }
    }
}
