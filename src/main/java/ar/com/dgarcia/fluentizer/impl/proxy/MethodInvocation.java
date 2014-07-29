package ar.com.dgarcia.fluentizer.impl.proxy;

import java.lang.reflect.Type;
import java.util.List;

/**
 * This type represents a method invocation with arguments
 * Created by kfgodel on 26/07/14.
 */
public interface MethodInvocation {

    /**
     * The name of the invoked method
     */
    String getMethodName();

    /**
     * The type list for each method parameter
     */
    List<Type> getMethodParameterTypes();

    /**
     * The list of object used as invocation arguments
     * @return
     */
    List<Object> getInvocationArguments();

    /**
     * Returns the class that represents the return type for the invoked method
     * @return The expected type
     */
    Class<?> getMethodReturnClass();
}
