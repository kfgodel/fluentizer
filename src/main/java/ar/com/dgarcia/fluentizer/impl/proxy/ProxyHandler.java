package ar.com.dgarcia.fluentizer.impl.proxy;

/**
 * This type represents an method invocation handler for object proxies
 * Created by kfgodel on 26/07/14.
 */
public interface ProxyHandler {

    /**
     * Handles the method invocation returning the expected return
     * @param invocation The original invocation
     * @return The expected method return for the invocation
     */
    Object proxy(MethodInvocation invocation);
}
