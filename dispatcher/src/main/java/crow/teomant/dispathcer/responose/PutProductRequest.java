package crow.teomant.dispathcer.responose;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PutProductRequest {

    private long count;

    private long price;

    private String product;
}
