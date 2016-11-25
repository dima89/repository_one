package com.tsi.collector;

import com.tsi.entity.Comment;
import com.tsi.entity.Document;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/rest")
public class Collector
{
    private static List<Document> documents;

    static{
        documents = populateDocuments();
    }

    @Path("getAllDocuments")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Document> getAllDocuments()
    {
        List<Document> documents = new ArrayList<>();
        Document doc = new Document();
        doc.setId(1);
        doc.setName("doc1");
        doc.setTitle("title");
        doc.setContent("content");
        Map<String, String> indexMap = new HashMap<String, String>()
        {{
            put("a","b");
        }};
        doc.setIndexMap(indexMap);
        Comment comment = new Comment();
        comment.setId(3);
        comment.setUserId(2);
        comment.setContent("comment content");
        doc.setComments(Collections.singletonList(comment));

        Document doc1 = new Document();
        doc1.setId(1);
        doc1.setName("doc1");
        doc1.setTitle("title");
        doc1.setContent("content");
        Map<String, String> indexMap1 = new HashMap<String, String>()
        {{
            put("a","b");
        }};
        doc1.setIndexMap(indexMap1);
        Comment comment1 = new Comment();
        comment1.setId(3);
        comment1.setUserId(2);
        comment1.setContent("comment content");
        doc1.setComments(Collections.singletonList(comment1));

        documents.add(doc);
        documents.add(doc1);
        return documents;
    }

    @Path("add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDocument(Document document)
    {
        return Response.status(201).entity(new Document()).build();
    }

    @Path("get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Document getDocumentById(@PathParam("id") String id)
    {
        return new Document();
    }

    @Path("update/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDocument(@PathParam("id") String id)
    {
        return Response.status(200).entity(new Document()).build();
    }

    @Path("delete/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDocument(@PathParam("id") String id)
    {
        return Response.status(200).entity(new Document()).build();
    }

    private static List<Document> populateDocuments(){
        List<Document> document = new ArrayList<Document>();
        //users.add(new User(counter.incrementAndGet(),"Sam",30, 70000));
        //users.add(new User(counter.incrementAndGet(),"Tom",40, 50000));
        //users.add(new User(counter.incrementAndGet(),"Jerome",45, 30000));
        //users.add(new User(counter.incrementAndGet(),"Silvia",50, 40000));
        return documents;
    }
}