package br.com.wtcc.loginapi.dto.response;

import java.util.List;

public class ResponseError extends Response{
    private String error;
    private List<String> errors;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
