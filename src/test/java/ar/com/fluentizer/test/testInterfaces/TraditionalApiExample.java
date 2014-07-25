package ar.com.fluentizer.test.testInterfaces;

import java.util.List;

/**
 * This type represents a traditional api using standard java conventions
 * Created by kfgodel on 23/07/14.
 */
public interface TraditionalApiExample {
    /**
     * Exemplary join method call by the union of two separate calls
     */
    void everyExampleHasAnd(String firstString, String secondString);

    /**
     * Exemplary option combination that gives violet
     */
    void redAndBlueAllowsOnlyViolet();

    /**
     * Exemplary option combination that gives orange
     */
    void redAndYellowAllowsOnlyOrange();

    /**
     * Example of chained tokens
     */
    void startTokenMiddleTokenMiddleTokenTerminalToken();

    void createAListOfAnd(List<String> strings);
}
