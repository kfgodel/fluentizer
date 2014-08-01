package ar.com.dgarcia.fluentizer.impl.results;

import ar.com.dgarcia.fluentizer.api.FluentizerException;
import ar.com.dgarcia.fluentizer.impl.chain.FluentChain;

import java.util.function.Supplier;

/**
 * This type represents a result for chain invocation that leads to no invokable traditional method
 * Created by kfgodel on 28/07/14.
 */
public class DeadEndResult implements Supplier<Object> {

    private FluentChain invalidChain;

    @Override
    public Object get() {
        throw new FluentizerException("There's no method to be invoked identified as : " + invalidChain);
    }

    public static DeadEndResult create(FluentChain failingChain) {
        DeadEndResult result = new DeadEndResult();
        result.invalidChain = failingChain;
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + invalidChain;
    }
}
