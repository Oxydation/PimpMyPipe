package filter;

import interfaces.Readable;
import interfaces.Writeable;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamCorruptedException;

/**
 * Created by Mathias on 30.10.2015.
 */
public class Source<T extends Character> implements Readable<T>, Runnable {
    private Reader _reader;
    private Writeable<T> _writeable;


    public Source(Reader reader) {
        _reader = reader;
    }

    public Source(Reader reader, Writeable<T> output) {
        _writeable = output;
        _reader = reader;
    }


    @Override
    public T read() throws StreamCorruptedException {
        try {
            int value =  _reader.read();
            if(value == -1 || value == 0){
                return null;
            }
            return (T) Character.valueOf((char) value);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void run() {
        if (_writeable != null) {
            T input = null;
            try {
                while ((input = read()) != null) {
                    _writeable.write(input);
                }
                _writeable.write(null);
            } catch (Exception e) {

            }
        }
    }
}
