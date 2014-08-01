package ar.com.dgarcia.fluentizer.impl.results;

import ar.com.dgarcia.fluentizer.impl.state.FluentInvocationState;
import ar.com.dgarcia.fluentizer.impl.proxy.*;

import java.util.function.Supplier;

/**
 * This type represents the result of matching part of a method, or methods for a fluent api call
 * Created by kfgodel on 28/07/14.
 */
public class PartialMatchResult implements Supplier<Object> {


    private FluentInvocationState currentState;
    private MethodInvocation lastInvocation;

    @Override
    public Object get() {
        Class<?> expectedReturnType = getReturnTypeFromLastInvocation();
        Object nextProxy = FluentProxies.createProxyFor(expectedReturnType, FluentProxyHandler.create(currentState));
        return nextProxy;
    }

    private Class<?> getReturnTypeFromLastInvocation() {
        return lastInvocation.getMethodReturnClass();
    }

    public static PartialMatchResult create(FluentInvocationState state, MethodInvocation invocation) {
        PartialMatchResult result = new PartialMatchResult();
        result.currentState = state;
        result.lastInvocation = invocation;
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + currentState ;
    }
}
