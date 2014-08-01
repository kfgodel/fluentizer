package ar.com.dgarcia.fluentizer.impl.state;

import ar.com.dgarcia.fluentizer.impl.proxy.MethodInvocation;

import java.util.function.Supplier;

/**
 * This type represents a method invocation chain, which collects invoked methods as one sequence
 * Created by kfgodel on 28/07/14.
 */
public interface FluentState {
    /**
     * Calculates the next fluent state and returns the supplier for the result of the invocation
     * @param invocation The new invocation that generates a new state
     * @return The supplier of the invocation result
     */
    Supplier<Object> calculateNextFor(MethodInvocation invocation);
}
