package filter;

import entities.Line;
import entities.Word;
import interfaces.*;
import interfaces.Readable;

import java.security.InvalidParameterException;

/**
 * Created by Mathias on 30.10.2015.
 */
public class WordFilter<in,out> extends DataEnrichmentFilter<Line, Word> {
    public WordFilter(interfaces.Readable<Line> input, Writeable<Word> output) throws InvalidParameterException {
        super(input, output);
    }

    public WordFilter(Readable<Line> input) throws InvalidParameterException {
        super(input);
    }

    public WordFilter(Writeable<Word> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected boolean fillEntity(Line nextVal, Word entity) {


        return false;
    }

    @Override
    protected Word getNewEntityObject() {
        return new Word();
    }
}
