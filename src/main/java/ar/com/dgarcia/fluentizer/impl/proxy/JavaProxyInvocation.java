package ar.com.dgarcia.fluentizer.impl.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

/**
 * This type represents a method invocation generated from a java proxy
 * Created by kfgodel on 26/07/14.
 */
public class JavaProxyInvocation implements MethodInvocation {

    private Method method;
    private Object[] args;

    public static JavaProxyInvocation create(Method method, Object[] args) {
        JavaProxyInvocation invocation = new JavaProxyInvocation();
        invocation.args = args;
        invocation.method = method;
        return invocation;
    }

    @Override
    public String getMethodName() {
        return null;
    }

    @Override
    public List<Type> getMethodParameterTypes() {
        return null;
    }

    @Override
    public List<Object> getInvocationArguments() {
        return null;
    }
}
