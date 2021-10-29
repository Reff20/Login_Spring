package br.com.wtcc.loginapi.dto.response;

public class Response {
    private String msg;
    private Integer status;
    private String acess;
    private String token;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAcess() {
        return acess;
    }

    public void setAcess(String acess) {
        this.acess = acess;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
