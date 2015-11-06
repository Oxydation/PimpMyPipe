package entities;

/**
 * Created by Mathias on 30.10.2015.
 */
public class Word {

    private String _word = "";


    public Word() {
        _word = "";
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
    public boolean equals(Object obj) {
        if (obj instanceof Word) {
            Word w = (Word) obj;
            w.getWord().equals(this.getWord());
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return _word;
    }
}
