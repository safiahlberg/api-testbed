package com.service.mm.mailbox.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Collections;
import java.util.Set;

public class DefaultHandler implements SOAPHandler<SOAPMessageContext> {

    private static Logger logger = LogManager.getLogger(DefaultHandler.class);

    public Set<QName> getHeaders() {
        return Collections.emptySet();
    }

    public boolean handleMessage(SOAPMessageContext context) {
        logger.info(isOutbound(context) ? "Message in outbound direction." : "Message in inbound direction.");
        return true;
    }

    public boolean handleFault(SOAPMessageContext context) {
        logger.error(isOutbound(context) ? "Fault in outbound direction." : "Fault in inbound direction.");
        return false;
    }

    public void close(MessageContext context) {
    }

    private Boolean isOutbound(SOAPMessageContext context) {
        return (Boolean)
                context.get (MessageContext.MESSAGE_OUTBOUND_PROPERTY);

    }
}
