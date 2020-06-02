package crow.teomant.gateway.auth.jwt.repository;

import crow.teomant.gateway.auth.jwt.entity.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken, String> {
}