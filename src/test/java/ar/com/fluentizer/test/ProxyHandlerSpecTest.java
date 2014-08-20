package ar.com.fluentizer.test;

import ar.com.dgarcia.fluentizer.impl.proxy.JavaProxies;
import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.TestContext;
import org.junit.runner.RunWith;

import java.lang.reflect.InvocationHandler;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by kfgodel on 26/07/14.
 */
@RunWith(JavaSpecRunner.class)
public class ProxyHandlerSpecTest extends JavaSpec<TestContext> {

    @Override
    public void define() {

        it("receives invocations from the proxy instance", ()->{
            InvocationHandler mockedHandler = mock(InvocationHandler.class);

            Runnable proxy = JavaProxies.createProxyOf(Runnable.class, mockedHandler);
            proxy.run();

            try {
                verify(mockedHandler).invoke(anyObject(), anyObject(), anyObject());
            } catch (Throwable e) {
                throw new RuntimeException("This shouldn't fail",e);
            }
        });

    }
}
