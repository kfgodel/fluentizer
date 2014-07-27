package ar.com.dgarcia.fluentizer.impl.proxy;

import ar.com.dgarcia.fluentizer.api.FluentizerException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * This type serves as a factory of proxy instances using java proxies
 * Created by kfgodel on 26/07/14.
 */
public class JavaProxies {

    /**
     * Creates a new proxy instance of given type
     * @param proxyType The interface type class
     * @param methodHandler The method handler for proxy invocations
     * @param <P> The expected instance type
     * @return The created proxy instance
     */
    public static <P> P createProxyOf(Class<P> proxyType, InvocationHandler methodHandler) throws FluentizerException {
        try {
            P proxyInstance = (P) Proxy.newProxyInstance(proxyType.getClassLoader(),
                    new Class<?>[]{proxyType},
                    methodHandler);
            return proxyInstance;
        } catch (IllegalArgumentException e) {
            throw new FluentizerException("Cannot create instance. Probably type is not an interface: " + proxyType,e);
        }
    }
}
