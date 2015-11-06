package filter;

import entities.Line;
import entities.Word;
import entities.WordSequence;
import interfaces.Readable;
import interfaces.Writeable;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.LinkedList;

/**
 * Created by Mathias on 30.10.2015.
 */
public class WordFilter2<in, out> extends AbstractFilter<Line, WordSequence> {


    public WordFilter2(Readable<Line> input) throws InvalidParameterException {
        super(input);
    }

    public WordFilter2(Writeable<WordSequence> output) throws InvalidParameterException {
        super(output);
    }

    public WordFilter2(Readable<Line> input, Writeable<WordSequence> output) throws InvalidParameterException {
        super(input, output);
    }

    @Override
    public WordSequence read() throws StreamCorruptedException {
        Line value = readInput();
        if (value == null) {
            return null;
        }
        return generateWordSequence(value);
    }

    @Override
    public void run() {

    }

    @Override
    public void write(Line value) throws StreamCorruptedException {
        if (value == null) {
            writeOutput(null);
            return;
        }

        writeOutput(generateWordSequence(value));
    }

    private WordSequence generateWordSequence(Line line) {
        String[] result = line.getLine().split(" ");
        LinkedList<Word> output = new LinkedList<>();

        for (String val : result) {
            output.add(new Word(val));
        }

        return new WordSequence(output, line.getLineNumber());
    }
}