package filter;

import entities.WordSequence;
import enumerations.Alignment;
import interfaces.Readable;
import interfaces.Writeable;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.io.Writer;

public class SinkB<T extends WordSequence> implements Writeable<T>, Runnable {
    private Writer _writer;
    private Readable<T> _readable;

    private int _amountLineChars = 87;
    private Alignment _alignment;

    public int getAmountLineChars() {
        return _amountLineChars;
    }

    public void setAmountLineChars(int amountLineChars) {
        _amountLineChars = amountLineChars;
    }

    public Alignment getAlignment() {
        return _alignment;
    }

    public void setAlignment(Alignment alignment) {
        _alignment = alignment;
    }

    public SinkB(Writer writer) {
        _writer = writer;
    }

    public SinkB(Writer writer, Readable<T> tReadable) {
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

        if (value != null) {
            try {
                String line = value.GetWordSequenceAsString();
                int amountChar = line.length();
                int rest = getAmountLineChars() - amountChar;

                String emptyLeft = "";
                String emptyRight = "";

                // Get algnment
                switch (getAlignment()) {
                    case CENTER:
                        int half = rest / 2;
                        String space = createSpaceString(half);
                        emptyLeft = space;
                        emptyRight = space;
                        break;
                    case RIGHT:
                        emptyLeft = createSpaceString(rest);
                        break;
                    case LEFT:
                    default:
                        emptyRight = createSpaceString(rest);
                        break;
                }
                _writer.write(emptyLeft + value.GetWordSequenceAsString() + emptyRight + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String createSpaceString(int amountChars) {
        StringBuilder sb = new StringBuilder(amountChars);
        for (int i = 0; i < amountChars; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
