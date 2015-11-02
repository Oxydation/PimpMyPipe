package filter;

import entities.Line;
import interfaces.Readable;
import interfaces.Writeable;

import java.security.InvalidParameterException;

/**
 * Created by Mathias on 30.10.2015.
 */
public class LineFilter<in, out> extends DataEnrichmentFilter<Character, Line> {
    public LineFilter(Writeable<Line> output) throws InvalidParameterException {
        super(output);
    }
    public LineFilter(Readable<Character> input) throws InvalidParameterException {
        super(input);
    }

    public LineFilter(Readable<Character> input, Writeable<Line> output) throws InvalidParameterException {
        super(input, output);
    }

    @Override
    protected boolean fillEntity(Character nextVal, Line entity) {
        if (nextVal != null && nextVal != -1) {
            entity.setLine(entity.getLine() + nextVal);
            return false;
        }
        return true;
    }

    @Override
    protected Line getNewEntityObject() {
        return new Line();
    }
}
