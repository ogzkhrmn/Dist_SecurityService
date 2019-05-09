package com.bank.security.dao;

import com.bank.security.core.HibernateConfiguration;
import org.hibernate.Session;

public class AbstractDao {

    protected Session getSessionFactory(){
        return HibernateConfiguration.getSession().getCurrentSession();
    }

}
