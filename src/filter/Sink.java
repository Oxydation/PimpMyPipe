package filter;

import entities.WordSequence;
import interfaces.Readable;
import interfaces.Writeable;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.io.Writer;
import java.util.List;

public class Sink<T extends List<WordSequence>> implements Writeable<T>, Runnable {
    private Writer _writer;
    private interfaces.Readable<T> _readable;

    public Sink(Writer writer) {
        _writer = writer;
    }

    public Sink(Writer writer, Readable<T> tReadable) {
        _writer = writer;
        _readable = tReadable;
    }

    @Override
    public void run() {
        if (_readable != null) {
            T input;
            try {
                while ((input = _readable.read()) != null) {
                    write(input);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void write(T value) throws StreamCorruptedException {
        if (value == null) {
            System.out.println("Finished.");
            return;
        }
        try {
            for (WordSequence wordSequence : value) {
                if (wordSequence != null) {
                    _writer.write(wordSequence.toString().replaceAll("[^a-zA-Z0-9  ']", "") + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
