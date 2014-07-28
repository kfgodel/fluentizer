package ar.com.dgarcia.fluentizer.impl.method;

import java.lang.reflect.Type;
import java.util.List;

/**
 * This type represents a traditional method that can be invoked in traditional api
 * Created by kfgodel on 28/07/14.
 */
public interface TraditionalMethod {
    /**
     * Invokes this method in the given instance with the passed arguments
     * @param hostInstance The instance to use as this
     * @param chainArgs The arguments of the invocation
     * @return The result of the invocation
     */
    Object invokeOn(Object hostInstance, List<Object> chainArgs);

    /**
     * Returns the method name
     */
    String getMethodName();

    /**
     * Returns the method return type (with generics info)
     * @return The method generic return type
     */
    Type getReturnType();

    /**
     * Returns the method parameter types (with generics info)
     * @return The method args type
     */
    List<Type> getParameterTypes();
}
