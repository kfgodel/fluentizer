package ar.com.fluentizer.test.testInterfaces;

import ar.com.dgarcia.fluentizer.api.FluentizerApi;
import ar.com.dgarcia.fluentizer.impl.method.JavaMethod;
import ar.com.dgarcia.javaspec.api.TestContext;

import java.util.function.Supplier;

/**
 * This type declares context variables commonly used in fluentizer tests
 * Created by kfgodel on 23/07/14.
 */
public interface FluentizerTestContext extends TestContext {

    void fluentizer(Supplier<FluentizerApi> fluentizer);
    FluentizerApi fluentizer();

    void traditionalApi(Supplier<TraditionalApiExample> traditionalDefinition);
    TraditionalApiExample traditionalApi();

    void fluentApi(Supplier<FluentApiExample> fluentDefinition);
    FluentApiExample fluentApi();

    JavaMethod method();
    void method(Supplier<JavaMethod> definition);
}
