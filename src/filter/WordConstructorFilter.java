package filter;

import entities.Word;
import interfaces.Readable;
import interfaces.Writeable;

import java.security.InvalidParameterException;

/**
 * Created by Mathias on 02.11.2015.
 */
public class WordConstructorFilter<in, out> extends DataEnrichmentFilter<Character, Word> {


    public WordConstructorFilter(Readable<Character> input, Writeable<Word> output) throws InvalidParameterException {
        super(input, output);
    }

    public WordConstructorFilter(Readable<Character> input) throws InvalidParameterException {
        super(input);
    }

    public WordConstructorFilter(Writeable<Word> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected boolean fillEntity(Character nextVal, Word entity) {
        if (nextVal != null && nextVal != ' ' && nextVal != '\n' && nextVal != '\r') {

            if (entity.getWord() != null) {
                entity.setWord((entity.getWord() + nextVal).trim());
            } else {
                entity.setWord(("" + nextVal).trim());
            }
            return false;
        }
        return true;
    }

    @Override
    protected Word getNewEntityObject() {
        return new Word();
    }
}
