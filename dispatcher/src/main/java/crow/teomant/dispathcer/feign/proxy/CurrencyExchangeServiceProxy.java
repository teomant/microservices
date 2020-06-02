package crow.teomant.dispathcer.feign.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name="forex-service")
@RibbonClient(name="forex-service")
public interface CurrencyExchangeServiceProxy {
    @GetMapping("/currency-exchange")
    public BigDecimal retrieveExchangeValue
            (@RequestParam String from, @RequestParam String to, @RequestParam BigDecimal quantity);
}
