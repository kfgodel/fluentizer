package ar.com.dgarcia.fluentizer.impl;

import ar.com.dgarcia.fluentizer.api.FluentizerApi;

/**
 * This type is the implementation of fluentizer
 * Created by kfgodel on 23/07/14.
 */
public class Fluentizer implements FluentizerApi {

    public static Fluentizer create() {
        Fluentizer fluentizer = new Fluentizer();
        return fluentizer;
    }
}
