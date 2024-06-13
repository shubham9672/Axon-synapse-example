package com.example.queries;

import org.jboss.logging.Logger;

import com.example.api.GiftCardCommand;
import com.example.api.GiftCardEvent;
import com.example.api.GiftCardQuery;
import com.example.client.AxonClientService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import java.util.function.Function;

@ApplicationScoped
public class GiftCardQueryGateway {

    private static final Logger logger = Logger.getLogger(GiftCardQueryGateway.class);

    @Inject
    AxonClientService<GiftCardCommand, GiftCardEvent, GiftCardQuery> axonClient;

    @ConfigProperty(name = "axon.context", defaultValue = "default")
    String defaultContext;

    public Object publishQuery(GiftCardQuery query, QueryResponseCardinality cardinality) {
        logger.infof("Dispatching query %s with body %s", query.getKind(), query);
        Function<GiftCardQuery, String> contextProvider = q -> defaultContext;

        return axonClient.publishQuery(
                query,
                GiftCardQuery::getKind,
                q -> "GiftCardSummary",
                q -> "multiple",
                contextProvider);
    }

    public enum QueryResponseCardinality {
        SINGLE, MULTIPLE
    }
}
