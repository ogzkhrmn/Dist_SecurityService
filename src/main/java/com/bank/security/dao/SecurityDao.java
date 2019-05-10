package com.bank.security.dao;

public interface SecurityDao {

    boolean isSecure(String tckn);

    Long saveError(String tckn);
}
