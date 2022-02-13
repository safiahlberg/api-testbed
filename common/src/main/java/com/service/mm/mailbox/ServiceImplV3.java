package com.service.mm.mailbox;

import jakarta.jws.HandlerChain;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.gov.minameddelanden.schema.message.v3.SealedDelivery;
import se.gov.minameddelanden.schema.message.v3.SecureMessage;
import se.gov.minameddelanden.schema.notification.v3.NotificationDelivery;
import se.gov.minameddelanden.schema.notification.v3.NotifyResult;
import se.gov.minameddelanden.schema.service.DeliveryResult;
import se.gov.minameddelanden.schema.service.v3.*;
import se.gov.minameddelanden.service.ApplicationFaultV3;
import se.gov.minameddelanden.service.ServicePortV3;

@HandlerChain(file = "/handler-chain.xml")
@WebService(endpointInterface = "se.gov.minameddelanden.service.ServicePortV3")
public class ServiceImplV3 implements ServicePortV3 {
    private static Logger logger = LoggerFactory.getLogger(ServiceImplV3.class);

    private static void accept(SecureMessage m) {
        logger.info("Message: {}");
        m.getHeader().getOfficialMatter().getAny().forEach(element -> logger.info(element.toString()));
    }

    @Override
    public NotifyResult notify(NotificationDelivery notify) throws ApplicationFaultV3 {
        return new NotifyResult();
    }

    @Override
    public DeliveryResult deliverSecure(

                    SealedDelivery message) {

        message.getSignedDelivery()
                .getDelivery()
                .getMessage()
                        .forEach(ServiceImplV3::accept);

        return new DeliveryResult();
    }

    @Override
    public DeliveryResult deliverForward(ForwardDelivery forwardMessage) throws ApplicationFaultV3 {
        return new DeliveryResult();
    }

}
