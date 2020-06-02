package crow.teomant.market.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestDto {

    private Integer quantity;

    private Integer price;

    private String productName;

    private String type;

    private UserDetailsDto userDetails;
}
