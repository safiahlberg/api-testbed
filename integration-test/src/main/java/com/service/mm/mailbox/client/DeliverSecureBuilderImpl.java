package com.service.mm.mailbox.client;

import org.w3c.dom.Element;
import se.gov.minameddelanden.schema.message.v3.*;
import se.gov.minameddelanden.schema.officialmatter.v3.OfficialMatterExtension;
import se.gov.minameddelanden.schema.service.v3.DeliverSecure;

import java.util.Collection;

public class DeliverSecureBuilderImpl implements DeliverSecureBuilder {
    private DeliverSecure deliverSecureTmp;

    public DeliverSecureBuilderImpl() {
        deliverSecureTmp = new DeliverSecure();

        final SealedDelivery sealedDelivery = new SealedDelivery();
        deliverSecureTmp.setDeliverSecure(sealedDelivery);

        final SignedDelivery signedDelivery = new SignedDelivery();
        deliverSecureTmp.getDeliverSecure().setSignedDelivery(signedDelivery);

        final SecureDelivery secureDelivery = new SecureDelivery();
        deliverSecureTmp.getDeliverSecure().getSignedDelivery().setDelivery(secureDelivery);

        final SecureMessage secureMessage = new SecureMessage();
        deliverSecureTmp.getDeliverSecure().getSignedDelivery().getDelivery().getMessages().add(secureMessage);

        final MessageHeader messageHeader = new MessageHeader();
        deliverSecureTmp.getDeliverSecure().getSignedDelivery().getDelivery().getMessages().forEach(secureMessage1 -> secureMessage.setHeader(messageHeader));
    }

    @Override
    public DeliverSecure build() {
        DeliverSecure deliverSecure = new DeliverSecure();
        deliverSecure.setDeliverSecure(deliverSecureTmp.getDeliverSecure());
        deliverSecure.getDeliverSecure()
                .setSignedDelivery(deliverSecureTmp.getDeliverSecure()
                        .getSignedDelivery());
        deliverSecure.getDeliverSecure().getSignedDelivery()
                .setDelivery(deliverSecureTmp.getDeliverSecure().getSignedDelivery()
                .getDelivery());
        deliverSecure.getDeliverSecure().getSignedDelivery().getDelivery()
                .getMessages().addAll(deliverSecureTmp.getDeliverSecure().getSignedDelivery().getDelivery()
                .getMessages());

        return deliverSecure;
    }

    @Override
    public DeliverSecureBuilder setSealedDelivery(SealedDelivery sealedDelivery) {
        deliverSecureTmp.setDeliverSecure(sealedDelivery);
        return this;
    }

    @Override
    public DeliverSecureBuilder setSignedDelivery(SignedDelivery signedDelivery) {
        deliverSecureTmp.getDeliverSecure().setSignedDelivery(signedDelivery);

        return this;
    }

    @Override
    public DeliverSecureBuilder setSecureDelivery(SecureDelivery secureDelivery) {
        deliverSecureTmp.getDeliverSecure().getSignedDelivery().setDelivery(secureDelivery);

        return this;
    }

    @Override
    public DeliverSecureBuilder addMessages(Collection<SecureMessage> messages) {
        deliverSecureTmp.getDeliverSecure().getSignedDelivery().getDelivery().getMessages().addAll(messages);

        return this;
    }

    @Override
    public DeliverSecureBuilder setMessageHeader(MessageHeader messageHeader) {
        deliverSecureTmp.getDeliverSecure().getSignedDelivery().getDelivery()
                .getMessages().forEach(message -> message.setHeader(messageHeader));

        return this;
    }

    @Override
    public DeliverSecureBuilder setOfficialMatter(OfficialMatter officialMatter) {
        deliverSecureTmp.getDeliverSecure().getSignedDelivery().getDelivery().getMessages()
                .forEach(message -> message.getHeader().setOfficialMatter(officialMatter));

        return this;
    }

    @Override
    public DeliverSecureBuilder setOfficialMatterAnies(Collection<Element> anies) {
        deliverSecureTmp.getDeliverSecure().getSignedDelivery().getDelivery().getMessages()
                .forEach(message -> message.getHeader().getOfficialMatter().getAnies().addAll(anies));

        return this;
    }

    @Override
    public DeliverSecureBuilder setOfficialMatterExtension(OfficialMatterExtension officialMatterExtension) {
        deliverSecureTmp.getDeliverSecure().getSignedDelivery().getDelivery().getMessages()
                .forEach(message -> message.getHeader().getOfficialMatter().setOfficialMatterExtension(officialMatterExtension));

        return this;
    }


}
