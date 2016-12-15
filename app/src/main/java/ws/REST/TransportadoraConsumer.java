package ws.REST;

import POJOS.Transportadora;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Guilherme on 14/12/2016.
 */

public interface TransportadoraConsumer {
    @POST("transportadora")
    Call<Transportadora> chamaCadastrarTransportadora(@Body Transportadora transportadora);
}
