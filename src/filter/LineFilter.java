package filter;

import entities.Line;
import interfaces.Readable;
import interfaces.Writeable;

import java.security.InvalidParameterException;

/**
 * Created by Mathias on 30.10.2015.
 */
public class LineFilter<in, out> extends DataEnrichmentFilter<Character, Line> {
    public LineFilter(Readable<Character> input) throws InvalidParameterException {
        super(input);
    }

    public LineFilter(Readable<Character> input, Writeable<Line> output) throws InvalidParameterException {
        super(input, output);
    }

    @Override
    protected boolean fillEntity(Character nextVal, Line entity) {
        if (nextVal != null && !nextVal.equals("\n\r")) {
            return false;
        }
        return true;
    }

    @Override
    protected Line getNewEntityObject() {
        return new Line();
    }
}
