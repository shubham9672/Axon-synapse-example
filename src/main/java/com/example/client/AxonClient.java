package com.example.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@RegisterRestClient(configKey = "axon-api")
public interface AxonClient {

        @GET
        @Path("/contexts/{context}/aggregate/{aggregateId}/events")
        @Produces(MediaType.APPLICATION_JSON)
        Response fetchAggregateEvents(@PathParam("context") String context,
                        @PathParam("aggregateId") String aggregateId);

        @POST
        @Path("/contexts/{context}/events/{payloadType}")
        @Consumes(MediaType.APPLICATION_JSON)
        Response appendEvent(@PathParam("context") String context,
                        @PathParam("payloadType") String payloadType,
                        Object event,
                        @HeaderParam("AxonIQ-AggregateType") String aggregateType,
                        @HeaderParam("AxonIQ-AggregateId") String aggregateId,
                        @HeaderParam("AxonIQ-SequenceNumber") int sequenceNumber);

        @POST
        @Path("/contexts/{context}/commands/{payloadType}")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        Response publishCommand(@PathParam("context") String context,
                        @PathParam("payloadType") String payloadType,
                        Object command,
                        @HeaderParam("AxonIQ-PayloadType") String payloadTypeHeader,
                        @HeaderParam("AxonIQ-RoutingKey") String routingKey);

        @POST
        @Path("/contexts/{context}/queries/{payloadType}")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        Response publishQuery(@PathParam("context") String context,
                        @PathParam("payloadType") String payloadType,
                        Object query,
                        @HeaderParam("AxonIQ-PayloadType") String payloadTypeHeader,
                        @HeaderParam("AxonIQ-ResponseType") String responseType,
                        @HeaderParam("AxonIQ-ResponseCardinality") String responseCardinality);

        @PUT
        @Path("/contexts/{context}/handlers/events/{handlerId}")
        @Consumes(MediaType.APPLICATION_JSON)
        Response registerEventHandler(@PathParam("context") String context,
                        @PathParam("handlerId") String handlerId,
                        Map<String, Object> request);

        @PUT
        @Path("/contexts/{context}/handlers/commands/{handlerId}")
        @Consumes(MediaType.APPLICATION_JSON)
        Response registerCommandHandler(@PathParam("context") String context,
                        @PathParam("handlerId") String handlerId,
                        Map<String, Object> request);

        @PUT
        @Path("/contexts/{context}/handlers/queries/{handlerId}")
        @Consumes(MediaType.APPLICATION_JSON)
        Response registerQueryHandler(@PathParam("context") String context,
                        @PathParam("handlerId") String handlerId,
                        Map<String, Object> request);
}
