package ar.com.dgarcia.fluentizer.impl.results;

import ar.com.dgarcia.fluentizer.impl.chain.FluentChain;
import ar.com.dgarcia.fluentizer.impl.method.TraditionalMethod;

import java.util.List;
import java.util.function.Supplier;

/**
 * This type represents the result of matching method invocation which resolves the invocation chain
 * Created by kfgodel on 28/07/14.
 */
public class MethodMatchResult implements Supplier<Object> {

    private FluentChain matchingChain;
    private TraditionalMethod matchingMethod;
    private Object hostInstance;

    @Override
    public Object get() {
        List<Object> chainArgs = matchingChain.getCollectedArguments();
        return matchingMethod.invokeOn(hostInstance, chainArgs);
    }

    public static MethodMatchResult create(FluentChain matchingChain, TraditionalMethod matchingMethod, Object hostInstance) {
        MethodMatchResult result = new MethodMatchResult();
        result.hostInstance = hostInstance;
        result.matchingMethod = matchingMethod;
        result.matchingChain = matchingChain;
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + matchingMethod;
    }
}
