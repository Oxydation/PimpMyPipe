package entities;

import java.util.List;

/**
 * Created by Mathias on 30.10.2015.
 */
public class Index {
    private List<Word> _wordList;

    private int _line;

    public Index(List<Word> wordList, int line) {
        _wordList = wordList;
        _line = line;
    }

    public List<Word> getWordList() {
        return _wordList;
    }

    public void setWordList(List<Word> wordList) {
        _wordList = wordList;
    }

    public int getLine() {
        return _line;
    }

    public void setLine(int line) {
        _line = line;
    }
}
