package ar.com.dgarcia.fluentizer.impl.method;

import ar.com.dgarcia.fluentizer.api.FluentizerException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * This type represents a traditional java method
 * Created by kfgodel on 28/07/14.
 */
public class JavaMethod implements TraditionalMethod {

    private Method primitiveMethod;

    @Override
    public Object invokeOn(Object hostInstance, List<Object> chainArgs){
        try {
            return primitiveMethod.invoke(hostInstance, chainArgs.toArray());
        } catch (IllegalAccessException e) {
            throw new FluentizerException("Illegal access invoking method["+primitiveMethod+"] ",e);
        } catch (InvocationTargetException e) {
            Throwable methodException = e.getCause();
            throw new FluentizerException("Error inside method["+primitiveMethod+"]: " + methodException.getMessage(), methodException);
        }
    }

    @Override
    public String getMethodName() {
        return primitiveMethod.getName();
    }

    @Override
    public Type getReturnType() {
        return primitiveMethod.getGenericReturnType();
    }

    @Override
    public List<Type> getParameterTypes() {
        Type[] genericParameterTypes = primitiveMethod.getGenericParameterTypes();
        List<Type> argTypes = new ArrayList<>(genericParameterTypes.length);
        for (Type genericParameterType : genericParameterTypes) {
            argTypes.add(genericParameterType);
        }
        return argTypes;
    }

    public static JavaMethod create(Method method) {
        JavaMethod javaMethod = new JavaMethod();
        javaMethod.primitiveMethod = method;
        return javaMethod;
    }

    @Override
    public int hashCode() {
        List<Object> identityElements = getIdentityElementsFor(this);
        return identityElements.hashCode();
    }

    /**
     * Returns the list of objects that form the identity of the method
     * @param method The method to describe
     * @return A list of elements that conform the identity of the method
     */
    private List<Object> getIdentityElementsFor(TraditionalMethod method) {
        List<Object> identityElements = new ArrayList<>();
        identityElements.add(method.getReturnType());
        identityElements.add(method.getMethodName());
        identityElements.addAll(method.getParameterTypes());
        return identityElements;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  TraditionalMethod)){
            return false;
        }
        List<Object> thisIdentityElements = getIdentityElementsFor(this);
        List<Object> thatIdentityElements = getIdentityElementsFor((TraditionalMethod) obj);
        boolean areEqualLists = thisIdentityElements.equals(thatIdentityElements);
        return areEqualLists;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + primitiveMethod.toString();
    }
}
