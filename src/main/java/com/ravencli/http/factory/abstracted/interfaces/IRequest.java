package com.ravencli.http.factory.abstracted.interfaces;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public interface IRequest {
    public HttpResponse run(HttpRequest request);
}
