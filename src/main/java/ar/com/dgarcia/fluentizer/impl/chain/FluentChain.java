package ar.com.dgarcia.fluentizer.impl.chain;

import ar.com.dgarcia.fluentizer.impl.method.TraditionalMethod;
import ar.com.dgarcia.fluentizer.impl.proxy.MethodInvocation;

import java.util.List;

/**
 * This type represents a sequence of fluent invocation that chains to form a traditional invocation
 * Created by kfgodel on 28/07/14.
 */
public interface FluentChain {
    /**
     * Calculates the resulting chain of adding the given method invocation to this chain
     * @param invocation The added invocation
     * @return The resulting chain
     */
    FluentChain chainedTo(MethodInvocation invocation);

    /**
     * Indicates if the given method matches name and parameters of this chain
     * @param foundMethod The method to be invoked as this chain
     * @return true if the args of this cain can be passed to the method invocation
     */
    boolean canBeInvokedAs(TraditionalMethod foundMethod);

    /**
     * Returns the arguments collected in the chain
     * @return The list of arguments in the order they were collected
     */
    List<Object> getCollectedArguments();

    /**
     * Returns the chained names of the invocations
     * @return The name of all invocations as one method
     */
    String getChainedName();
}
