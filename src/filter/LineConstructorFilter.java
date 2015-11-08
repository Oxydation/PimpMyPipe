package filter;

import entities.Word;
import entities.WordSequence;
import interfaces.Readable;
import interfaces.Writeable;

import java.io.IOException;
import java.io.Writer;
import java.security.InvalidParameterException;

/**
 * Created by Mathias on 30.10.2015.
 */
public class LineConstructorFilter<in, out> extends DataEnrichmentFilter<Word, WordSequence> {
    private int _lineNumber = 1;
    private int _wordsLimit = 10;
    private Writer _writer;

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

    public LineConstructorFilter(Writer writer, Readable<Word> tReadable) throws InvalidParameterException {
        super(tReadable);
        _writer = writer;
    }

    @Override
    protected boolean fillEntity(Word nextVal, WordSequence entity) {
        if (nextVal != null && entity.getWords().size() < getWordsLimit() - 1) {
            if (nextVal.getWord() != "") {
                entity.getWords().add(nextVal);
            }
            return false;
        } else {

            // Add last word and write to output if needed
            if (nextVal != null && nextVal.getWord() != "") {
                entity.getWords().add(nextVal);
            }
            entity.setLineNumber(_lineNumber++);

            if (_writer != null) {
                try {
                    _writer.write(entity.GetWordSequenceAsString() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
    }

    @Override
    protected WordSequence getNewEntityObject() {
        return new WordSequence();
    }
}
