package entities;

/**
 * Created by Mathias on 30.10.2015.
 */
public class Line{
    private String _line;

    public Line() {

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

    @Override
    public String toString() {
        return _line;
    }
}
