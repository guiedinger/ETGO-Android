package ws.REST;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * Created by Guilherme on 28/10/2016.
 */

public class NewResponseErrorHandler implements ResponseErrorHandler {

    protected boolean hasError(HttpStatus statusCode) {
        return (statusCode.series() == HttpStatus.Series.CLIENT_ERROR ||
                statusCode.series() == HttpStatus.Series.SERVER_ERROR);
    }

    public boolean hasError(ClientHttpResponse response) throws IOException {
        return hasError(response.getStatusCode());
    }


    public void handleError(ClientHttpResponse response) throws IOException{
        
    }
}
