package com.service.mm.mailbox;

import se.gov.minameddelanden.schema.message.v3.SealedDelivery;
import se.gov.minameddelanden.schema.notification.v3.NotificationDelivery;
import se.gov.minameddelanden.schema.notification.v3.NotifyResult;
import se.gov.minameddelanden.schema.service.DeliveryResult;
import se.gov.minameddelanden.schema.service.v3.ForwardDelivery;
import se.gov.minameddelanden.service.*;

class MailboxDelegate {

    public DeliveryResult deliverSecureV3(SealedDelivery deliverSecure) throws ApplicationFaultV3 {
        return new DeliveryResult();
    }

    public DeliveryResult deliverForwardV3(ForwardDelivery forwardMessage) throws ApplicationFaultV3 {
        return new DeliveryResult();
    }

    public NotifyResult notifyV3(NotificationDelivery notify) throws ApplicationFaultV3 {
        return new NotifyResult();
    }

    public DeliveryResult deliverSecureV2(se.gov.minameddelanden.schema.message.v2.SealedDelivery deliverSecure) throws DeliverSecureFault {
        return new DeliveryResult();
    }

    public DeliveryResult deliverForwardV2(se.gov.minameddelanden.schema.message.ForwardDelivery arg0) throws DeliverForwardFault {
        return new DeliveryResult();
    }

    public se.gov.minameddelanden.schema.notification.NotifyResult notifyV2(se.gov.minameddelanden.schema.notification.v2.NotificationDelivery notify) throws NotifyFault {
        return new se.gov.minameddelanden.schema.notification.NotifyResult();
    }
}
