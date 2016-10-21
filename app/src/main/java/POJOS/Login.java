package POJOS;

import java.io.Serializable;

public class Login implements Serializable {

    private String userName;

    private String password;

    private String token;

    private Usuario usuario;


    public String getUserName() {
        return userName;
    }




    public void setUserName(String userName) {
        this.userName = userName;
    }




    public String getPassword() {
        return password;
    }




    public void setPassword(String password) {
        this.password = password;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Login(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }




    public Login() {

    }



}
