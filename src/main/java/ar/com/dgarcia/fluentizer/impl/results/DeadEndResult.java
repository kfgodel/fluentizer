package ar.com.dgarcia.fluentizer.impl.results;

import ar.com.dgarcia.fluentizer.api.FluentizerException;
import ar.com.dgarcia.fluentizer.impl.chain.FluentChain;

import java.util.function.Supplier;

/**
 * This type represents a result for chain invocation that leads to no invokable traditional method
 * Created by kfgodel on 28/07/14.
 */
public class DeadEndResult implements Supplier<Object> {

    private Object hostInstance;
    private FluentChain invalidChain;

    @Override
    public Object get() {
        StringBuilder builder = new StringBuilder();
        builder.append("There's no method identified as: ");
        builder.append(invalidChain.getChainedName());
        builder.append("(");
        builder.append(invalidChain.getCollectedParameters());
        builder.append(") to be invoked in [");
        builder.append(hostInstance.getClass().getSimpleName());
        builder.append("]. Cannot make fluent invocation");
        throw new FluentizerException(builder.toString());
    }

    public static DeadEndResult create(FluentChain failingChain, Object hostInstance) {
        DeadEndResult result = new DeadEndResult();
        result.invalidChain = failingChain;
        result.hostInstance = hostInstance;
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + invalidChain;
    }
}
