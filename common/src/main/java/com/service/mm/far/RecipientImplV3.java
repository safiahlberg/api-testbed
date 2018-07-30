package com.service.mm.far;

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

import javax.annotation.Resource;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.ws.WebServiceContext;
import java.util.Arrays;
import java.util.List;


@HandlerChain(file = "/handler-chain.xml")
@WebService(endpointInterface = "se.gov.minameddelanden.recipient.RecipientPortV3")
public class RecipientImplV3 implements RecipientPortV3 {

    public static final String TARGETNAMESPACE = "http://minameddelanden.gov.se/Recipient";

    @Resource
    WebServiceContext context;

    @Override
    public AccountPreferences getAccountPreferences(String recipientId, String userIdentity) throws ApplicationFaultV3 {
        return null;
    }

    @Override
    public PendingAccountPreferences getPendingAccountPreferences(String id, String userIdentity) throws ApplicationFaultV3 {
        return null;
    }

    @Override
    public OrganizationIdentity deletePendingAccountPreferences(OrganizationIdentity organizationIdentity) throws ApplicationFaultV3 {
        return null;
    }

    @Override
    public SignatureAuthorizationResult storeAccountPreferences(String id, String userIdentity, AccountPreferences preferences, String agreementText, List<SignatureData> signatureData) throws ApplicationFaultV3 {
        return null;
    }

    @Override
    public List<ReachabilityStatus> isReachable(String senderOrgNr, List<String> recipientId) throws ApplicationFaultV3 {
        ReachabilityStatus status = new ReachabilityStatus();
        AccountStatus accountStatus = new AccountStatus();
        status.setAccountStatus(accountStatus);

        try {
            accountStatus.setLastRegistration(DatatypeFactory.newInstance().newXMLGregorianCalendar(
                    2017, 1, 1, 1, 1, 1, 1, 0));
        } catch (DatatypeConfigurationException e) {
            throw new ApplicationFaultV3("Internal error, could not create date", new ExceptionInformation(), e);
        }
        accountStatus.setPending(false);

        return Arrays.asList(new ReachabilityStatus());
    }

    @Override
    public List<AccountStatus> isRegistered(List<String> recipientId) throws ApplicationFaultV3 {
        return null;
    }

    @Override
    public SignatureAuthorizationResult register(AccountRequest accountRequest, AccountTypes accountType, List<SignatureData> signature) throws ApplicationFaultV3 {
        return null;
    }

    @Override
    public SignatureAuthorizationResult deregister(AccountRequest deregisterAccount, List<SignatureData> signature) throws ApplicationFaultV3 {
        return null;
    }

    @Override
    public List<PendingAccountRequest> getPendingAccountRequest(List<String> recipientId, String userIdentity) throws ApplicationFaultV3 {
        return null;
    }

    @Override
    public void deletePendingAccountRequest(String recipientId, String userIdentity) throws ApplicationFaultV3 {

    }

    @Override
    public SignatureAuthorizationResult changeServiceSupplier(String id, String userIdentity, String serviceSupplier, String agreementText, List<SignatureData> signatureData) throws ApplicationFaultV3 {
        return null;
    }

    @Override
    public PendingChangeServiceSupplier getPendingChangeServiceSupplier(String id, String userIdentity) throws ApplicationFaultV3 {
        return null;
    }

    @Override
    public ServiceSupplier deletePendingChangeServiceSupplier(OrganizationIdentity organizationIdentity) throws ApplicationFaultV3 {
        return null;
    }

    @Override
    public AuthorizedAccount isAuthorizedSigner(String companyId, String userIdentity) throws ApplicationFaultV3 {
        return null;
    }
}
