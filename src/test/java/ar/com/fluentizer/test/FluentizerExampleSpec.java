package ar.com.fluentizer.test;

import ar.com.dgarcia.fluentizer.api.FluentizerApi;
import ar.com.dgarcia.fluentizer.api.FluentizerException;
import ar.com.dgarcia.fluentizer.impl.Fluentizer;
import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.fluentizer.test.testInterfaces.FluentApiExample;
import ar.com.fluentizer.test.testInterfaces.ForFluentizer;
import ar.com.fluentizer.test.testInterfaces.TraditionalApiExample;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * This type serves as a test an example of fluentizer features
 * Created by kfgodel on 23/07/14.
 */
@RunWith(JavaSpecRunner.class)
public class FluentizerExampleSpec extends JavaSpec<ForFluentizer> {

    @Override
    public void define() {

        describe("using fluentizer", () -> {

            context().fluentizer(() -> Fluentizer.create());

            describe("a traditional interface can be made fluent", () -> {

                context().traditionalApi(() -> mock(TraditionalApiExample.class));
                context().fluentApi(()-> {
                    FluentizerApi fluentizer = context().fluentizer();
                    TraditionalApiExample traditionalApi = context().traditionalApi();
                    return fluentizer.expressAs(FluentApiExample.class, traditionalApi);
                });

                it("joining two method calls with one arg each, into one method call with two args", ()->{

                    context().fluentApi().everyExampleHas("Hello").and("World");

                    verify(context().traditionalApi()).everyExampleHasAnd("Hello","World");
                });

                it("joining no arg method calls to chain decisions", ()->{
                    context().fluentApi().red().andBlue().allowsOnlyViolet();
                    context().fluentApi().red().andYellow().allowsOnlyOrange();

                    verify(context().traditionalApi()).redAndBlueAllowsOnlyViolet();
                    verify(context().traditionalApi()).redAndYellowAllowsOnlyOrange();
                });

                it("throwing an exception if no traditional method found", ()->{

                    try {
                        context().fluentApi().red().unimplementedMethod();
                        failBecauseExceptionWasNotThrown(FluentizerException.class);
                    }catch (FluentizerException e){
                        assertThat(e.getMessage()).startsWith("There's no method identified as: redUnimplementedMethod([])");
                    }

                });

            });
        });

    }
}
