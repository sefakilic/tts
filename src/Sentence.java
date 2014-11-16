package LSASummarization;

import java.util.HashMap;
import java.util.ArrayList;
import net.zemberek.islemler.KokBulucu;
import net.zemberek.yapi.Kok;

class Sentence {
    // Data members
    private String wholeSentence;
    private ArrayList<String> words;
    private HashMap<String, Integer> termCount;
    boolean isInSummary;
    
    // Methods
    Sentence(String wholeSentence) {
        isInSummary = false;
        this.wholeSentence = wholeSentence;
        // Split sentence into words
        String[] words = wholeSentence.split(" ");
        this.words = new ArrayList<String>();
        for(String word : words) {
            if(word.equals("") == false) {
                Kok[] kokler = Main.kb.kokBul(word);
                if(kokler.length > 0) {
                    this.words.add(kokler[0].icerik());
                }
            }
        }
        
        // TODO: handle <... mutluyum.">
        
        /*        
        for(String word : this.words) {
            System.out.print(word + "|");
        }
        System.out.println("");
        */
    }
    String getWholeSentence() {
        return wholeSentence;
    }
    ArrayList<String> getWords() {
        return words;
    }
    Integer getTermCount(String key) {
        return termCount.get(key);
    }
    boolean containsTerm(String key) {
        return termCount.containsKey(key);
    }
    boolean isInSummary() {
        return isInSummary;
    }
    void includeToSummary() {
        isInSummary = true;
    }
    void countTerms() {
        termCount = new HashMap<String, Integer>();
        for(String word : words) {
            if(termCount.containsKey(word)) {
                termCount.put(word, termCount.get(word) + 1);
            }
            else {
                termCount.put(word, 1);
            }
        }
    }
}
    
    
