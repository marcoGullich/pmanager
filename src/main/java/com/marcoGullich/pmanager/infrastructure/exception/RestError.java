package com.marcoGullich.pmanager.infrastructure.exception;

import java.util.List;

public class RestError {

    private String erroCode;
    private String errorMensage;
    private List<String> details;
    private int status;
    private String path;

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public String getErroCode() {
        return erroCode;
    }

    public void setErroCode(String erroCode) {
        this.erroCode = erroCode;
    }

    public String getErrorMensage() {
        return errorMensage;
    }

    public void setErrorMensage(String errorMensage) {
        this.errorMensage = errorMensage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
