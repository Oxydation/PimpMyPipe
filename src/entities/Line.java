package entities;

/**
 * Created by Mathias on 30.10.2015.
 */
public class Line {
    private int _lineNumber;

    private String _line = "";

    public Line() {

    }

    public Line(int lineNumber, String line) {
        _lineNumber = lineNumber;
        _line = line;
    }

    public Line(String line) {
        _line = line;
    }

    public String getLine() {
        return _line;
    }

    public void setLine(String line) {
        _line = line;
    }

    public int getLineNumber() {
        return _lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        _lineNumber = lineNumber;
    }

    @Override
    public String toString() {
        return _line;
    }
}
