package filter;

import entities.WordSequence;
import interfaces.*;
import interfaces.Readable;

import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Mathias on 05.11.2015.
 */
public class ABCOrderFilter<in> extends DataTransformationFilter<List<WordSequence>> {
    public ABCOrderFilter(Readable<List<WordSequence>> input, Writeable<List<WordSequence>> output) throws InvalidParameterException {
        super(input, output);
    }

    public ABCOrderFilter(Readable<List<WordSequence>> input) throws InvalidParameterException {
        super(input);
    }

    public ABCOrderFilter(Writeable<List<WordSequence>> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected void process(List<WordSequence> entity) {
        Collections.sort(entity, (o1, o2) -> {return o1.getWords().getFirst().getWord().compareTo(o2.getWords().getFirst().getWord());});
    }
}
