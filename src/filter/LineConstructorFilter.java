package filter;

import entities.Word;
import entities.WordSequence;
import enumerations.Alignment;
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
    private int _amountLineChars = 87;
    private Alignment _alignment;

    private Writer _writer;

    public int getWordsLimit() {
        return _wordsLimit;
    }

    public void setWordsLimit(int wordsLimit) {
        _wordsLimit = wordsLimit;
    }

    public int getAmountLineChars() {
        return _amountLineChars;
    }

    public void setAmountLineChars(int amountLineChars) {
        _amountLineChars = amountLineChars;
    }

    public Alignment getAlignment() {
        return _alignment;
    }

    public void setAlignment(Alignment alignment) {
        _alignment = alignment;
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

                    // TODO: Add alignment!
                    String line = entity.GetWordSequenceAsString();
                    int amountChar = line.length();
                    int rest = getAmountLineChars() - amountChar;

                    String emptyLeft = "";
                    String emptyRight = "";

                    // Get algnment
                    switch (getAlignment()) {
                        case CENTER:
                            int half = rest/ 2;
                            String space = createSpaceString(half);
                            emptyLeft = space;
                            emptyRight = space;
                            break;
                        case RIGHT:
                            emptyLeft = createSpaceString(rest);
                            break;
                        case LEFT:
                        default:
                            emptyRight = createSpaceString(rest);
                            break;
                    }
                    _writer.write(emptyLeft + entity.GetWordSequenceAsString() + emptyRight+ "\n");
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

    private String createSpaceString(int amountChars) {
        StringBuilder sb = new StringBuilder(amountChars);
        for (int i = 0; i < amountChars; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
