package com.tsi.collector;

import com.tsi.entity.Comment;
import com.tsi.entity.Document;
import com.tsi.entity.DocumentBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.*;
import java.util.stream.Collectors;

@Path("/")
public class ServiceFacadeImpl implements ServiceFacade
{
    static List<Document> documentList;

    static {
        documentList = populateDocuments();
    }

    @Path("getAllDocuments")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Document> getAllDocuments()
    {
        return documentList;
    }

    @Path("add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDocument(Document document)
    {
        document.setId(UUID.randomUUID());

        for (Comment comment: document.getComments()) {
            comment.setId(UUID.randomUUID());
            comment.setUserId(UUID.randomUUID());
        }

        documentList.add(document);
        System.out.println("Created document with id " + document.getId());

        return Response.status(201).entity(document).build();
    }

    @Path("get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getDocumentById(@PathParam("id") String id, @Context UriInfo uriInfo)
    {
       System.out.println("Fetching document with id " + id);

       Document response = documentList.stream()
                .filter(document -> id.equals(document.getId().toString()))
                .findAny()
                .orElse(null);

       if (response != null) {
            System.out.println("Found document with id " + response.getId());
       }

        return Response.status(200).entity(response).location(uriInfo.getBaseUri()).build();
    }

    @Path("update")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDocument(Document documentToUpdate)
    {
        System.out.println("Updating document with id " + documentToUpdate.getId());

        documentList = documentList.stream()
                .filter(document -> !document.getId().equals(documentToUpdate.getId()))
                .collect(Collectors.toList());

        for (Comment comment: documentToUpdate.getComments()) {
            if (comment.getId() == null) {
                comment.setId(UUID.randomUUID());
                comment.setUserId(UUID.randomUUID());
            }
        }

        documentList.add(documentToUpdate);

        System.out.println("Updated document with id " + documentToUpdate.getId());

        return Response.status(200).entity(documentToUpdate).build();
    }

    @Path("delete/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteDocument(@PathParam("id") String id, @Context UriInfo uriInfo)
    {
        System.out.println("Removing document with id " + id);

        documentList = documentList.stream()
                .filter(document -> !id.equals(document.getId().toString()))
                .collect(Collectors.toList());

        System.out.println("Removed document with id " + id);

        return Response.status(204).location(uriInfo.getBaseUri()).build();
    }

    private static List<Document> populateDocuments(){
        List<Document> documentList = new ArrayList<Document>();

        Document documentFirst = new DocumentBuilder()
                .setId(UUID.fromString("664a8fa0-312c-48ee-9863-dcb88ca0c50a"))
                .setName("First name doc")
                .setContent("First content doc")
                .setTitle("First title doc")
                .setIndexMap("index one", "index two")
                .setIndexMap("index two", "index three")
                .addComment(new Comment(UUID.fromString("f9bc64b5-03c9-415b-976b-9dd597d585e6"), UUID.fromString("32895c35-f496-4cb1-a505-7ccb4fbd1be1"), "First comment doc"))
                .addComment(new Comment(UUID.fromString("f9bc64b5-03c9-415b-976b-9dd597d585e7"), UUID.fromString("32895c35-f496-4cb1-a505-7ccb4fbd1be2"), "Second comment doc"))
                .build();

        System.out.println("Created document with id " + documentFirst.getId());

        Document documentSecond = new DocumentBuilder()
                .setId(UUID.fromString("d88c925f-a8ec-45f0-836b-0a3e6bb621d0"))
                .setName("Second name doc")
                .setContent("Second content doc")
                .setTitle("Second title doc")
                .setIndexMap("index one", "index two")
                .addComment(new Comment(UUID.fromString("ed60422f-9be9-4230-af61-95080c1871f5"), UUID.fromString("59d02fff-0c02-4ad1-8958-c0b22e6b2478"), "Second comment doc"))
                .build();

        System.out.println("Created document with id " + documentSecond.getId());

        Document documentThird = new DocumentBuilder()
                .setId(UUID.fromString("1cb38092-37dd-4475-80f0-8772a988a6ce"))
                .setName("Third name doc")
                .setContent("Third content doc")
                .setTitle("Third title doc")
                .setIndexMap("index one", "index two")
                .addComment(new Comment(UUID.fromString("7fcadc44-ad7b-4207-8d3a-973e09190b22"), UUID.fromString("0cd81f37-dbf2-49d5-8438-964304914b5e"), "Third comment doc"))
                .build();

        System.out.println("Created document with id " + documentThird.getId());

        documentList.add(documentFirst);
        documentList.add(documentSecond);
        documentList.add(documentThird);

        return documentList;
    }

}