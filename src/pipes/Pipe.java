package pipes;

import interfaces.*;
import interfaces.Readable;

import java.io.StreamCorruptedException;

/**
 * Created by Mathias on 30.10.2015.
 */
public class Pipe<E> implements IOable<E,E> {
    private IOable<E,E> _iOable;

    /**
     * If the object is readable, it is a pull pipe.
     * If the object is writeable, it is a push pipe.
     * @param iOable
     */
    public Pipe(IOable<E,E> iOable){
        _iOable = iOable;
    }

    @Override
    public E read() throws StreamCorruptedException {
        return _iOable.read();
    }

    @Override
    public void write(E value) throws StreamCorruptedException {
        System.out.println(value);
        _iOable.write(value);
    }
}
