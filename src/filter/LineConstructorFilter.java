package filter;

import entities.Line;
import entities.Word;
import entities.WordSequence;
import interfaces.Readable;
import interfaces.Writeable;

import java.security.InvalidParameterException;

/**
 * Created by Mathias on 30.10.2015.
 */
public class LineConstructorFilter<in, out> extends DataEnrichmentFilter<Word, WordSequence> {
    private int _lineNumber = 1;
    private int _wordsLimit = 10;

    public int getWordsLimit() {
        return _wordsLimit;
    }

    public void setWordsLimit(int wordsLimit) {
        _wordsLimit = wordsLimit;
    }

    public LineConstructorFilter(Readable<Word> input, Writeable<WordSequence> output) throws InvalidParameterException {
        super(input, output);
        _lineNumber = 1;
    }

    public LineConstructorFilter(Readable<Word> input) throws InvalidParameterException {
        super(input);
        _lineNumber = 1;
    }


    public LineConstructorFilter(Writeable<WordSequence> output) throws InvalidParameterException {
        super(output);
        _lineNumber = 1;
    }

    @Override
    protected boolean fillEntity(Word nextVal, WordSequence entity) {
        if (nextVal != null && entity.getWords().size() < getWordsLimit()) {
            entity.getWords().add(nextVal);
            return false;
        }
        //entity.setLine(entity.getLine().replace('\r', ' '));
        entity.setLineNumber(_lineNumber++);
        return true;
    }

    @Override
    protected WordSequence getNewEntityObject() {
        return new WordSequence();
    }
}
