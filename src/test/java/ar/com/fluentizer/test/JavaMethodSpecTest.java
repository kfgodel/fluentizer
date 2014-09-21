package ar.com.fluentizer.test;

import ar.com.dgarcia.fluentizer.api.FluentizerException;
import ar.com.dgarcia.fluentizer.impl.method.JavaMethod;
import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.TestContext;
import ar.com.dgarcia.javaspec.api.Variable;
import ar.com.fluentizer.test.testInterfaces.FluentizerTestContext;
import org.junit.runner.RunWith;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

/**
 * This type verifies the behavior of JavaMethod
 * Created by kfgodel on 21/09/14.
 */
@RunWith(JavaSpecRunner.class)
public class JavaMethodSpecTest extends JavaSpec<FluentizerTestContext> {
    @Override
    public void define() {
        beforeEach(()->{
            context().method(()-> createTestMethod());
        });

        it("has a name", ()->{
            assertThat(context().method().getMethodName())
                    .isEqualTo("toString");
        });
        it("has a return type", ()->{
            assertThat(context().method().getReturnType())
                    .isEqualTo(String.class);
        });
        it("has parameter types", ()->{
            assertThat(context().method().getParameterTypes())
                    .isEqualTo(Collections.emptyList());
        });
        it("can be invoked on an instance", ()->{
            assertThat(context().method().invokeOn("Hola", Collections.emptyList()))
                    .isEqualTo("Hola");
        });
        it("re throws original exception on invocation", ()->{

            try {
                context().method().invokeOn(new ExplodingToString(), Collections.emptyList());
                failBecauseExceptionWasNotThrown(FluentizerException.class);
            } catch (Exception e) {
                assertThat(e).hasMessage("Error inside method[public java.lang.String java.lang.Object.toString()]: Boom!");
            }
        });
    }

    private JavaMethod createTestMethod() {
        try {
            Method primitiveToString = Object.class.getMethod("toString");
            return JavaMethod.create(primitiveToString);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Can't access toString?!", e);
        }
    }

    public static class ExplodingToString {
        @Override
        public String toString() {
            throw new RuntimeException("Boom!");
        }
    }
}
