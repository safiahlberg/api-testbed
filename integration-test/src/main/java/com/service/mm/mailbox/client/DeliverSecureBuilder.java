package com.service.mm.mailbox.client;

import org.w3c.dom.Element;
import se.gov.minameddelanden.schema.message.v3.*;
import se.gov.minameddelanden.schema.officialmatter.v3.OfficialMatterExtension;
import se.gov.minameddelanden.schema.service.v3.DeliverSecure;

import java.util.Collection;

public interface DeliverSecureBuilder {
    DeliverSecure build();

    DeliverSecureBuilder setSealedDelivery(final SealedDelivery sealedDelivery);

    DeliverSecureBuilder setSignedDelivery(final SignedDelivery signedDelivery);

    DeliverSecureBuilder setSecureDelivery(SecureDelivery secureDelivery);

    DeliverSecureBuilder addMessages(Collection<SecureMessage> messages);

    DeliverSecureBuilder setMessageHeader(MessageHeader messageHeader);

    DeliverSecureBuilder setOfficialMatter(OfficialMatter officialMatter);

    DeliverSecureBuilder setOfficialMatterAnies(Collection<Element> anies);

    DeliverSecureBuilder setOfficialMatterExtension(OfficialMatterExtension officialMatterExtension);
}
