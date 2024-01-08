package com.marijana.library1223.utils;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtUtil {

    //secret key, min 45 characters
    private final static String SECRET_KEY = "trebanamtakomaloskoronistajedankremenijednaiskradatamaodezauvijekizsna";

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
