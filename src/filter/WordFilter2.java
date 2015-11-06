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
        Line value = null;
        WordSequence result = null;

        while((value =  readInput()) != null && (result = generateWordSequence(value)).getWords().size() < 1){
        }

        if (value == null) {
            return null;
        }

        return result;
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

        WordSequence result = generateWordSequence(value);

        if (result.getWords().size() >= 1) {
            writeOutput(result);
        }
    }

    private WordSequence generateWordSequence(Line line) {
        String[] result = line.getLine().split(" ");
        LinkedList<Word> output = new LinkedList<>();

        for (String val : result) {
            // Check string if it is not empty
            if (!val.equals("") && !val.equals("\r")) {
                output.add(new Word(val.trim()));
            }
        }

        return new WordSequence(output, line.getLineNumber());
    }
}