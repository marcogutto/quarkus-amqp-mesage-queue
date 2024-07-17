package org.acme.service;

import org.acme.domain.Document;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);
    
    @Incoming("words-in")
    @Outgoing("words")
    public Document processaMensagemAssincrona(JsonObject d) {

        Document document = d.mapTo(Document.class);

        logger.info("Documento recebido da fila: " + document.getText());

        return document;

    }

}
