package crow.teomant.market.repository;

import crow.teomant.market.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestEntityRepository extends JpaRepository<RequestEntity, Long> {

    public List<RequestEntity> findByProductName(String product);
}
