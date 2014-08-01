package ar.com.dgarcia.fluentizer.impl.proxy;

import com.google.common.collect.Lists;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
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
        return method.getName();
    }

    @Override
    public List<Type> getMethodParameterTypes() {
        return Lists.newArrayList(method.getGenericParameterTypes());
    }

    @Override
    public List<Object> getInvocationArguments() {
        if(args == null){
            return Collections.emptyList();
        }
        return Lists.newArrayList(args);
    }

    @Override
    public Class<?> getMethodReturnClass() {
        return method.getReturnType();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(getClass().getSimpleName());
        builder.append("{ methods: ");
        builder.append(this.method);
        builder.append(", args: ");
        builder.append(Arrays.toString(this.args));
        builder.append("}");
        return builder.toString();
    }
}
