package ar.com.dgarcia.fluentizer.impl;

import ar.com.dgarcia.fluentizer.api.FluentizerApi;
import ar.com.dgarcia.fluentizer.impl.proxy.FluentProxyHandler;
import ar.com.dgarcia.fluentizer.impl.proxy.JavaProxies;
import ar.com.dgarcia.fluentizer.impl.proxy.JavaProxyHandlerAdapter;
import ar.com.dgarcia.fluentizer.impl.proxy.ProxyHandler;

/**
 * This type is the implementation of fluentizer
 * Created by kfgodel on 23/07/14.
 */
public class Fluentizer implements FluentizerApi {

    public static Fluentizer create() {
        Fluentizer fluentizer = new Fluentizer();
        return fluentizer;
    }

    @Override
    public <T> T expressAs(Class<T> fluentApiClass, Object realApiHandler) {
        ProxyHandler handler = createFluentHandlerFor(realApiHandler);
        T fluentApi = createProxyFor(fluentApiClass, handler);
        return fluentApi;
    }

    private <T> T createProxyFor(Class<T> fluentApiClass, ProxyHandler handler) {
        JavaProxyHandlerAdapter javaHandler = JavaProxyHandlerAdapter.create(handler);
        T javaProxy = JavaProxies.createProxyOf(fluentApiClass, javaHandler);
        return javaProxy;
    }

    private ProxyHandler createFluentHandlerFor(Object realApiHandler) {
        return FluentProxyHandler.create();
    }
}
