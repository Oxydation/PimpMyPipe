package pipes;

import interfaces.IOable;

import java.io.StreamCorruptedException;

/**
 * Created by Mathias on 30.10.2015.
 */
public class Pipe<T,E> implements IOable<T,E> {
    public Pipe() {
    }



    @Override
    public E read() throws StreamCorruptedException {
        return null;
    }

    @Override
    public void write(T value) throws StreamCorruptedException {
    
    }
}
