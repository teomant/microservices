package crow.teomant.dispathcer.feign.proxy;

import crow.teomant.dispathcer.controller.rest.dto.RequestDto;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="market-service")
@RibbonClient(name="market-service")
public interface MarketServiceProxy {
    @PostMapping("/put-product")
    public void putProduct(@RequestBody RequestDto requestDto);
}
