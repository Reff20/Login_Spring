package br.com.wtcc.loginapi.dto.response;

public class ResponseSuccess extends Response{
    private Boolean renovar;

    public Boolean getRenovar() {
        return renovar;
    }

    public void setRenovar(Boolean renovar) {
        this.renovar = renovar;
    }
}
