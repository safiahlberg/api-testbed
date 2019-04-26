package com.service.mm.mailbox;

import com.google.gson.Gson;
import se.gov.minameddelanden.schema.service.v3.*;
import se.gov.minameddelanden.service.ServicePortV3;

import javax.jws.HandlerChain;
import javax.jws.WebService;

@HandlerChain(file = "/handler-chain.xml")
@WebService(endpointInterface = "se.gov.minameddelanden.service.ServicePortV3")
public class ServiceImplV3 implements ServicePortV3 {

    @Override
    public NotifyResponse notify(Notify message) {
        return new NotifyResponse();
    }

    @Override
    public DeliverSecureResponse deliverSecure(DeliverSecure message) {
        Gson gson = new Gson();

        message.getDeliverSecure()
                .getSignedDelivery()
                .getDelivery()
                .getMessage()
                .forEach(
                        secureMessage -> System.out.println(gson.toJson(secureMessage.getHeader().getOfficialMatter().getAny()))
                );

        return new DeliverSecureResponse();
    }

    @Override
    public DeliverForwardResponse deliverForward(DeliverForward message) {
        return new DeliverForwardResponse();
    }
}
