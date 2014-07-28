package ar.com.dgarcia.fluentizer.impl.proxy;

import ar.com.dgarcia.fluentizer.impl.chain.FluentState;

import java.util.function.Supplier;

/**
 * This type implements the proxy handler, trying to fin the best method match for a fluent invocation
 * Created by kfgodel on 26/07/14.
 */
public class FluentProxyHandler implements ProxyHandler {

    private FluentState fluentState;

    public static FluentProxyHandler create(FluentState fluentState) {
        FluentProxyHandler proxyHandler = new FluentProxyHandler();
        proxyHandler.fluentState = fluentState;
        return proxyHandler;
    }

    @Override
    public Object proxy(MethodInvocation invocation) {
        Supplier<Object> responseSupplier = fluentState.calculateNextFor(invocation);
        return responseSupplier.get();
    }
}
