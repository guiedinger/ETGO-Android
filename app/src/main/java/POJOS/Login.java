package POJOS;

/**
 * Created by Guilherme on 20/10/2016.
 */
public class Login {

    private String userName;
    private String senha;

    public Login(String senha, String userName) {
        this.senha = senha;
        this.userName = userName;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
