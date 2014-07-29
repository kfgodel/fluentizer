package ar.com.dgarcia.fluentizer.impl.chain;

import ar.com.dgarcia.fluentizer.impl.method.TraditionalMethod;
import ar.com.dgarcia.fluentizer.impl.proxy.MethodInvocation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kfgodel on 28/07/14.
 */
public class InvocationChain implements FluentChain {

    private String chainedName;
    private List<Object> collectedArgs;
    private List<Type> collectedParameters;

    @Override
    public FluentChain chainedTo(MethodInvocation invocation) {
        String combinedName = getChainedName() + capitalized(invocation.getMethodName());

        List<Object> combinedArgs = new ArrayList<>(getCollectedArguments());
        combinedArgs.addAll(invocation.getInvocationArguments());

        List<Type> combinedTypes = new ArrayList<>(collectedParameters);
        combinedTypes.addAll(invocation.getMethodParameterTypes());

        FluentChain createdChain = InvocationChain.create(combinedName, combinedArgs, combinedTypes);
        return createdChain;
    }

    /**
     * Returns the name starting with a capitalized name
     * @param methodName The method name to capitalize
     * @return The resulting suffix for the chain
     */
    private String capitalized(String methodName) {
        return Character.toUpperCase(methodName.charAt(0)) + methodName.substring(1);
    }

    @Override
    public boolean canBeInvokedAs(TraditionalMethod foundMethod) {
        boolean hasSameName = getChainedName().equals(foundMethod.getMethodName());
        boolean parameterTypes = collectedParameters.equals(foundMethod.getParameterTypes());
        return hasSameName && parameterTypes;
    }

    @Override
    public List<Object> getCollectedArguments() {
        return collectedArgs;
    }

    @Override
    public String getChainedName() {
        return chainedName;
    }

    public static InvocationChain create(String chainedName, List<Object> arguments, List<Type> parameterTypes) {
        InvocationChain chain = new InvocationChain();
        chain.chainedName = chainedName;
        chain.collectedArgs = arguments;
        chain.collectedParameters = parameterTypes;
        return chain;
    }
}
