package com.bank.security.service;

import com.bank.security.core.annotation.Bean;
import com.bank.security.dao.SecurityDao;
import com.bank.security.model.RequestModel;
import com.bank.security.model.ResponseModel;
import com.bank.security.model.SecureUser;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;

@Path("/security")
public class SecurityService implements Serializable {

    @Bean
    private static SecurityDao securityDao;

    @POST
    @Path("/isUserSecure")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SecureUser isUserSecure(RequestModel model) {
        SecureUser secureUser = new SecureUser();
        try {
            secureUser.setSuccess(securityDao.isSecure(model.getTckn()));
        } catch (Exception e) {
            secureUser.setSuccess(true);
            securityDao.saveError(model.getTckn());
        }
        return secureUser;
    }

    @POST
    @Path("/control")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseModel isAlive() {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setSuccess(true);
        return responseModel;
    }

}
