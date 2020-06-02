package crow.teomant.forex.web.controller;

import crow.teomant.forex.exchange.entity.ExchangeValue;
import crow.teomant.forex.exchange.repository.ExchangeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class ForexController {

    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeValueRepository repository;

    @GetMapping("/currency-exchange")
    public BigDecimal retrieveExchangeValue
            (@RequestParam String from, @RequestParam String to, @RequestParam BigDecimal quantity){

        ExchangeValue exchangeValue =
                repository.findByFromAndTo(from, to);

        return exchangeValue.getConversionMultiple().multiply(quantity);
    }
}
