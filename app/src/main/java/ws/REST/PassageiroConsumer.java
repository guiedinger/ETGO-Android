package ws.REST;

import POJOS.Passageiro;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by Guilherme on 01/12/2016.
 */

public interface PassageiroConsumer {

    @POST("passageiro")
    Call<Passageiro> chamaCadastrarPassageiro(@Body Passageiro passageiro);

    @FormUrlEncoded
    @POST("passageiro")
    Call<Passageiro> loginPassageiro(@Field("userName") String userName, @Field("password") String password);

    @FormUrlEncoded
    @PUT("passageiro/atualizarSaldo")
    Call<Passageiro> atualizarSaldo(@Field("id") Integer id, @Field("credito") Double credito);

    @PUT("passageiro/atualizarToken/{id}")
    Call<Passageiro> atualizarToken(@Path("id") Integer id);

    @GET("passageiro/{id}")
    Call<Passageiro> consultarPassageiro(@Path("id") Integer id);
}
