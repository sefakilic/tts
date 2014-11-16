package LSASummarization;

import java.util.ArrayList;
import java.text.BreakIterator;
import java.util.HashSet;

class Document {
    // Data members
    
    private String text;
    private ArrayList<Sentence> sentences;
    private int numberOfTerms;
    private ArrayList<String> allTerms;
    private int numberOfSummarySentences;
//   private ArrayList<String> autoSummarySentences;
    
    // Methods
    Document(String text, int numberOfSummarySentences) {
    
        this.text = text;
        sentences = new ArrayList<Sentence>();
        breakIntoSentences();
        this.numberOfSummarySentences = numberOfSummarySentences;
        // Count number of different terms
        countTerms();
        // initialize summary sentences array list
//        autoSummarySentences = new ArrayList<String>();
        
        /*
        System.out.println("Document created");
        System.out.println("sentences:");
        for(Sentence s : sentences) {
            System.out.println("\t" + s.getWholeSentence());
        }
        System.out.println("numberOfSentences = " + sentences.size());
        */
    }
    
    String getText() {
        return text;
    }
    
    int getNumberOfTerms() {
        return numberOfTerms;
    }
    
    Sentence getSentence(int i) {
        return sentences.get(i);
    }
    
    ArrayList<Sentence> getSentences() {
        return sentences;
    }
    
    int getNumberOfSentences() {
        return sentences.size();
    }
    
    ArrayList<String> getAllTerms() {
        return allTerms;
    }
    
    int getNumberOfSummarySentences() {
        return numberOfSummarySentences;
    }
//    ArrayList<String> getSummarySentences() {
//        return autoSummarySentences;
//   }
//    void addSummarySentence(String sentence) {
//        autoSummarySentences.add(sentence);
 //   }
    
    private void addSentence(Sentence newSentence) {
        sentences.add(newSentence);
    }
    
    // Break the text into sentences
    private void breakIntoSentences() {
        //System.out.println("breakIntoSentences");
        Sentence sen;
        String str;
        BreakIterator sb = BreakIterator.getSentenceInstance();
        sb.setText(text);
        int index = 0;
        while(sb.next() != BreakIterator.DONE) {
            str = text.substring(index, sb.current());
            sen = new Sentence(str);
            sentences.add(sen);
            index = sb.current();
        }
        
        
        /*
        System.out.println("<SENTENCES num=" + 
                           sentences.size()+">");
        for(Sentence s : sentences) {
            System.out.println(s.getWholeSentence());
        }
        System.out.println("</SENTENCES>");
        */
        
        
    }
    
    // Count the number of different terms in document
    // Call after breakIntoSentences method.
    private void countTerms() {
        numberOfTerms = 0;
        HashSet<String> terms = new HashSet<String>();
        for(Sentence sen : sentences) {
            sen.countTerms();
            for(String word : sen.getWords()) {
                terms.add(word);
            }
        }
        numberOfTerms = terms.size();
        //System.out.println("numberOfTerms: " + numberOfTerms);
        // Put all terms into data member allTerms
        allTerms = new ArrayList<String>();
        for(String term : terms) {
            allTerms.add(term);
        }
        
        /*
        System.out.println("<TERMS>");
        for(String term : allTerms) {
            System.out.println(term);
        }
        System.out.println("</TERMS>");
        */
    }
    
}
    
    
