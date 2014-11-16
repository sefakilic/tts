package LSASummarization;

import Jama.*;
import java.util.ArrayList;

class LSA {
    // Data members
    static Matrix A; /* terms by sequence matrix
                      * A = [A1 A2 A3 ... An] with each colum vector Ai
                      * representing the weighted term frequency vector of
                      * sentence i in the document under consideration
                      */
    static Matrix VTranspose;
    static SingularValueDecomposition S;
    
    // Methods
    static void lsa() {
        // Proposed SVD-based document summarization method. 
        // get number of terms in the document
        int numberOfTerms = Main.doc.getNumberOfTerms();
        // get number of sentences in the document
        int numberOfSentences = Main.doc.getNumberOfSentences();
        // create matrix
        A = new Matrix(numberOfTerms, numberOfSentences);
               
        // get all terms in the document
        ArrayList<String> allTerms = Main.doc.getAllTerms();
        
        // fill matrix A
        /* The weigthed term-frequency vector Ai = [a1i a2i ... ani]'
         * of sentence i is defined as
         * aji = L(tji) * G(tji)
         * 
         * L(tji) is the local weighting for term j in sentence i
         * G(tji) is the global weighting for term j in the whole document
         *
         * Weighting schemas:
         *  1) Local weighting: Binary weight
         *  2) Global weighting: No weight
         *  No-normalization
         */
        
        
        //for(String term : allTerms) {
        //    System.out.print(term + " ");
        //}
        //System.out.println("");
        
        for(int c = 0; c < numberOfSentences; ++c) {
            Sentence sen = Main.doc.getSentence(c);
            for(int r = 0; r < numberOfTerms; ++r) {
                String term = allTerms.get(r);
                if(sen.containsTerm(term) == false) {
                    A.set(r, c, 0);
        
                }
                else {
                    A.set(r, c, 1);
                    //A.set(r, c, sen.getTermCount(term));
                }
            }
        }
        
        
        //System.out.println("\n-\n" + allTerms);
        //A.print(10,5);
        
        // Do the singular value decomposition
        // Assuming A is an mxn matrix and m >= n.
        S = A.svd();
        Matrix V = S.getV();
        VTranspose = V.transpose();
        //VTranspose.print(10, 5);
        
        int k = 0; // number of selected sentences for summarization
        while(k < Main.doc.getNumberOfSummarySentences()) {
            /* Select the sentence which has the largest index value
             * with the k'th right singular vector, and include it in
             * the summary.
             * It means find the column vector of V' whose k'th element
             * is the largest.
             */
            
            /*
            System.out.println("kth elements of column vectors when k = "
                               + k );
            for(int i = 0; i < numberOfSentences; ++i) {
                System.out.print(VTranspose.get(k, i) + " ");
            }
            System.out.println();
            */
            double largest = Double.NEGATIVE_INFINITY;
            int index = -1;
            for(int i = 0; i < numberOfSentences; ++i) {
                if(largest < VTranspose.get(k, i) && Main.doc.getSentence(i).isInSummary() == false) {
                    largest = VTranspose.get(k, i);
                    index = i;
                }
            }
        
            //System.out.println(k+1 + " index= " + index + " largest= " + largest);
            //System.out.println(allTerms.get(k));
            
            Main.doc.getSentence(index).includeToSummary();
            //Main.doc.addSummarySentence(
            //            Main.doc.getSentence(index).getWholeSentence());
            k = k + 1;
        }
    }
    
    
}
