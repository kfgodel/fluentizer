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

        List<Type> combinedTypes = new ArrayList<>(getCollectedParameters());
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
    public boolean isCompleteFor(TraditionalMethod foundMethod) {
        boolean hasSameName = getChainedName().equals(foundMethod.getMethodName());
        boolean parameterTypes = getCollectedParameters().equals(foundMethod.getParameterTypes());
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

    @Override
    public boolean isPartialFor(TraditionalMethod currentMethod) {
        boolean isPartialMethodName = currentMethod.getMethodName().startsWith(getChainedName());
        if(!isPartialMethodName){
            return false;
        }
        return startsWithSameParameterTypes(currentMethod);
    }

    /**
     * Indicates if the given method starts with parameters equals to those of this chain
     * @param currentMethod The method to check
     * @return false if any of the parameter types of this chain are different
     */
    private boolean startsWithSameParameterTypes(TraditionalMethod currentMethod) {
        List<Type> methodTypes = currentMethod.getParameterTypes();
        List<Type> chainParameters = getCollectedParameters();
        if(chainParameters.size() > methodTypes.size()){
            return false;
        }
        for (int i = 0; i < chainParameters.size(); i++) {
            Type chainType = chainParameters.get(i);
            Type methodType = methodTypes.get(i);
            if(!chainType.equals(methodType)){
                return false;
            }
        }
        return true;
    }

    public static InvocationChain create(String chainedName, List<Object> arguments, List<Type> parameterTypes) {
        InvocationChain chain = new InvocationChain();
        chain.chainedName = chainedName;
        chain.collectedArgs = arguments;
        chain.collectedParameters = parameterTypes;
        return chain;
    }

    public List<Type> getCollectedParameters() {
        return collectedParameters;
    }

}
