package com.testcorp.handler;

import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class DefaultHandler implements SOAPHandler<SOAPMessageContext> {

    private static Logger logger = LoggerFactory.getLogger(DefaultHandler.class);

    public Set<QName> getHeaders() {
        return Collections.emptySet();
    }

    public boolean handleMessage(SOAPMessageContext context) {
        logger.info(isOutbound(context) ? "Message in outbound direction." : "Message in inbound direction.");

        SOAPMessage message = context.getMessage();
        try {
            message.writeTo(System.out);
        } catch (SOAPException e) {
            logger.error("SOAPException during message.writeTo()", e);
        } catch (IOException e) {
            logger.error("IOException during message.writeTo()", e);
        }

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
