package LSASummarization;


import java.io.*;
import java.nio.charset.Charset;
import net.zemberek.erisim.Zemberek;
import net.zemberek.islemler.KokBulucu;
import net.zemberek.yapi.Kok;
import net.zemberek.tr.yapi.TurkiyeTurkcesi;

class Main {
    static Document doc;
    static Zemberek zemberek = new Zemberek(new TurkiyeTurkcesi());
    static KokBulucu kb = zemberek.kokBulucu();
        
    // main
    public static void main(String[] args) {
        
        
        try {
            getDocument(args[0], Integer.parseInt(args[2]));
        }
        catch(Exception e) {
            System.out.println("Error in document constructor.");
            System.err.println(e);
        }
        
        /*
        System.out.println("Default Charset=" + Charset.defaultCharset());
        System.out.println("file.encoding=" + System.getProperty("file.encoding"));

        
        System.out.println("# of sentences: " + doc.getNumberOfSentences());
        System.out.println("# of terms: " + doc.getNumberOfTerms());
        */
        
        
        
        LSA.lsa();
        
        /*
        for(Sentence sentence : doc.getSentences()) {
            System.out.println(sentence.getWholeSentence());
        }
        */
        
        /*    
        System.out.println("TERMS");
        for(String str : doc.getAllTerms()) {
            System.out.print(str + " ");
        }
        System.out.println("");
        System.out.println("END OF TERMS");
        
        System.out.println("SUMMARY");
        for(Sentence sentence : doc.getSentences()) {
            if(sentence.isInSummary() == true) {
                System.out.println(sentence.getWholeSentence());
            }
        }
        System.out.println("END OF SUMMARY");
        */
        
        try {
            FileOutputStream fout = new FileOutputStream(args[1]);
            PrintStream ps = new PrintStream(fout);
            
            for(Sentence sentence : doc.getSentences()) {
                if(sentence.isInSummary() == true) {
                    ps.println(sentence.getWholeSentence());
                }
            }

            fout.close();
        }
        catch(IOException e) {
            System.err.println("unable to write to file");
            System.exit(-1);
        }
            
    }
    // Read file and create a document instance and assign it to doc.
    private static void getDocument(String fileName, int numOfSummarySentences)
                        throws Exception {
        FileInputStream f = new FileInputStream(fileName);
        InputStreamReader in = new InputStreamReader(f);
        BufferedReader br = new BufferedReader(in);
        
        StringBuffer sb = new StringBuffer();
        String eachLine = br.readLine();
        while(eachLine != null) {
            sb.append(eachLine);
            sb.append(" ");
            eachLine = br.readLine();
        }
        //System.out.println(sb.toString());
        //String utf8str = new String(sb.toString().getBytes(), "UTF-8");
        doc = new Document(sb.toString(), numOfSummarySentences); 
        
        //for(Sentence s : doc.getSentences()) {
        //    System.out.println(s.getWholeSentence().length());
        //    System.out.println(s.getWholeSentence());
        //}
    }
        
        
}
