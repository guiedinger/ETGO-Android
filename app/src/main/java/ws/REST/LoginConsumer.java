package ws.REST;

import android.util.Log;

import POJOS.Login;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Guilherme on 20/10/2016.
 */
public class LoginConsumer {

    RestTemplate restTemplate;
    public static final String URL_BASE = "http://192.168.0.3:8080/EtgoServidor/rest/login";

    public LoginConsumer() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public Login chamaCadastrar(Login login){

        ResponseEntity<Login> response = restTemplate.postForEntity(URL_BASE, login, Login.class);
        login = response.getBody();
        return login;
    }


}
