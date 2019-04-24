package com.service.mm.far;

import se.gov.minameddelanden.recipient.ApplicationFaultV3;
import se.gov.minameddelanden.recipient.RecipientPortV3;
import se.gov.minameddelanden.schema.common.v3.ExceptionInformation;
import se.gov.minameddelanden.schema.recipient.AccountStatus;
import se.gov.minameddelanden.schema.recipient.ReachabilityStatus;
import se.gov.minameddelanden.schema.recipient.v2.*;
import se.gov.minameddelanden.schema.recipient.v3.*;

import javax.annotation.Resource;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.ws.WebServiceContext;


@HandlerChain(file = "/handler-chain.xml")
@WebService(endpointInterface = "se.gov.minameddelanden.recipient.RecipientPortV3")
public class RecipientImplV3 implements RecipientPortV3 {

    public static final String TARGETNAMESPACE = "http://minameddelanden.gov.se/Recipient";

    @Resource
    WebServiceContext context;

    @Override
    public GetAccountPreferencesResponse getAccountPreferences(GetAccountPreferences parameters) {
        return null;
    }

    @Override
    public GetPendingAccountPreferencesResponse getPendingAccountPreferences(GetPendingAccountPreferences parameters) {
        return null;
    }

    @Override
    public DeletePendingAccountPreferencesResponse deletePendingAccountPreferences(DeletePendingAccountPreferences parameters) {
        return null;
    }

    @Override
    public StoreAccountPreferencesResponse storeAccountPreferences(StoreAccountPreferences parameters) {
        return null;
    }

    @Override
    public IsReachableResponse isReachable(IsReachable parameters) throws ApplicationFaultV3 {
        ReachabilityStatus status = new ReachabilityStatus();
        AccountStatus accountStatus = new AccountStatus();
        status.setAccountStatus(accountStatus);
        IsReachableResponse response = new IsReachableResponse();

        try {
            accountStatus.setLastRegistration(DatatypeFactory.newInstance().newXMLGregorianCalendar(
                    2017, 1, 1, 1, 1, 1, 1, 0));
        } catch (DatatypeConfigurationException e) {
            throw new ApplicationFaultV3("Internal error, could not create date", new ExceptionInformation(), e);
        }
        accountStatus.setPending(false);

        response.getReturn().add(status);

        return response;
    }

    @Override
    public IsRegisteredResponse isRegistered(IsRegistered parameters) {
        return null;
    }

    @Override
    public RegisterResponse register(Register parameters) {
        return null;
    }

    @Override
    public DeregisterResponse deregister(Deregister parameters) {
        return null;
    }

    @Override
    public GetPendingAccountRequestResponse getPendingAccountRequest(GetPendingAccountRequest parameters) {
        return null;
    }

    @Override
    public DeletePendingAccountRequestResponse deletePendingAccountRequest(DeletePendingAccountRequest parameters) {
        return null;
    }

    @Override
    public ChangeServiceSupplierResponse changeServiceSupplier(ChangeServiceSupplier parameters) {
        return null;
    }

    @Override
    public GetPendingChangeServiceSupplierResponse getPendingChangeServiceSupplier(GetPendingChangeServiceSupplier parameters) {
        return null;
    }

    @Override
    public DeletePendingChangeServiceSupplierResponse deletePendingChangeServiceSupplier(DeletePendingChangeServiceSupplier parameters) {
        return null;
    }

    @Override
    public IsAuthorizedSignerResponse isAuthorizedSigner(IsAuthorizedSigner parameters) {
        return null;
    }
}
