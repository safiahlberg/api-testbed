package com.service.mm.mailbox;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se.gov.minameddelanden.schema.service.v3.*;
import se.gov.minameddelanden.service.ServicePortV3;

import javax.jws.HandlerChain;
import javax.jws.WebService;

@HandlerChain(file = "/handler-chain.xml")
@WebService(endpointInterface = "se.gov.minameddelanden.service.ServicePortV3")
public class ServiceImplV3 implements ServicePortV3 {
    private static Logger logger = LogManager.getLogger(ServiceImplV3.class);

    @Override
    public NotifyResponse notify(Notify message) {
        return new NotifyResponse();
    }

    @Override
    public DeliverSecureResponse deliverSecure(DeliverSecure message) {

        message.getDeliverSecure()
                .getSignedDelivery()
                .getDelivery()
                .getMessages()
                .forEach(
                        secureMessage -> secureMessage.getHeader()
                                .getOfficialMatter().getAnies()
                                .forEach(element -> logger.info(element::getTagName))
                );

        return new DeliverSecureResponse();
    }

    @Override
    public DeliverForwardResponse deliverForward(DeliverForward message) {
        return new DeliverForwardResponse();
    }
}
