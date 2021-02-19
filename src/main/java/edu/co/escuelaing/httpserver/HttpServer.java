package edu.co.escuelaing.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpServer {
    private Map<String, Handler<String>> Handlers =new HashMap();

    public HttpServer(){
        super();
    }
    public void registerHandler(Handler<String> h , String prefix){
        Handlers.put(prefix,h);
        System.out.println("Adding handler with key: "+prefix);
    }

    public void startServer() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            boolean pathRead=false;
            String path ="";
            while ((inputLine = in.readLine()) != null) {
                if(!pathRead){
                    path=inputLine.split(" ")[1];
                    System.out.println("Path read : "+path);
                    pathRead=true;
                }
                System.out.println("Recibí: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }
            String prefix= "/Apps";
            String sufix= "/hello";
            if(Handlers.containsKey(prefix)){
                System.out.println("Using handler for: "+prefix);
                out.println(getDefaultOkOutputHeader()+Handlers.get(prefix).Handle(sufix,null,null));

            }else{
                out.println(getDefaultOkOutput());

            }
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    private String getDefaultOkOutputHeader(){
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n";

    }
    private String getDefaultOkOutput(){
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Title of the document</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Mi propio mensaje</h1>\n"
                + "</body>\n"
                + "</html>\n" ;

    }
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000; //returns default port if heroku-port isn't set(i.e. on localhost);
    }
}



