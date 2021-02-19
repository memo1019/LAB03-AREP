package edu.co.escuelaing.httpserver;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

public interface Handler<T> {
    public T Handle(String path, HttpRequest req, HttpResponse res);

}
