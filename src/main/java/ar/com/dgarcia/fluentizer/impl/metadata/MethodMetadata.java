package ar.com.dgarcia.fluentizer.impl.metadata;

import ar.com.dgarcia.fluentizer.api.FluentizerException;
import ar.com.dgarcia.fluentizer.impl.method.JavaMethod;
import ar.com.dgarcia.fluentizer.impl.method.TraditionalMethod;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * This type serves as a singleton access to class methods metadata
 * Created by kfgodel on 28/07/14.
 */
public class MethodMetadata {

    private static WeakHashMap<Class<?>, Set<TraditionalMethod>> apiMethods = new WeakHashMap<>();

    /**
     * Recovers all the methods from the given instance class
     * @param realApiHandler The instance to iterate reflectively to get the methods
     * @return The cached list or a new one
     */
    public static Set<TraditionalMethod> getApiMethodsFrom(Object realApiHandler) {
        if(realApiHandler == null){
            throw new FluentizerException("There's no methods for null");
        }
        Class<?> apiClass = realApiHandler.getClass();
        return getApiMethodsFor(apiClass);
    }

    /**
     * Recovers all public methods (including inherited).<br> Uses cached list if existing
     * @param apiClass The class to get methods from
     * @return The methods list
     */
    private static Set<TraditionalMethod> getApiMethodsFor(Class<?> apiClass) {
        if(apiMethods.containsKey(apiClass)){
            return apiMethods.get(apiClass);
        }
        Set<TraditionalMethod> foundMethods = extractMethodsFrom(apiClass);
        apiMethods.put(apiClass, foundMethods);
        return foundMethods;
    }

    /**
     * Extracts public methods from the given class
     * @param apiClass The class to extract methods own and inherited
     * @return The list of methods
     */
    private static Set<TraditionalMethod> extractMethodsFrom(Class<?> apiClass) {
        Set<TraditionalMethod> foundMethods = new LinkedHashSet<>();
        Class<?> currentType = apiClass;
        while (currentType != null){
            Method[] declaredMethods = currentType.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                if(Modifier.isPublic(declaredMethod.getModifiers())){
                    TraditionalMethod apiMethod = JavaMethod.create(declaredMethod);
                    foundMethods.add(apiMethod);
                }
            }
            currentType = currentType.getSuperclass();
        }
        return foundMethods;
    }
}
