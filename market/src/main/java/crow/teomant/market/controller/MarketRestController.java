package crow.teomant.market.controller;

import crow.teomant.market.controller.dto.RequestDto;
import crow.teomant.market.controller.dto.UserDetailsDto;
import crow.teomant.market.entity.RequestEntity;
import crow.teomant.market.entity.RequestTypeEnum;
import crow.teomant.market.repository.RequestEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class MarketRestController {

    @Autowired
    private RequestEntityRepository requestEntityRepository;

    @GetMapping("/product/{name}")
    public List<RequestEntity> getProductsByName(@PathVariable String name) {
        return requestEntityRepository.findByProductName(name);
    }

    @PostMapping("/put-product")
    public ResponseEntity retrieveExchangeValue(@RequestBody RequestDto requestDto) {
        Optional<RequestTypeEnum> typeOptional = Arrays.stream(RequestTypeEnum.values())
                .filter(value -> value.getType().equalsIgnoreCase(requestDto.getType()))
                .findFirst();
        if (!typeOptional.isPresent()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        UserDetailsDto userDetails = requestDto.getUserDetails();
        if (!checkUserRole(typeOptional, userDetails)) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        RequestEntity s = new RequestEntity();
        s.setQuantity(requestDto.getQuantity());
        s.setPrice(requestDto.getPrice());
        s.setProductName(requestDto.getProductName());
        s.setType(typeOptional.get());

        requestEntityRepository.save(s);
        return new ResponseEntity(HttpStatus.OK);
    }

    private boolean checkUserRole(Optional<RequestTypeEnum> typeOptional, UserDetailsDto userDetails) {
        return userDetails != null && !userDetails.getRoles().isEmpty() && userDetails.getRoles().stream().anyMatch(roleDto ->
            roleDto.getName().equalsIgnoreCase(typeOptional.get().getType()) || roleDto.getName().equalsIgnoreCase("admin")
        );
    }
}
