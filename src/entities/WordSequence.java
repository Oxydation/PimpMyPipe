package entities;

import java.util.LinkedList;

/**
 * Created by Mathias on 05.11.2015.
 */
public class WordSequence {
    private int _lineNumber;
    private LinkedList<Word> _words;

    public WordSequence(LinkedList<Word> words) {
        _words = words;
    }

    public WordSequence(LinkedList<Word> words, int lineNumber) {
        _lineNumber = lineNumber;
        _words = words;
    }

    public LinkedList<Word> getWords() {
        return _words;
    }

    public void setWords(LinkedList<Word> words) {
        _words = words;
    }

    public int getLineNumber() {
        return _lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        _lineNumber = lineNumber;
    }


    private String GetWordSequenceAsString() {
        StringBuilder sb = new
                StringBuilder();

        for (Word word : _words) {
            sb.append(word + " ");
        }
        return sb.toString();

    }

    @Override
    public String toString() {
        return String.format("%s %d", GetWordSequenceAsString(), _lineNumber);
    }
}
