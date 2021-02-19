package edu.co.escuelaing.nanosparkweb;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import edu.co.escuelaing.httpserver.Handler;
import edu.co.escuelaing.httpserver.HttpServer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NanoSparkServer implements Handler<String> {

    private static NanoSparkServer _theinstance =new NanoSparkServer();
    private HttpServer hserver= new HttpServer();


    public static NanoSparkServer getInstance() {
        return _theinstance;
    }
    private Map<String, BiFunction<HttpRequest, HttpResponse,String>> bodyMap=new HashMap();

    private NanoSparkServer(){
        hserver.registerHandler(this,"/Apps");

    }

    public void startServer() {
        try{
            hserver.startServer();
        } catch (IOException ex) {
            Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void get(String path, BiFunction<HttpRequest, HttpResponse,String> bifunction) {
        bodyMap.put(path,bifunction);
    }
    public String getValue(String path,HttpRequest req,HttpResponse res){
        return bodyMap.get(path).apply(req,res);
    }

    @Override
    public String Handle(String path,HttpRequest req,HttpResponse res) {
        return getValue(path,req,res);
    }
}
