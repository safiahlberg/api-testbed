package com.service.mm.mailbox;

import se.gov.minameddelanden.schema.service.v3.*;
import se.gov.minameddelanden.service.ServicePortV3;

import javax.jws.HandlerChain;
import javax.jws.WebService;

@HandlerChain(file = "/handler-chain.xml")
@WebService(endpointInterface = "se.gov.minameddelanden.service.ServicePortV3")
public class ServiceImplV3 implements ServicePortV3 {

    @Override
    public NotifyResponse notify(Notify parameters) {
        return new NotifyResponse();
    }

    @Override
    public DeliverSecureResponse deliverSecure(DeliverSecure parameters) {
        return new DeliverSecureResponse();
    }

    @Override
    public DeliverForwardResponse deliverForward(DeliverForward parameters) {
        return new DeliverForwardResponse();
    }
}
