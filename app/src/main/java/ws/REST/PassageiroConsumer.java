package ws.REST;

import POJOS.Passageiro;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * Created by Guilherme on 01/12/2016.
 */

public interface PassageiroConsumer {

    @POST("passageiro")
    Call<Passageiro> chamaCadastrarPassageiro(@Body Passageiro passageiro);

    @FormUrlEncoded
    @POST("passageiro")
    Call<Passageiro> loginPassageiro(@Field("userName") String userName, @Field("password") String password);
}
