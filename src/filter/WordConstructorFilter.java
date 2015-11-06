package filter;

import entities.Line;
import entities.Word;
import interfaces.*;
import interfaces.Readable;
import javafx.scene.chart.PieChart;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * Created by Mathias on 02.11.2015.
 */
public class WordConstructorFilter<in, out> extends DataEnrichmentFilter<Character, Word>  {


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
        if (nextVal != null && nextVal != ' ') {
            entity.setWord(entity.getWord() + nextVal);
            return false;
        }
        return true;
    }

    @Override
    protected Word getNewEntityObject() {
        return new Word();
    }
}
