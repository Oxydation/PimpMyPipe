package filter;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamCorruptedException;

/**
 * Created by Mathias on 30.10.2015.
 */
public class Source<T extends Character> implements interfaces.Readable<T> {
    private Reader _reader;

    public Source(Reader reader) {
        _reader = reader;
    }

    @Override
    public T read() throws StreamCorruptedException {
        try {
            return (T)Character.valueOf((char)_reader.read());
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
    }
}
