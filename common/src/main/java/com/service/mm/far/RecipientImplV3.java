package com.service.mm.far;

import jakarta.annotation.Resource;
import jakarta.jws.HandlerChain;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceContext;
import se.gov.minameddelanden.recipient.ApplicationFaultV3;
import se.gov.minameddelanden.recipient.RecipientPortV3;
import se.gov.minameddelanden.schema.authority.SignatureAuthorizationResult;
import se.gov.minameddelanden.schema.common.SignatureData;
import se.gov.minameddelanden.schema.common.v3.ExceptionInformation;
import se.gov.minameddelanden.schema.recipient.*;
import se.gov.minameddelanden.schema.recipient.v2.AuthorizedAccount;
import se.gov.minameddelanden.schema.recipient.v2.OrganizationIdentity;
import se.gov.minameddelanden.schema.recipient.v2.PendingAccountPreferences;
import se.gov.minameddelanden.schema.recipient.v2.PendingChangeServiceSupplier;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.List;

@HandlerChain(file = "/handler-chain.xml")
@WebService(endpointInterface = "se.gov.minameddelanden.recipient.RecipientPortV3")
public class RecipientImplV3 implements RecipientPortV3 {

    @Resource
    WebServiceContext context;

    @Override
    public AccountPreferences getAccountPreferences(String recipientId, String userIdentity) {
        return null;
    }

    @Override
    public PendingAccountPreferences getPendingAccountPreferences(String id, String userIdentity) {
        return null;
    }

    @Override
    public OrganizationIdentity deletePendingAccountPreferences(OrganizationIdentity organizationIdentity) {
        return null;
    }

    @Override
    public SignatureAuthorizationResult storeAccountPreferences(String id, String userIdentity, AccountPreferences preferences, String agreementText, List<SignatureData> signatureData) {
        return null;
    }

    @Override
    public List<ReachabilityStatus> isReachable(String senderOrgNr, List<String> recipientId) throws ApplicationFaultV3 {
        ReachabilityStatus status = new ReachabilityStatus();
        AccountStatus accountStatus = new AccountStatus();
        status.setAccountStatus(accountStatus);

        final List<ReachabilityStatus> response = new java.util.ArrayList<>();

        try {
            accountStatus.setLastRegistration(DatatypeFactory.newInstance().newXMLGregorianCalendar(
                    2017, 1, 1, 1, 1, 1, 1, 0));
        } catch (DatatypeConfigurationException e) {
            throw new ApplicationFaultV3("Internal error, could not create date", new ExceptionInformation(), e);
        }
        accountStatus.setPending(false);

        response.add(status);

        return response;
    }

    @Override
    public List<AccountStatus> isRegistered(List<String> recipientId) {
        return null;
    }

    @Override
    public SignatureAuthorizationResult register(AccountRequest accountRequest, AccountTypes accountType, List<SignatureData> signature) {
        return null;
    }

    @Override
    public SignatureAuthorizationResult deregister(AccountRequest deregisterAccount, List<SignatureData> signature) {
        return null;
    }

    @Override
    public List<PendingAccountRequest> getPendingAccountRequest(List<String> recipientId, String userIdentity) {
        return null;
    }

    @Override
    public void deletePendingAccountRequest(String recipientId, String userIdentity) {

    }

    @Override
    public SignatureAuthorizationResult changeServiceSupplier(String id, String userIdentity, String serviceSupplier, String agreementText, List<SignatureData> signatureData) throws ApplicationFaultV3 {
        return null;
    }

    @Override
    public PendingChangeServiceSupplier getPendingChangeServiceSupplier(String id, String userIdentity) {
        return null;
    }

    @Override
    public ServiceSupplier deletePendingChangeServiceSupplier(OrganizationIdentity organizationIdentity) {
        return null;
    }

    @Override
    public AuthorizedAccount isAuthorizedSigner(String companyId, String userIdentity) {
        return null;
    }
}
