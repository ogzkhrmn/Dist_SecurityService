package com.bank.security.dao.impl;

import com.bank.security.core.annotation.RealTransaction;
import com.bank.security.core.annotation.ServiceBean;
import com.bank.security.dao.AbstractDao;
import com.bank.security.dao.SecurityDao;
import com.bank.security.entities.Security;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@ServiceBean("secuirtyDao")
public class SecuirtyDaoImpl extends AbstractDao implements SecurityDao {

    @Override
    @RealTransaction
    public boolean isSecure(String tckn) {
        Session session = getSessionFactory();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Security> query = builder.createQuery(Security.class);
        Root<Security> root = query.from(Security.class);
        query.select(root).where(builder.equal(root.get("tckn"),tckn));

        return !CollectionUtils.isNotEmpty(session.createQuery(query).getResultList());
    }
}
