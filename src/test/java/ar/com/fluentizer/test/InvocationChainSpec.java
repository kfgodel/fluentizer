package ar.com.fluentizer.test;

import ar.com.dgarcia.fluentizer.impl.chain.FluentChain;
import ar.com.dgarcia.fluentizer.impl.chain.RootChain;
import ar.com.dgarcia.fluentizer.impl.method.TraditionalMethod;
import ar.com.dgarcia.fluentizer.impl.proxy.MethodInvocation;
import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.TestContext;
import com.google.common.collect.Lists;
import org.junit.runner.RunWith;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This type verifies the behavior of an invocation chain
 * Created by kfgodel on 28/07/14.
 */
@RunWith(JavaSpecRunner.class)
public class InvocationChainSpec extends JavaSpec<InvocationChainSpec.InvocationChainSpecContext> {

    public static interface InvocationChainSpecContext extends TestContext {
        void rootChain(Supplier<FluentChain> definition);
        FluentChain rootChain();

        void mockedInvocation(Supplier<MethodInvocation> definition);
        MethodInvocation mockedInvocation();

        void doubleChain(Supplier<FluentChain> definition);
        FluentChain doubleChain();

        void invokableMethod(Supplier<TraditionalMethod> definition);
        TraditionalMethod invokableMethod();
    }

    @Override
    public void define() {

        context().rootChain(()-> RootChain.create());
        context().mockedInvocation(() -> {
            MethodInvocation mocked = mock(MethodInvocation.class);
            when(mocked.getMethodName()).thenReturn("aMethod");
            when(mocked.getInvocationArguments()).thenReturn(Lists.newArrayList("1", "2"));
            when(mocked.getMethodParameterTypes()).thenReturn(Lists.newArrayList(String.class, String.class));
            return mocked;
        });

        describe("root chain", () -> {
            it("is not complete for any method", () -> {
                assertThat(context().rootChain().isCompleteFor(null)).isFalse();
            });
            it("is partial for all", () -> {
                assertThat(context().rootChain().isPartialFor(null)).isTrue();
            });
            it("has empty name", () -> {
                assertThat(context().rootChain().getChainedName()).isEmpty();
            });
            it("has no args", () -> {
                assertThat(context().rootChain().getCollectedArguments()).isEmpty();
            });
            it("starts a chain", () -> {
                FluentChain startedChain = context().rootChain().chainedTo(context().mockedInvocation());
                assertThat(startedChain.getChainedName()).isEqualTo("aMethod");
            });
        });

        describe("started chain", ()->{

            context().doubleChain(()-> context().rootChain()
                    .chainedTo(context().mockedInvocation())
                    .chainedTo(context().mockedInvocation()));

            context().invokableMethod(()->{
                TraditionalMethod mockedTraditional = mock(TraditionalMethod.class);
                when(mockedTraditional.getMethodName()).thenReturn("aMethodAMethod");
                when(mockedTraditional.getParameterTypes()).thenReturn(Lists.newArrayList(String.class, String.class, String.class, String.class));
                return mockedTraditional;
            });

            it("is partial for the invokable method", ()->{
                FluentChain partialChain = context().rootChain().chainedTo(context().mockedInvocation());
                assertThat(partialChain.isPartialFor(context().invokableMethod())).isTrue();
            });
            it("is completed matching method by name and arg", ()->{
                assertThat(context().doubleChain().isCompleteFor(context().invokableMethod())).isTrue();
            });

            it("concatenates invoked method names with capital letter", ()->{
                assertThat(context().doubleChain().getChainedName()).isEqualTo("aMethodAMethod");
            });
            it("collects invocation args in a list", ()->{
                assertThat(context().doubleChain().getCollectedArguments()).isEqualTo(Lists.newArrayList("1","2","1","2"));
            });

        });

    }
}
