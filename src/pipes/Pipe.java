package pipes;

import interfaces.*;
import interfaces.Readable;

import java.io.StreamCorruptedException;

/**
 * Created by Mathias on 30.10.2015.
 */
public class Pipe<E> implements IOable<E,E> {

private Writeable<E>  _writeable;
    private Readable<E> _readable;

    public Pipe(Writeable<E> writeable){
    _writeable = writeable;
    }

    public Pipe(Readable<E> readable) {
        _readable = readable;
    }

    /**
     * If the object is readable, it is a pull pipe.
     * If the object is writeable, it is a push pipe.
     * @param iOable
     */
    public Pipe(IOable<E,E> iOable, boolean isPush){
       if(isPush){
           _writeable = iOable;
       }else{
           _readable = iOable;
       }
    }

    @Override
    public E read() throws StreamCorruptedException {
        return _readable.read();
    }

    @Override
    public void write(E value) throws StreamCorruptedException {
        _writeable.write(value);
    }
}
