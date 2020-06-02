package crow.teomant.market.entity;

import crow.teomant.market.converter.RequestTypeEnumConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Integer price;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "type")
    @Convert(converter = RequestTypeEnumConverter.class)
    private RequestTypeEnum type;
}
