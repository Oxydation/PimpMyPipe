package filter;

import entities.Line;
import entities.Word;
import interfaces.Readable;
import interfaces.Writeable;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mathias on 30.10.2015.
 */
public class WordFilter2<in, out> extends AbstractFilter<Line, List<Word>> {
    public WordFilter2(Readable<Line> input) throws InvalidParameterException {
        super(input);
    }

    public WordFilter2(Writeable<List<Word>> output) throws InvalidParameterException {
        super(output);
    }

    public WordFilter2(Readable<Line> input, Writeable<List<Word>> output) throws InvalidParameterException {
        super(input, output);
    }

    @Override
    public List<Word> read() throws StreamCorruptedException {
// TODO: für pull
        return null;
    }

    @Override
    public void run() {

    }

    @Override
    public void write(Line value) throws StreamCorruptedException {
        String[] result = value.getLine().split(" ");
        List<Word> output = new LinkedList<>();

        for (String val : result) {
            output.add(new Word(val));
        }

        writeOutput(output);
    }
}