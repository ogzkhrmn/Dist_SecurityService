package com.bank.security.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SecureUser implements Serializable {

    private boolean isSecure;

    public boolean isSecure() {
        return isSecure;
    }

    public void setSecure(boolean secure) {
        isSecure = secure;
    }
}
