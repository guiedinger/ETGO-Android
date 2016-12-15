package ws.REST;

import POJOS.Empresa;
import POJOS.Passageiro;
import POJOS.Transportadora;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static ws.REST.ServiceGenerator.API_BASE_URL;

/**
 * Created by Guilherme on 01/12/2016.
 */

public class ConnectionManager {

    public static Retrofit retrofit;

    public static Call<Passageiro> postForCreatePassageiro(Passageiro passageiro){
        PassageiroConsumer passageiroConsumer = getRetrofitInstance().create(PassageiroConsumer.class);
        return passageiroConsumer.chamaCadastrarPassageiro(passageiro);
    }
    public static Call<Passageiro> posForLoginPassageiro(String userName, String password){
        PassageiroConsumer passageiroConsumer = getRetrofitInstance().create(PassageiroConsumer.class);
        return passageiroConsumer.loginPassageiro(userName, password);
    }

    public static Call<Transportadora> postForCreateTransportadora(Transportadora transportadora){
        TransportadoraConsumer transportadoraConsumer = getRetrofitInstance().create(TransportadoraConsumer.class);
        return transportadoraConsumer.chamaCadastrarTransportadora(transportadora);
    }

    public static Call<Empresa> postForCreateEmpresa(Empresa empresa){
        EmpresaConsumer empresaConsumer = getRetrofitInstance().create(EmpresaConsumer.class);
        return empresaConsumer.chamaCadastrarEmpresa(empresa);
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
