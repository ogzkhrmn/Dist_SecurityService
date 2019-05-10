package com.bank.security.dao.impl;

import com.bank.security.core.annotation.RealTransaction;
import com.bank.security.core.annotation.ServiceBean;
import com.bank.security.dao.AbstractDao;
import com.bank.security.dao.SecurityDao;
import com.bank.security.entities.Security;
import com.bank.security.entities.SecurityError;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@ServiceBean("securityDao")
public class SecuirtyDaoImpl extends AbstractDao implements SecurityDao {

    @Override
    @RealTransaction
    public boolean isSecure(String tckn) {
        Session session = getSessionFactory();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Security> query = builder.createQuery(Security.class);
        Root<Security> root = query.from(Security.class);
        query.select(root).where(builder.equal(root.get("tckn"), tckn));
        return !CollectionUtils.isNotEmpty(session.createQuery(query).getResultList());
    }

    @Override
    @RealTransaction
    public Long saveError(String tckn) {
        SecurityError errorEntity = new SecurityError();
        errorEntity.setTckn(tckn);
        errorEntity.setModule("SECURITY_SERVICE");
        errorEntity.setSuccess(false);
        getSessionFactory().save(errorEntity);
        return errorEntity.getId();
    }
}
