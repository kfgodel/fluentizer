package ar.com.dgarcia.fluentizer.impl.proxy;

/**
 * This type implements the proxy handler, trying to fin the best method match for a fluent invocation
 * Created by kfgodel on 26/07/14.
 */
public class FluentProxyHandler implements ProxyHandler {

    public static FluentProxyHandler create() {
        FluentProxyHandler proxyHandler = new FluentProxyHandler();
        return proxyHandler;
    }

    @Override
    public Object proxy(MethodInvocation invocation) {
        return null;
    }
}
