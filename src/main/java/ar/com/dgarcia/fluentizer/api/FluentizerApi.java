package ar.com.dgarcia.fluentizer.api;

/**
 * This type defines the public api for fluentizer
 * Created by kfgodel on 23/07/14.
 */
public interface FluentizerApi {
    /**
     * Creates an instance of given fluent api type routing method calls to the realApiHandler.<br>
     * By using this new instance, your users can have a fluent api, while you can avoid all the class wiring to make it work.<br>
     * Define you public methods in the realApiHandler, and create interfaces to express thos methods fluently.<br>
     * Fluentizer will try to map the fluent method flow to your actual methods.
     * @param fluentApiClass The interface that acts as starting api point
     * @param realApiHandler The object that will handle the real method calls, and whose public methods will be mapped to the fluent api
     * @param <T> The type of expected fluent api
     * @return The fluent api object to be used as a nice interface of your realApiHandler
     */
    <T> T expressAs(Class<T> fluentApiClass, Object realApiHandler);
}
