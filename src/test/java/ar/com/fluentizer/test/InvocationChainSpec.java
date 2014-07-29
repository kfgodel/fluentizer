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
    }

    @Override
    public void define() {

        context().rootChain(()-> RootChain.create());
        context().mockedInvocation(()-> {
            MethodInvocation mocked = mock(MethodInvocation.class);
            when(mocked.getMethodName()).thenReturn("aMethod");
            when(mocked.getInvocationArguments()).thenReturn(Lists.newArrayList("1", "2"));
            when(mocked.getMethodParameterTypes()).thenReturn(Lists.newArrayList(String.class, String.class));
            return mocked;
        });

        describe("root chain", () -> {
            it("cannot be invoked", () -> {
                assertThat(context().rootChain().canBeInvokedAs(null)).isFalse();
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

            it("concatenates invoked method names with capital letter", ()->{
                assertThat(context().doubleChain().getChainedName()).isEqualTo("aMethodAMethod");
            });
            it("collects invocation args in a list", ()->{
                assertThat(context().doubleChain().getCollectedArguments()).isEqualTo(Lists.newArrayList("1","2","1","2"));
            });
            it("can detect a feasible invocation by name and arg", ()->{
                TraditionalMethod mockedTraditional = mock(TraditionalMethod.class);
                when(mockedTraditional.getMethodName()).thenReturn("aMethodAMethod");
                when(mockedTraditional.getParameterTypes()).thenReturn(Lists.newArrayList(String.class, String.class, String.class, String.class));
                assertThat(context().doubleChain().canBeInvokedAs(mockedTraditional)).isTrue();
            });

        });

    }
}
