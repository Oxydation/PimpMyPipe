package filter;

import entities.Word;
import entities.WordSequence;
import interfaces.Readable;
import interfaces.Writeable;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mathias on 05.11.2015.
 */
public class CirculationFilter<in, out> extends AbstractFilter<List<Word>, List<WordSequence>> {
    public CirculationFilter(Readable<List<Word>> input) throws InvalidParameterException {
        super(input);
    }

    public CirculationFilter(Writeable<List<WordSequence>> output) throws InvalidParameterException {
        super(output);
    }

    public CirculationFilter(Readable<List<Word>> input, Writeable<List<WordSequence>> output) throws InvalidParameterException {
        super(input, output);
    }

    @Override
    public List<WordSequence> read() throws StreamCorruptedException {
        return null;
    }

    @Override
    public void run() {

    }

    @Override
    public void write(List<Word> value) throws StreamCorruptedException {
        LinkedList<WordSequence> sequences = new LinkedList<>();

        // Add first sequence
        WordSequence lastWordSquence = new WordSequence((LinkedList<Word>) value);
        sequences.add(new WordSequence((LinkedList<Word>) lastWordSquence.getWords().clone()));

        for (int i = 0; i < value.size() - 1; i++) {
            Word last = lastWordSquence.getWords().removeLast();
            lastWordSquence.getWords().addFirst(last);
            sequences.add(new WordSequence((LinkedList<Word>) lastWordSquence.getWords().clone()));
        }

        writeOutput(sequences);
    }
}
