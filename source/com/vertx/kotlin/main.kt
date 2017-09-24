package com.vertx.kotlin


import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.core.http.HttpServerOptions

fun main(args : Array<String>)
{
    val options = VertxOptions();
    options.setEventLoopPoolSize(4);
    options.setWorkerPoolSize(16);
    val vertx = Vertx.vertx(options);
    val httpOptions = HttpServerOptions();
    httpOptions.maxHeaderSize = 16 * 1024;
    val router = buildRouter(vertx);

    println("Vertx Server Main Listening On 8082");
    val mainServer = vertx.createHttpServer(httpOptions);
    mainServer.requestHandler(router::accept).listen(8082);

  
    for(i in 0 until  options.eventLoopPoolSize) {
        println("Vertx Server $i Listening On 8082");
        val server = vertx.createHttpServer(httpOptions);
        server.requestHandler(router::accept).listen(8082);
    }
    println("Vertx Server Run ends!!!");

  
}