package ar.com.dgarcia.fluentizer.impl.chain;

import ar.com.dgarcia.fluentizer.impl.method.TraditionalMethod;
import ar.com.dgarcia.fluentizer.impl.proxy.MethodInvocation;

import java.util.List;

/**
 * This type represents the starting chain for a fluent invocation chain
 * Created by kfgodel on 28/07/14.
 */
public class RootChain implements FluentChain {
    @Override
    public FluentChain chainedTo(MethodInvocation invocation) {
        return null;
    }

    @Override
    public boolean canBeInvokedAs(TraditionalMethod foundMethod) {
        return false;
    }

    @Override
    public List<Object> getCollectedArguments() {
        return null;
    }

    public static RootChain create() {
        RootChain rootChain = new RootChain();
        return rootChain;
    }
}
