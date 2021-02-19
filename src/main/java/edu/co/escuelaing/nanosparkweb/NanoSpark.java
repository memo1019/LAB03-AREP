package edu.co.escuelaing.nanosparkweb;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class NanoSpark {

    public static void get(String path, BiFunction<HttpRequest, HttpResponse,String> bifunction){
        NanoSparkServer nanosvr = NanoSparkServer.getInstance();
        nanosvr.get(path,bifunction);
    }
    public static void startServer(){
        NanoSparkServer nanosvr =NanoSparkServer.getInstance();
        nanosvr.startServer();

    }
}
