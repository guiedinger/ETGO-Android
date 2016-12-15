package ws.REST;

import POJOS.Empresa;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Guilherme on 14/12/2016.
 */

public interface EmpresaConsumer {
    @POST("empresa")
    Call<Empresa> chamaCadastrarEmpresa(@Body Empresa empresa);

    @FormUrlEncoded
    @POST("empresa")
    Call<Empresa> loginEmpresa(@Field("userName") String userName, @Field("password") String password);
}
