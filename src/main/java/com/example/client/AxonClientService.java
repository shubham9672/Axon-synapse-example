package com.example.client;

// import java.util.List;
// import java.util.function.Function;
// import java.util.stream.Collectors;

// import org.antlr.v4.runtime.misc.Pair;
// import org.eclipse.microprofile.rest.client.inject.RestClient;
// import org.jboss.logging.Logger;

// import com.example.client.dto.Data;

// import jakarta.enterprise.context.ApplicationScoped;
// import jakarta.inject.Inject;

// @ApplicationScoped
// public class AxonClientService<C, E, Q> {

//     private static final Logger LOGGER = Logger.getLogger(AxonClient.class);

//     @Inject
//     @RestClient
//     AxonClient axonClient;

//     @Inject
//     ConfigService configService;

//     public List<Pair<E, Integer>> fetchAggregateEvents(C command, Function<C, String> aggregateIdProvider) {
//         String context = configService.getAxonContext();
//         String aggregateId = aggregateIdProvider.apply(command);

//         try {
//             Data data = axonClient.fetchAggregateEvents(context, aggregateId).readEntity(Data.class);
//             return data.getItems().stream()
//                     .map(item -> new Pair<>((E) item.getPayload(), item.getSequenceNumber()))
//                     .collect(Collectors.toList());
//         } catch (Exception e) {
//             LOGGER.error("Error fetching aggregate events", e);
//             throw new RuntimeException(e);
//         }
//     }
// }

import com.example.client.AxonClient;
import com.example.client.dto.Data;
import com.example.client.dto.HandlerRegistrationResponse;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@ApplicationScoped
public class AxonClientService<C, E, Q> {

    @Inject
    @RestClient
    AxonClient axonClient;

    @ConfigProperty(name = "axon.context", defaultValue = "default")
    String defaultContext;

    @ConfigProperty(name = "axon.api.url", defaultValue = "http://localhost:8080/v1")
    String axonApiUrl;

    public List<Object[]> fetchAggregateEvents(C command, Function<C, String> aggregateIdProvider) {
        String context = defaultContext;
        String aggregateId = aggregateIdProvider.apply(command);
        Response response = axonClient.fetchAggregateEvents(context, aggregateId);
        Data data = response.readEntity(Data.class);
        return data.getItems().stream().map(item -> new Object[] { item.getPayload(), item.getSequenceNumber() })
                .toList();
    }

    public Object[] appendEvent(E event, Function<E, String> aggregateIdProvider,
            Function<E, String> aggregateTypeProvider,
            Function<E, String> payloadTypeProvider, Integer latestVersion) {
        String context = defaultContext;
        int sequenceNumber = latestVersion != null ? latestVersion + 1 : 0;
        Response response = axonClient.appendEvent(context, payloadTypeProvider.apply(event), event,
                aggregateTypeProvider.apply(event), aggregateIdProvider.apply(event), sequenceNumber);
        return new Object[] { event, sequenceNumber };
    }

    public List<Object> publishCommand(C command, Function<C, String> payloadTypeProvider,
            Function<C, String> routingKeyProvider,
            Function<C, String> contextProvider) {
        String context = contextProvider.apply(command);
        String payloadType = payloadTypeProvider.apply(command);
        Response response = axonClient.publishCommand(context, payloadType, command, payloadType,
                routingKeyProvider.apply(command));
        return response.readEntity(List.class);
    }

    public Object publishQuery(Q query, Function<Q, String> payloadTypeProvider,
            Function<Q, String> responseTypeProvider,
            Function<Q, String> responseCardinalityProvider, Function<Q, String> contextProvider) {
        String context = contextProvider.apply(query);
        String payloadType = payloadTypeProvider.apply(query);
        Response response = axonClient.publishQuery(context, payloadType, query, payloadType,
                responseTypeProvider.apply(query), responseCardinalityProvider.apply(query));
        return response.readEntity(Object.class);
    }

    public HandlerRegistrationResponse registerEventHandler(String handlerId, List<String> events, String clientId,
            String componentName, String route) {
        String context = defaultContext;
        String callbackEndpoint = axonApiUrl + route;
        Map<String, Object> request = Map.of(
                "names", events,
                "endpoint", callbackEndpoint,
                "endpointType", "http-raw",
                "clientId", clientId,
                "componentName", componentName);
        Response response = axonClient.registerEventHandler(context, handlerId, request);
        return response.readEntity(HandlerRegistrationResponse.class);
    }

    public HandlerRegistrationResponse registerCommandHandler(String handlerId, List<String> commands, String clientId,
            String componentName, String route) {
        String context = defaultContext;
        String callbackEndpoint = axonApiUrl + route;
        Map<String, Object> request = Map.of(
                "names", commands,
                "endpoint", callbackEndpoint,
                "endpointType", "http-raw",
                "clientId", clientId,
                "componentName", componentName);
        Response response = axonClient.registerCommandHandler(context, handlerId, request);
        return response.readEntity(HandlerRegistrationResponse.class);
    }

    public HandlerRegistrationResponse registerQueryHandler(String handlerId, List<String> queries, String clientId, String componentName, String route) {
        String context = defaultContext;
        String callbackEndpoint = axonApiUrl + route;
        Map<String, Object> request = Map.of(
                "names", queries,
                "endpoint", callbackEndpoint,
                "endpointType", "http-raw",
                "clientId", clientId,
                "componentName", componentName
        );
        Response response = axonClient.registerQueryHandler(context, handlerId, request);
        return response.readEntity(HandlerRegistrationResponse.class);
    }
}
