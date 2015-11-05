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
public class CirculationFilter<in, out> extends AbstractFilter<WordSequence, List<WordSequence>> {
    public CirculationFilter(Readable<WordSequence> input) throws InvalidParameterException {
        super(input);
    }

    public CirculationFilter(Writeable<List<WordSequence>> output) throws InvalidParameterException {
        super(output);
    }

    public CirculationFilter(Readable<WordSequence> input, Writeable<List<WordSequence>> output) throws InvalidParameterException {
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
    public void write(WordSequence value) throws StreamCorruptedException {
        if (value == null) {
            writeOutput(null);
            return;
        }

        LinkedList<WordSequence> sequences = new LinkedList<>();

        // Add first sequence
        WordSequence lastWordSquence = value;
        sequences.add(new WordSequence((LinkedList<Word>) lastWordSquence.getWords().clone(), value.getLineNumber()));

        for (int i = 0; i < value.getWords().size() - 1; i++) {
            Word last = lastWordSquence.getWords().removeLast();
            lastWordSquence.getWords().addFirst(last);
            sequences.add(new WordSequence((LinkedList<Word>) lastWordSquence.getWords().clone(), value.getLineNumber()));
        }

        writeOutput(sequences);
    }
}
