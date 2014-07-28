package ar.com.dgarcia.fluentizer.impl.proxy;

/**
 * This type is a factory to create fluent proxies. Delegates to Java proxies
 * Created by kfgodel on 28/07/14.
 */
public class FluentProxies {

    /**
     * Creates a fluent proxy for the given handler and expected type
     * @param fluentApiClass The interface type to be proxied
     * @param handler THe proxy method handler
     * @param <T> The expected instance type
     * @return Created proxy instance
     */
    public static <T> T createProxyFor(Class<T> fluentApiClass, ProxyHandler handler) {
        JavaProxyHandlerAdapter javaHandler = JavaProxyHandlerAdapter.create(handler);
        T javaProxy = JavaProxies.createProxyOf(fluentApiClass, javaHandler);
        return javaProxy;
    }

}
