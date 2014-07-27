package ar.com.dgarcia.fluentizer.impl.proxy;

import java.lang.reflect.Method;

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

}
