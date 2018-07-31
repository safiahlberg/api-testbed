package com.service.mm.mailbox;

import se.gov.minameddelanden.schema.service.v3.*;
import se.gov.minameddelanden.service.ApplicationFaultV3;
import se.gov.minameddelanden.service.ServicePortV3;

import javax.jws.HandlerChain;
import javax.jws.WebService;

@HandlerChain(file = "/handler-chain.xml")
@WebService(endpointInterface = "se.gov.minameddelanden.service.ServicePortV3")
public class ServiceImplV3 implements ServicePortV3 {

    @Override
    public NotifyResponse notify(Notify parameters) throws ApplicationFaultV3 {
        return new NotifyResponse();
    }

    @Override
    public DeliverSecureResponse deliverSecure(DeliverSecure parameters) throws ApplicationFaultV3 {
        return new DeliverSecureResponse();
    }

    @Override
    public DeliverForwardResponse deliverForward(DeliverForward parameters) throws ApplicationFaultV3 {
        return new DeliverForwardResponse();
    }
}
