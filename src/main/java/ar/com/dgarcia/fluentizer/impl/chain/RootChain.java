package ar.com.dgarcia.fluentizer.impl.chain;

import ar.com.dgarcia.fluentizer.impl.method.TraditionalMethod;
import ar.com.dgarcia.fluentizer.impl.proxy.MethodInvocation;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * This type represents the starting chain for a fluent invocation chain
 * Created by kfgodel on 28/07/14.
 */
public class RootChain implements FluentChain {
    @Override
    public FluentChain chainedTo(MethodInvocation invocation) {
        String startingName = invocation.getMethodName();
        List<Object> startingArgs = invocation.getInvocationArguments();
        List<Type> startingTypes = invocation.getMethodParameterTypes();
        return InvocationChain.create(startingName, startingArgs, startingTypes);
    }

    @Override
    public boolean canBeInvokedAs(TraditionalMethod foundMethod) {
        return false;
    }

    @Override
    public List<Object> getCollectedArguments() {
        return Collections.emptyList();
    }

    @Override
    public String getChainedName() {
        return "";
    }

    public static RootChain create() {
        RootChain rootChain = new RootChain();
        return rootChain;
    }
}
