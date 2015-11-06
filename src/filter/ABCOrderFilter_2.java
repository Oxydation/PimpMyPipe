package filter;

import entities.WordSequence;
import interfaces.Readable;
import interfaces.Writeable;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mathias on 05.11.2015.
 */
public class ABCOrderFilter_2<T> extends AbstractFilter<List<WordSequence>, List<WordSequence>> {
    private List<WordSequence> _wordSequences = new LinkedList<>();

    public ABCOrderFilter_2(Readable<List<WordSequence>> input) throws InvalidParameterException {
        super(input);
    }

    public ABCOrderFilter_2(Writeable<List<WordSequence>> output) throws InvalidParameterException {
        super(output);
    }

    public ABCOrderFilter_2(Readable<List<WordSequence>> input, Writeable<List<WordSequence>> output) throws InvalidParameterException {
        super(input, output);
    }

    @Override
    public List<WordSequence> read() throws StreamCorruptedException {
        List<WordSequence> input = readInput();

        if(input == null){
            return null;
        }

        while (input != null) {
            _wordSequences.addAll(input);
            input = readInput();
        }

        Collections.sort(_wordSequences, new AlphabeticComperator());
        return _wordSequences;
    }

    @Override
    public void run() {

    }

    @Override
    public void write(List<WordSequence> value) throws StreamCorruptedException {
        if (value != null) {
            _wordSequences.addAll(value);
        } else {
            Collections.sort(_wordSequences, new AlphabeticComperator());
            writeOutput(_wordSequences);
            _wordSequences = new LinkedList<>();
        }
    }

    private class AlphabeticComperator implements Comparator<WordSequence> {
        @Override
        public int compare(WordSequence o1, WordSequence o2) {
            return o1.toString().compareToIgnoreCase(o2.toString());
        }
    }
}
