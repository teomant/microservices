package crow.teomant.market.converter;

import crow.teomant.market.entity.RequestTypeEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter
public class RequestTypeEnumConverter implements AttributeConverter<RequestTypeEnum, String> {
    @Override
    public String convertToDatabaseColumn(RequestTypeEnum requestTypeEnum) {
        return requestTypeEnum.getType();
    }

    @Override
    public RequestTypeEnum convertToEntityAttribute(String string) {
        return Arrays.stream(RequestTypeEnum.values())
                .filter(value -> value.getType().equalsIgnoreCase(string))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
