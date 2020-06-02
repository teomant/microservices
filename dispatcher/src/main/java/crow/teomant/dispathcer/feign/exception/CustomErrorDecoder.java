package crow.teomant.dispathcer.feign.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()){
            case 400:
                return new Exception("Bad request");
            case 401:
                return new Exception("Unauthorized");
            case 404:
                return new Exception("Not found");
            default:
                return new Exception("Generic error");
        }
    }
}