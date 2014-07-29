package ar.com.dgarcia.fluentizer.impl.chain;

import ar.com.dgarcia.fluentizer.impl.method.TraditionalMethod;
import ar.com.dgarcia.fluentizer.impl.proxy.MethodInvocation;
import ar.com.dgarcia.fluentizer.impl.results.DeadEndResult;
import ar.com.dgarcia.fluentizer.impl.results.MethodMatchResult;
import ar.com.dgarcia.fluentizer.impl.results.PartialMatchResult;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by kfgodel on 28/07/14.
 */
public class FluentInvocationState implements FluentState {

    private Object hostInstance;
    private FluentChain currentChain;
    private Set<TraditionalMethod> currentMethods;

    @Override
    public Supplier<Object> calculateNextFor(MethodInvocation invocation) {
        FluentChain nextChain = currentChain.chainedTo(invocation);
        Set<TraditionalMethod> matchedMethods = filterMethodsStartingWith(nextChain);
        if(matchedMethods.isEmpty()){
           // We cannot satisfy the invocation
           return DeadEndResult.create(nextChain);
        }
        if(matchedMethods.size() == 1){
            TraditionalMethod foundMethod = matchedMethods.stream().findFirst().get();
            if(nextChain.isCompleteFor(foundMethod)){
                return MethodMatchResult.create(nextChain, foundMethod, hostInstance);
            }
        }
        FluentInvocationState nextState = FluentInvocationState.create(nextChain, matchedMethods, hostInstance);
        return PartialMatchResult.create(nextState, invocation);
    }

    private Set<TraditionalMethod> filterMethodsStartingWith(FluentChain nextChain) {
        Set<TraditionalMethod> filteredMethods = currentMethods.stream()
                .filter(currentMethod -> nextChain.isPartialFor(currentMethod))
                .collect(Collectors.toCollection(() -> new LinkedHashSet<>()));
        return filteredMethods;
    }

    public static FluentInvocationState create(FluentChain chain, Set<TraditionalMethod> methods, Object hostInstance) {
        FluentInvocationState state = new FluentInvocationState();
        state.currentChain = chain;
        state.currentMethods = methods;
        state.hostInstance = hostInstance;
        return state;
    }
}
