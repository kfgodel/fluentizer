package ar.com.fluentizer.test.testInterfaces;

/**
 * This type allows a fluent continuation starting from red
 * Created by kfgodel on 23/07/14.
 */
public interface RedContinuation {
    PartialRedBlue andBlue();

    PartialRedYellow andYellow();

    void unimplementedMethod();
}
