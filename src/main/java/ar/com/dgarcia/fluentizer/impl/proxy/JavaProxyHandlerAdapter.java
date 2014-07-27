package ar.com.dgarcia.fluentizer.impl.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * This type converts java proxy to fluent proxy invocations
 * Created by kfgodel on 26/07/14.
 */
public class JavaProxyHandlerAdapter implements InvocationHandler {

    private ProxyHandler proxyHandler;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        JavaProxyInvocation invocation = JavaProxyInvocation.create(method, args);
        return proxyHandler.proxy(invocation);
    }

    public static JavaProxyHandlerAdapter create(ProxyHandler proxyHandler) {
        JavaProxyHandlerAdapter adapter = new JavaProxyHandlerAdapter();
        adapter.proxyHandler = proxyHandler;
        return adapter;
    }
}
