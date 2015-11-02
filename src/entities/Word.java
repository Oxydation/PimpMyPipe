package entities;

/**
 * Created by Mathias on 30.10.2015.
 */
public class Word {

    private String _word;


    public Word(){

    }

    public Word(String word) {
        _word = word;
    }

    public String getWord() {
        return _word;
    }

    public void setWord(String word) {
        _word = word;
    }

    @Override
    public String toString() {
        return _word;
    }
}
