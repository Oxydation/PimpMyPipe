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
    private interfaces.Readable<T> _tReadable;

    public Sink(Writer writer) {
        _writer = writer;
    }

    public Sink(Writer writer, Readable<T> tReadable) {
        _writer = writer;
        _tReadable = tReadable;
    }

    @Override
    public void run() {

    }

    @Override
    public void write(T value) throws StreamCorruptedException {
        if(value == null){
            System.out.println("Finished.");
            return;
        }
        try {
            for (WordSequence wordSequence : value) {
                if(wordSequence != null){
                    _writer.write(wordSequence.toString() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
