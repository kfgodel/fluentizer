package ar.com.dgarcia.fluentizer.api;

/**
 * This type represents an exception on fluentizer usage
 * Created by kfgodel on 26/07/14.
 */
public class FluentizerException extends RuntimeException {

    public FluentizerException(String message) {
        super(message);
    }

    public FluentizerException(String message, Throwable cause) {
        super(message, cause);
    }

    public FluentizerException(Throwable cause) {
        super(cause);
    }
}
