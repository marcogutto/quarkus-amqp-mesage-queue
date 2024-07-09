package org.acme.resource;

import org.acme.domain.Document;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.reactive.messaging.MutinyEmitter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/v1")
@ApplicationScoped
public class ProducerResource {

    private static final Logger logger = LoggerFactory.getLogger(ProducerResource.class);
    
    @Inject
    @Channel("words-out")
    MutinyEmitter<Document> emitter;

    @POST
    public void send(Document document) {
        logger.info(document.getText());
        emitter.send(document)
                .subscribe()
                .with(
                    ack -> logger.info("Documento enviado para fila com sucesso: " + document.getText()),
                    failure -> logger.error("Erro ao enviar documento para fila.", failure)
                );
    }

}
