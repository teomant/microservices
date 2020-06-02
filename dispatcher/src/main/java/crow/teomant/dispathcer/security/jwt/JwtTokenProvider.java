package crow.teomant.dispathcer.security.jwt;

import crow.teomant.dispathcer.security.CustomUserDetails;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;

@Component
public class JwtTokenProvider {

    private static final String AUTH = "auth";
    private static final String AUTHORIZATION = "Authorization";
    private String secretKey = "secret-key";

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String resolveToken(HttpServletRequest req) {

        return req.getHeader(AUTHORIZATION);
    }

    public UserDetails getUserDetails(String token) {
        String userName = getUsername(token);
        List<String> roleList = getRoleList(token);
        return new CustomUserDetails(userName, roleList.toArray(new String[roleList.size()]));
    }

    public List<String> getRoleList(String token) {
        return (List<String>) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).
                getBody().get(AUTH);
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = getUserDetails(token);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
