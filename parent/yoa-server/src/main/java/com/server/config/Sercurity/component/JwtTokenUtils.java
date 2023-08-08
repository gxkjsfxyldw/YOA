package com.server.config.Sercurity.component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtils {

    //用户名的key
    private static final String CLAIM_KEY_USERNAME = "sub";
    //token创建时间
    private static final String CLAIM_KEY_CREATED = "created";
    //密钥和失效时间，通过配置文件获取
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 根据用户名生成token
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        //用户名
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        //当前时间
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * * 根据荷载生成JWT TOKEN
     * @param claims
     * @return
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)//荷载
                .setExpiration(generateExpirationDate())//失效时间
                .signWith(SignatureAlgorithm.HS512, secret)//签名
                .compact();//密钥
    }
    /**
     * 生成token失效时间
     *
     * @return
     */
    private Date generateExpirationDate() {
        //当前时间+配置的失效时间
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从token里面拿用户名
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            //从token里面先获取到荷载，因为用户名是存在荷载里面的
            Claims claims = getClaimsFormToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 从token中获取荷载
     *
     * @param token
     * @return
     */
    private Claims getClaimsFormToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)//密钥
                    .parseClaimsJws(token)//
                    .getBody();//
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 验证token是否有效：
     * 1.token是否过期
     * 2.token荷载的用户名是否与userDetails一致
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否失效
     *
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date expireDate = getExpiredDateFromToken(token);
        //判断expireDate是否处于当前时间的前面，是则过期
        return expireDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     *
     * @param token
     * @return
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return claims.getExpiration();
    }


    /**
     * 判断token是否可以被刷新
     *
     * @param token
     * @return
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     *
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFormToken(token);
        //置换新的当前时间，就相当于刷新了
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
}
