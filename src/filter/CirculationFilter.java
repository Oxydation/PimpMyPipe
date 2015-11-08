package filter;

import entities.Word;
import entities.WordSequence;
import interfaces.Readable;
import interfaces.Writeable;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mathias on 05.11.2015.
 */
public class CirculationFilter<in, out> extends AbstractFilter<WordSequence, List<WordSequence>> {
    private HashSet<String> _notAllowedWords = new HashSet();

    public CirculationFilter(Readable<WordSequence> input) throws InvalidParameterException {
        super(input);
    }

    public CirculationFilter(Writeable<List<WordSequence>> output) throws InvalidParameterException {
        super(output);
    }

    public CirculationFilter(Readable<WordSequence> input, Writeable<List<WordSequence>> output) throws InvalidParameterException {
        super(input, output);
    }

    /**
     * Sets the suppressed words for the index creation.
     * @param suppressedWords
     */
    public void setSuppressedIndexWords(List<String> suppressedWords){
        _notAllowedWords = new HashSet<>(suppressedWords);
    }

    @Override
    public List<WordSequence> read() throws StreamCorruptedException {
        WordSequence input = readInput();

        if (input == null) {
            return null;
        }

        return generateCombiniations(input);
    }

    @Override
    public void run() {

    }

    @Override
    public void write(WordSequence value) throws StreamCorruptedException {
        if (value == null) {
            writeOutput(null);
            return;
        }

        writeOutput(generateCombiniations(value));
    }

    private List<WordSequence> generateCombiniations(WordSequence wordSequence) {
        LinkedList<WordSequence> wordSequences = new LinkedList<>();

        // Add first sequence
        WordSequence lastWordSquence = wordSequence;

        // Add new word sequence if it does not contain a suppressed word
        if(lastWordSquence.getWords().size() > 0 && !_notAllowedWords.contains(lastWordSquence.getWords().getFirst().getWord().toLowerCase().replaceAll("[^a-zA-Z0-9 ]", ""))){
            if(lastWordSquence.getWords().getFirst().getWord().matches("[a-zA-Z]*")) {
                wordSequences.add(new WordSequence((LinkedList<Word>) lastWordSquence.getWords().clone(), lastWordSquence.getLineNumber()));
            }
        }

        for (int i = 0; i < lastWordSquence.getWords().size() - 1; i++) {
            // Move last word to first position
            Word last = lastWordSquence.getWords().removeLast();
            lastWordSquence.getWords().addFirst(last);

            // Add new word sequence if it does not contain a suppressed word
            if(lastWordSquence.getWords().size() > 0 && !_notAllowedWords.contains(lastWordSquence.getWords().getFirst().getWord().toLowerCase().replaceAll("[^a-zA-Z0-9 ]", ""))){
               if(lastWordSquence.getWords().getFirst().getWord().matches("[a-zA-Z]*")){
                    wordSequences.add(new WordSequence((LinkedList<Word>) lastWordSquence.getWords().clone(), lastWordSquence.getLineNumber()));
               }

            }
        }

        return wordSequences;
    }
}
