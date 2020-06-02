package crow.teomant.forex.exchange.repository;

import crow.teomant.forex.exchange.entity.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
        ExchangeValue findByFromAndTo(String from, String to);
}
