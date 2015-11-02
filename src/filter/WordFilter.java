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
public class WordFilter<in, out> extends DataEnrichmentFilter<Line, List<Word>>  {


    public WordFilter(interfaces.Readable<Line> input, Writeable<List<Word>> output) throws InvalidParameterException {
        super(input, output);
    }

    public WordFilter(Readable<Line> input) throws InvalidParameterException {
        super(input);
    }

    public WordFilter(Writeable<List<Word>> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected boolean fillEntity(Line nextVal, List<Word> entity) {
        // Könnte man auch mit einem Abstract Filter erreichen: einfach den Code in das Write einfügen
        String[] result = nextVal.getLine().split(" ");
        for (String val : result){
            entity.add(new Word(val));
        }
        return true;
    }

    @Override
    protected List<Word> getNewEntityObject() {
        return null;
    }
}
