package ws.REST;

import POJOS.Empresa;
import POJOS.Linha;
import POJOS.Motorista;
import POJOS.Onibus;
import POJOS.Passageiro;
import POJOS.Transportadora;
import POJOS.Usuario;
import POJOS.Viagem;
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

    public static Call<Passageiro> getForPassageiro(Integer id){
        PassageiroConsumer passageiroConsumer = getRetrofitInstance().create(PassageiroConsumer.class);
        return passageiroConsumer.consultarPassageiro(id);
    }

    public static Call<Passageiro> putForSaldoUp(Integer id, Double credito){
        PassageiroConsumer passageiroConsumer = getRetrofitInstance().create(PassageiroConsumer.class);
        return passageiroConsumer.atualizarSaldo(id, credito);
    }

    public static Call<Passageiro> putForTokenUp(Integer id){
        PassageiroConsumer passageiroConsumer = getRetrofitInstance().create(PassageiroConsumer.class);
        return passageiroConsumer.atualizarToken(id);
    }

    public static Call<Transportadora> postForCreateTransportadora(Transportadora transportadora){
        TransportadoraConsumer transportadoraConsumer = getRetrofitInstance().create(TransportadoraConsumer.class);
        return transportadoraConsumer.chamaCadastrarTransportadora(transportadora);
    }

    public static Call<Transportadora> posForLoginTransportadora(String userName, String password){
        TransportadoraConsumer transportadoraConsumer = getRetrofitInstance().create(TransportadoraConsumer.class);
        return transportadoraConsumer.loginTransportadora(userName, password);
    }

    public static Call<Transportadora> postForAddOnibus(Integer id, Onibus onibus){
        TransportadoraConsumer transportadoraConsumer = getRetrofitInstance().create(TransportadoraConsumer.class);
        return transportadoraConsumer.adicionarOnibus(id, onibus);
    }

    public static Call<Transportadora> postForAddMotorista(Integer id, Motorista motorista){
        TransportadoraConsumer transportadoraConsumer = getRetrofitInstance().create(TransportadoraConsumer.class);
        return transportadoraConsumer.adicionarMotorista(id, motorista);
    }

    public static Call<Transportadora> postForAddLinha(Integer id, Linha linha){
        TransportadoraConsumer transportadoraConsumer = getRetrofitInstance().create(TransportadoraConsumer.class);
        return transportadoraConsumer.adicionarLinha(id, linha);
    }

    public static Call<Transportadora> postForIniciarViagem(Viagem viagem){
        TransportadoraConsumer transportadoraConsumer = getRetrofitInstance().create(TransportadoraConsumer.class);
        return transportadoraConsumer.iniciarViagem(viagem);
    }

    public static Call<Transportadora> putForEfetuarPagamento(Integer id, String token, Double valor){
        TransportadoraConsumer transportadoraConsumer = getRetrofitInstance().create(TransportadoraConsumer.class);
        return transportadoraConsumer.efetuarPagamento(id, token, valor);
    }

    public static Call<Empresa> postForCreateEmpresa(Empresa empresa){
        EmpresaConsumer empresaConsumer = getRetrofitInstance().create(EmpresaConsumer.class);
        return empresaConsumer.chamaCadastrarEmpresa(empresa);
    }

    public static Call<Empresa> postForLoginEmpresa(String userName, String password){
        EmpresaConsumer empresaConsumer = getRetrofitInstance().create(EmpresaConsumer.class);
        return empresaConsumer.loginEmpresa(userName, password);
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
