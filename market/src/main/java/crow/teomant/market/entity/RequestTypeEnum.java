package crow.teomant.market.entity;

import lombok.Getter;

@Getter
public enum RequestTypeEnum {

    SELL("Sell"),
    BUY("Buy");

    private String type;

    private RequestTypeEnum(String type) {
        this.type = type;
    }
}
