package com.vertx.kotlin

import io.vertx.core.Vertx
import io.vertx.ext.web.Router

fun buildRouter(vertx : Vertx) : Router
{
    val router = Router.router(vertx);
  
    router.route("/").handler(::showIndex)
   
    router.route("/block").blockingHandler(::showIndexSync,false)

    return router;
}