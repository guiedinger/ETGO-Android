package ws.REST;

import POJOS.Linha;
import POJOS.Motorista;
import POJOS.Onibus;
import POJOS.Transportadora;
import POJOS.Viagem;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Guilherme on 14/12/2016.
 */

public interface TransportadoraConsumer {
    @POST("transportadora")
    Call<Transportadora> chamaCadastrarTransportadora(@Body Transportadora transportadora);

    @POST("transportadora/adicionarOnibus/{id}")
    Call<Transportadora> adicionarOnibus(@Path("id") Integer id, @Body Onibus onibus);

    @POST("transportadora/adicionarMotorista/{id}")
    Call<Transportadora> adicionarMotorista(@Path("id") Integer id, @Body Motorista motorista);

    @POST("transportadora/adicionarLinha/{id}")
    Call<Transportadora> adicionarLinha(@Path("id") Integer id, @Body Linha linha);

    @POST("transportadora/viagem")
    Call<Transportadora> iniciarViagem(@Body Viagem viagem);

    @FormUrlEncoded
    @PUT("transportadora/efetuarPagamento")
    Call<Transportadora> efetuarPagamento(@Field("id") Integer id, @Field("token") String token, @Field("valor") Double valor);

    @FormUrlEncoded
    @POST("transportadora")
    Call<Transportadora> loginTransportadora(@Field("userName") String userName, @Field("password") String password);
}
