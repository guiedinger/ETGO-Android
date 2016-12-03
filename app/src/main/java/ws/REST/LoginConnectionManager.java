package ws.REST;

import POJOS.Login;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static ws.REST.ServiceGenerator.API_BASE_URL;

/**
 * Created by Guilherme on 01/12/2016.
 */

public class LoginConnectionManager {

    public static Retrofit retrofit;

    public static Call<Login> postForCreate(Login login){
        LoginService loginService = getRetrofitInstance().create(LoginService.class);
        return loginService.chamaCadastrar(login);
    }
    public static Call<Login> posForLogin(String userName, String password){
        LoginService loginService = getRetrofitInstance().create(LoginService.class);
        return loginService.loginUsuario(userName, password);
    }
    private static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit;
        } else {
            return retrofit;
        }
    }
}
