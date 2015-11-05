package entities;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mathias on 05.11.2015.
 */
public class WordSequence {
    private LinkedList<Word> _words;

    public WordSequence(LinkedList<Word> words) {
        _words = words;
    }

    public LinkedList<Word> getWords() {
        return _words;
    }

    public void setWords(LinkedList<Word> words) {
        _words = words;
    }

    @Override
    public String toString() {
       return _words.toString();
    }
}
