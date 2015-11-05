package filter;

import interfaces.*;
import interfaces.Readable;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

/**
 * Created by Mathias on 05.11.2015.
 */
public class ABCOrderFilter2<T> extends  AbstractFilter<T,T> {
    public ABCOrderFilter2(interfaces.Readable<T> input) throws InvalidParameterException {
        super(input);
    }

    public ABCOrderFilter2(Writeable<T> output) throws InvalidParameterException {
        super(output);
    }

    public ABCOrderFilter2(Readable<T> input, Writeable<T> output) throws InvalidParameterException {
        super(input, output);
    }

    @Override
    public T read() throws StreamCorruptedException {
        return null;
    }

    @Override
    public void run() {

    }

    @Override
    public void write(T value) throws StreamCorruptedException {

    }
}
