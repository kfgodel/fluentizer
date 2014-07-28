package ar.com.fluentizer.test;

import ar.com.dgarcia.fluentizer.impl.metadata.MethodMetadata;
import ar.com.dgarcia.fluentizer.impl.method.TraditionalMethod;
import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.TestContext;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type is the spec for method metadata expected behavior
 * Created by kfgodel on 28/07/14.
 */
@RunWith(JavaSpecRunner.class)
public class MethodMetadataSpec extends JavaSpec<TestContext> {

    public static class TestingClass{
        public void anApiMethod(){
        }

        protected void aProtectedMethod(){
        }

        private void aPrivateMethod(){
        }

        public Object aCovariantReturnTypeMethod(){
            return null;
        }
    }

    public static class TestingSubClass extends TestingClass {
        public void aSubMethod(){
        }

        @Override
        public String aCovariantReturnTypeMethod() {
            return null;
        }
    }

    @Override
    public void define() {

        it("collects Object public methods as part of the api", ()->{
            Set<TraditionalMethod> collectedMethods = MethodMetadata.getApiMethodsFrom(new TestingClass());

            assertThat(collectedMethods).extracting("methodName")
                    .contains("toString").contains("wait").contains("equals")
                    .contains("getClass").contains("hashCode").contains("notify").contains("notifyAll");
        });

        it("collects class own public methods", ()->{
            Set<TraditionalMethod> collectedMethods = MethodMetadata.getApiMethodsFrom(new TestingClass());

            assertThat(collectedMethods).extracting("methodName")
                    .contains("anApiMethod").contains("aCovariantReturnTypeMethod")
                    .doesNotContain("aProtectedMethod").doesNotContain("aPrivateMethod");
        });

        it("collects class inherited public methods", ()->{
            Set<TraditionalMethod> collectedMethods = MethodMetadata.getApiMethodsFrom(new TestingSubClass());

            assertThat(collectedMethods).extracting("methodName")
                    .contains("aSubMethod").contains("aCovariantReturnTypeMethod");
        });

        it("collects co-variations for method generics", ()->{
            Set<TraditionalMethod> collectedMethods = MethodMetadata.getApiMethodsFrom(new TestingSubClass());

            List<TraditionalMethod> covariantMethods = collectedMethods.stream().filter((method) -> method.getMethodName().equals("aCovariantReturnTypeMethod")).collect(Collectors.toList());
            assertThat(covariantMethods).hasSize(2);
            assertThat(covariantMethods).extracting("returnType").contains(Object.class).contains(String.class);
        });

    }
}
