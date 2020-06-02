package crow.teomant.dispathcer.controller.rest;

import crow.teomant.dispathcer.controller.rest.dto.RequestDto;
import crow.teomant.dispathcer.controller.rest.dto.RoleDto;
import crow.teomant.dispathcer.controller.rest.dto.UserDetailsDto;
import crow.teomant.dispathcer.feign.proxy.CurrencyExchangeServiceProxy;
import crow.teomant.dispathcer.feign.proxy.MarketServiceProxy;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@RestController
public class DispatcherController {

    @Autowired
    private CurrencyExchangeServiceProxy proxy;

    @Autowired
    private MarketServiceProxy marketServiceProxy;

    @PostMapping("/currency-converter")
    public ResponseEntity convertCurrencyPost(String from, String to,
                                                      BigDecimal quantity) {
        try {
            return ResponseEntity.ok(proxy.retrieveExchangeValue(from, to, quantity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/put-product")
    public ResponseEntity putProduct(@RequestParam String type, @RequestParam String productName,
                                     @RequestParam int price, @RequestParam int quantity) {

        RequestDto requestDto = new RequestDto();
        requestDto.setPrice(price);
        requestDto.setProductName(productName);
        requestDto.setQuantity(quantity);
        requestDto.setType(type);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetailsDto userDetailsDto = new UserDetailsDto();
            userDetailsDto.setUsername(authentication.getName());
            userDetailsDto.setRoles(
                    authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .map(authority -> StringUtils.replace(authority, "ROLE_", ""))
                            .map(RoleDto::new)
                            .collect(Collectors.toSet())
            );
            requestDto.setUserDetails(userDetailsDto);
        }

        try {
            marketServiceProxy.putProduct(requestDto);
            return ResponseEntity.ok().body("Success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
