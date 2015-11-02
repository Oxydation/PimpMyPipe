package filter;

import entities.Line;
import entities.Word;
import interfaces.*;
import interfaces.Readable;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mathias on 30.10.2015.
 */
public class WordFilter2<in,out> extends AbstractFilter<Line, List<Word>> {
    private List<Word> _tempOutputEntity;

    public WordFilter2(Readable<Line> input) throws InvalidParameterException {
        super(input);
    }

    public WordFilter2(Writeable<List<Word>> output) throws InvalidParameterException {
        super(output);
    }

    public WordFilter2(Readable<Line> input, Writeable<List<Word>> output) throws InvalidParameterException {
        super(input, output);
    }

    @Override
    public List<Word> read() throws StreamCorruptedException {

        return _tempOutputEntity;
    }

    @Override
    public void run() {

    }

    @Override
    public void write(Line value) throws StreamCorruptedException {
        if(_tempOutputEntity == null){
            _tempOutputEntity = new LinkedList<>();
        }

        String[] result = value.getLine().split(" ");
        for (String val : result){
            _tempOutputEntity.add(new Word(val));
        }
    }
}