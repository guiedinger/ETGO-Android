package ws.REST;

import org.springframework.http.MediaType;

import POJOS.Login;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * Created by Guilherme on 01/12/2016.
 */

public interface LoginService {

    @POST("login")
    Call<Login> chamaCadastrar(@Body Login login);

    @FormUrlEncoded
    @POST("login")
    Call<Login> loginUsuario(@Field("userName") String userName, @Field("password") String password);
}
