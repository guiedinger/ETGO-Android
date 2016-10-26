package ws.REST;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import POJOS.Login;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Guilherme on 20/10/2016.
 */
public class LoginConsumer {

    RestTemplate restTemplate;
    public static final String URL_BASE = "http://192.168.0.7:8080/EtgoServidor/rest/login";

    public LoginConsumer() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    }

    public ResponseEntity<Login> logar(Login login){
//        String URL = URL_BASE+"/{username}/{password}";
//        Map map = new HashMap();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("userName",login.getUserName());
        map.add("password",login.getPassword());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<Login> response = restTemplate.postForEntity(URL_BASE, request, Login.class);

        return response;
    }

    public Login chamaCadastrar(Login login){
        ResponseEntity<Login> response = restTemplate.postForEntity(URL_BASE, login, Login.class);
        login = response.getBody();
        return login;
    }



}
