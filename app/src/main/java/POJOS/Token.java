package POJOS;

import java.io.Serializable;
import java.util.Date;

public class Token implements Serializable {

    private Integer idToken;

    private String token;

    private Usuario usuario;

    private Date data;

    public Integer getIdToken() {
        return idToken;
    }

    public void setIdToken(Integer idToken) {
        this.idToken = idToken;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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

    public Token(Integer idToken, Date data, String token, Usuario usuario) {
        this.idToken = idToken;
        this.data = data;
        this.token = token;
        this.usuario = usuario;
    }

    public Token() {

    }



}
