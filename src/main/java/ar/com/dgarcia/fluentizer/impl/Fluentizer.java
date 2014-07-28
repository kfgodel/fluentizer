package ar.com.dgarcia.fluentizer.impl;

import ar.com.dgarcia.fluentizer.api.FluentizerApi;
import ar.com.dgarcia.fluentizer.impl.chain.FluentInvocationState;
import ar.com.dgarcia.fluentizer.impl.chain.RootChain;
import ar.com.dgarcia.fluentizer.impl.metadata.MethodMetadata;
import ar.com.dgarcia.fluentizer.impl.method.TraditionalMethod;
import ar.com.dgarcia.fluentizer.impl.proxy.FluentProxies;
import ar.com.dgarcia.fluentizer.impl.proxy.FluentProxyHandler;
import ar.com.dgarcia.fluentizer.impl.proxy.ProxyHandler;

import java.util.Set;

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
        T fluentApi = FluentProxies.createProxyFor(fluentApiClass, handler);
        return fluentApi;
    }

    private ProxyHandler createFluentHandlerFor(Object realApiHandler) {
        Set<TraditionalMethod> allApiMethods = MethodMetadata.getApiMethodsFrom(realApiHandler);
        FluentInvocationState startingState = FluentInvocationState.create(RootChain.create(), allApiMethods, realApiHandler);
        return FluentProxyHandler.create(startingState);
    }
}
