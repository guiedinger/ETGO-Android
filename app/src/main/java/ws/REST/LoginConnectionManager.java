package ws.REST;

import POJOS.Passageiro;
import POJOS.Token;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static ws.REST.ServiceGenerator.API_BASE_URL;

/**
 * Created by Guilherme on 01/12/2016.
 */

public class LoginConnectionManager {

    public static Retrofit retrofit;

    public static Call<Passageiro> postForCreate(Passageiro passageiro){
        PassageiroLoginService passageiroLoginService = getRetrofitInstance().create(PassageiroLoginService.class);
        return passageiroLoginService.chamaCadastrarPassageiro(passageiro);
    }
    public static Call<Passageiro> posForLogin(String userName, String password){
        PassageiroLoginService passageiroLoginService = getRetrofitInstance().create(PassageiroLoginService.class);
        return passageiroLoginService.loginPassageiro(userName, password);
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
