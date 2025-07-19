package com.univaq.swa.soccorsoweb.rest;

import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;

public class RESTclient {
    private static final String base = "http://localhost:8080/soccorsowebswa/rest";

    private static final String dummyHelpRequest = "{";
    private static final String dummyOperator = "";
    private static final String dummyCredentials = "";
    private static final String dummyMission = "";

    CloseableHttpClient client = HttpClients.createDefault();

    private void logRequest(ClassicHttpRequest request) {
        try {
            System.out.println("* Metodo: " + request.getMethod());
            System.out.println("* URL: " + request.getRequestUri());
            if(request.getFirstHeader("Accept") != null) {
                System.out.println("* " + request.getFirstHeader("Accept"));
            }
            System.out.println("* Headers: ");
            Header[] request_headers = request.getHeaders();
            for (Header header : request_headers) {
                System.out.println("** " + header.getName() + " = " + header.getValue());
            }
            switch (request.getMethod()) {
                case "POST": {
                    HttpEntity e = ((HttpPost) request).getEntity();
                    System.out.print("* Payload: ");
                    e.writeTo(System.out);
                    System.out.println();
                    System.out.println("* Tipo payload: " + e.getContentType());
                    break;
                }
                case "PUT": {
                    HttpEntity e = ((HttpPut) request).getEntity();
                    System.out.print("* Payload: ");
                    e.writeTo(System.out);
                    System.out.println();
                    System.out.println("* Tipo payload: " + e.getContentType());
                    break;
                }
                case "PATCH": {
                    HttpEntity e = ((HttpPatch) request).getEntity();
                    System.out.print("* Payload: ");
                    e.writeTo(System.out);
                    System.out.println();
                    System.out.println("* Tipo payload: " + e.getContentType());
                    break;
                }
                default:
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void logResponse(ClassicHttpResponse response) {
        System.out.println("* Headers: ");
        Header[] response_headers = response.getHeaders();
        for (Header header : response_headers) {
            System.out.println("** " + header.getName() + " = " + header.getValue());
        }
        System.out.println("* Return status: " + response.getReasonPhrase() + " (" + response.getCode() + ")");
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            try {
                entity.writeTo(System.out);
                System.out.println();
            } catch (IOException ex) {
                System.out.println("Cannot dump response: " + ex.getMessage());
            }
        }
    }

    private void executeAndDump(String description, ClassicHttpRequest request) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(description);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("REQUEST: ");
        logRequest(request);
        try {
            client.execute(request, response -> {
                //preleviamo il contenuto della risposta
                System.out.println("RESPONSE: ");
                logResponse(response);
                return null;
            });
        } catch (IOException ex) {
            System.out.println("Cannot execute request: " + ex.getMessage());
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();
    }

    public void doTests() throws IOException {
        HttpGet getRequest = new HttpGet(base + "/requests/closed");
        getRequest.setHeader("Accept", "application/json");
        executeAndDump("Lista richieste chiuse: ", getRequest);

        getRequest = new HttpGet(base + "/requests/7");
        getRequest.setHeader("Accept", "application/json");
        executeAndDump("Richiesta id = 7: ", getRequest);

        getRequest = new HttpGet(base + "/requests/7/mission");
        getRequest.setHeader("Accept", "application/json");
        executeAndDump("Missione richiesta id = 7: ", getRequest);

        getRequest = new HttpGet(base + "/operators/6");
        getRequest.setHeader("Accept", "application/json");
        executeAndDump("Operatore id = 6: ", getRequest);

        getRequest = new HttpGet(base + "/operators/free");
        getRequest.setHeader("Accept", "application/json");
        executeAndDump("Operatori liberi: ", getRequest);

        getRequest = new HttpGet(base + "/operators/6/missions");
        getRequest.setHeader("Accept", "application/json");
        executeAndDump("Missioni operatore id = 6: ", getRequest);

        HttpPost postRequest = new HttpPost(base + "/auth/login");
        postRequest.setEntity(new StringEntity(dummyCredentials, ContentType.APPLICATION_JSON));
        executeAndDump("Login: ", postRequest);

        Header h = client.execute(postRequest, response -> {
            return response.getFirstHeader("Authorization");
        });

        postRequest = new HttpPost(base + "/requests");
        postRequest.setEntity(new StringEntity(dummyHelpRequest, ContentType.APPLICATION_JSON));
        executeAndDump("Aggiunta richiesta", postRequest);

        Header rid = client.execute(postRequest, response -> {
            return response.getFirstHeader("HelpRequestId");
        });

        postRequest = new HttpPost(base + "/operators");
        postRequest.setEntity(new StringEntity(dummyOperator, ContentType.APPLICATION_JSON));
        postRequest.setHeader("Authorization", h.getValue());
        executeAndDump("Aggiunta operatore", postRequest);

        Header oid = client.execute(postRequest, response -> {
            return response.getFirstHeader("OperatorId");
        });

        HttpPatch patchRequest = new HttpPatch(base + "/requests/7/validate");
        patchRequest.setHeader("Authorization", h.getValue());
        executeAndDump("Validazione richiesta", patchRequest);

        patchRequest = new HttpPatch(base + "/requests/7/mission");
        patchRequest.setEntity(new StringEntity(dummyMission, ContentType.APPLICATION_JSON));
        patchRequest.setHeader("Authorization", h.getValue());
        executeAndDump("Aggiunta missione a richiesta", patchRequest);

        patchRequest = new HttpPatch(base + "/requests/7/close");
        patchRequest.setHeader("authorization", h.getValue());
        executeAndDump("Chiusura missione", patchRequest);

        HttpDelete deleteRequest = new HttpDelete(base + "/requests/7");
        deleteRequest.setHeader("Authorization", h.getValue());
        executeAndDump("Eliminazione richiesta", deleteRequest);

        deleteRequest = new HttpDelete(base + "/auth/logout");
        executeAndDump("Logout", deleteRequest);
    }

    public static void Main(String[] args) throws IOException{
        RESTclient resTclient = new RESTclient();
        resTclient.doTests();
    }
}