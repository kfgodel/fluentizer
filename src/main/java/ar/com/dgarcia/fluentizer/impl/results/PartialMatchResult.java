package ar.com.dgarcia.fluentizer.impl.results;

import ar.com.dgarcia.fluentizer.impl.chain.FluentInvocationState;
import ar.com.dgarcia.fluentizer.impl.proxy.*;

import java.util.function.Supplier;

/**
 * This type represents the result of matching part of a method, or methods for a fluent api call
 * Created by kfgodel on 28/07/14.
 */
public class PartialMatchResult implements Supplier<Object> {


    private FluentInvocationState nextState;
    private MethodInvocation lastInvocation;

    @Override
    public Object get() {
        Class<?> expectedReturnType = getReturnTypeFromLastInvocation();
        Object nextProxy = FluentProxies.createProxyFor(expectedReturnType, FluentProxyHandler.create(nextState));
        return nextProxy;
    }

    private Class<?> getReturnTypeFromLastInvocation() {
        return null;
    }

    public static PartialMatchResult create(FluentInvocationState nextState, MethodInvocation invocation) {
        PartialMatchResult result = new PartialMatchResult();
        result.nextState = nextState;
        result.lastInvocation = invocation;
        return result;
    }
}
