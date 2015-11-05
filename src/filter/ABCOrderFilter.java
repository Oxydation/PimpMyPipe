package filter;

import entities.WordSequence;
import interfaces.*;
import interfaces.Readable;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mathias on 05.11.2015.
 */
public class ABCOrderFilter<in,out> extends  DataEnrichmentFilter<List<WordSequence>,List<WordSequence>> {

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
    protected boolean fillEntity(List<WordSequence> nextVal, List<WordSequence> entity) {
        if (nextVal != null) {
            entity.addAll(nextVal);
            return false;
        }

        Collections.sort(entity, new AlphabeticComperator());
        //Collections.sort(entity, (o1, o2) -> o1.toString().compareTo(o2.toString()));
        return true;
    }

    @Override
    protected List<WordSequence> getNewEntityObject() {
        return new LinkedList<>();
    }

    private class AlphabeticComperator implements Comparator<WordSequence> {
        @Override
        public int compare(WordSequence o1, WordSequence o2) {
            return o1.toString().compareToIgnoreCase(o2.toString());
        }
    }
}
