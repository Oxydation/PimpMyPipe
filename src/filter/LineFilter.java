package filter;

import entities.Line;
import interfaces.Readable;
import interfaces.Writeable;

import java.security.InvalidParameterException;

/**
 * Created by Mathias on 30.10.2015.
 */
public class LineFilter<in, out> extends DataEnrichmentFilter<Character, Line> {
    private int _lineNumber = 1;

    public LineFilter(Writeable<Line> output) throws InvalidParameterException {
        super(output);
        _lineNumber = 1;
    }
    public LineFilter(Readable<Character> input) throws InvalidParameterException {
        super(input);
        _lineNumber = 1;
    }

    public LineFilter(Readable<Character> input, Writeable<Line> output) throws InvalidParameterException {
        super(input, output);
        _lineNumber = 1;
    }

    @Override
    protected boolean fillEntity(Character nextVal, Line entity) {
        if (nextVal != null && nextVal != '\n') {
            entity.setLine(entity.getLine() + nextVal);
            return false;
        }
        entity.setLine(entity.getLine().replace('\r',' '));
        entity.setLineNumber(_lineNumber++);
        return true;
    }

    @Override
    protected Line getNewEntityObject() {
        return new Line();
    }
}
