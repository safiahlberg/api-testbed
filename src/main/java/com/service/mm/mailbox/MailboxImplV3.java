package com.service.mm.mailbox;

import se.gov.minameddelanden.schema.message.v3.SealedDelivery;
import se.gov.minameddelanden.schema.notification.v3.NotificationDelivery;
import se.gov.minameddelanden.schema.notification.v3.NotifyResult;
import se.gov.minameddelanden.schema.service.DeliveryResult;
import se.gov.minameddelanden.schema.service.v3.ForwardDelivery;
import se.gov.minameddelanden.service.ApplicationFaultV3;
import se.gov.minameddelanden.service.ServicePortV3;

import javax.jws.HandlerChain;
import javax.jws.WebService;

@HandlerChain(file = "handler-chain.xml")
@WebService(endpointInterface = "se.gov.minameddelanden.service.ServicePortV3")
public class MailboxImplV3 implements ServicePortV3 {

    public DeliveryResult deliverSecure(SealedDelivery deliverSecure) throws ApplicationFaultV3 {
        return new MailboxDelegate().deliverSecureV3(deliverSecure);
    }

    public DeliveryResult deliverForward(ForwardDelivery forwardMessage) throws ApplicationFaultV3 {
        return new MailboxDelegate().deliverForwardV3(forwardMessage);
    }

    public NotifyResult notify(NotificationDelivery notify) throws ApplicationFaultV3 {
        return new MailboxDelegate().notifyV3(notify);
    }
}
