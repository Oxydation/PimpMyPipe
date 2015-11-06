package pipes;

import interfaces.IOable;
import interfaces.Readable;
import interfaces.Writeable;

import java.io.StreamCorruptedException;

/**
 * Created by Mathias on 30.10.2015.
 */
public class SplitPipe<E> implements IOable<E, E> {

    private Writeable<E> _writeable;
    private Writeable<E> _writeable2;
    private Readable<E> _readable;

    public SplitPipe(Writeable<E> writeable, Writeable<E> writeable2) {
        _writeable = writeable;
        _writeable2 = writeable2;
    }

    public SplitPipe(Writeable<E> writeable) {
        _writeable = writeable;
    }

    public SplitPipe(Readable<E> readable) {
        _readable = readable;
    }

    /**
     * If the object is readable, it is a pull pipe.
     * If the object is writeable, it is a push pipe.
     *
     * @param iOable
     */
    public SplitPipe(IOable<E, E> iOable, boolean isPush) {
        if (isPush) {
            _writeable = iOable;
        } else {
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

        if (_writeable2 != null) {
            _writeable2.write(value);
        }
    }
}
