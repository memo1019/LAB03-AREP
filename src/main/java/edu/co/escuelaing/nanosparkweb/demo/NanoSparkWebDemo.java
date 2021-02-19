package edu.co.escuelaing.nanosparkweb.demo;

import java.sql.SQLException;

import static edu.co.escuelaing.nanosparkweb.NanoSpark.*;
public class NanoSparkWebDemo {

    public static void main(String [] args ) throws SQLException {
        get("/hello",(req,res)->
                "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Title of the document</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Get from NanoSpark using lambda function</h1>\n"
                + "</body>\n"
                + "</html>\n");

        startServer();
    }
}
