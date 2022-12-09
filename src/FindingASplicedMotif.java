import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FindingASplicedMotif {
    public static void main(String[] args) {
        String filename = "src/rosalind_sseq.txt";
        ArrayList<String> dna = parseDNAFASTA(filename);
        String DNA = dna.get(0);
        String Motif = dna.get(1);
        System.out.println(findMotif(DNA, Motif));
    }

    /**
     * @param DNA  The DNA string
     * @param motif The motif string
     * @return The indices of the motif in the DNA string
     */
    public static String findMotif(String DNA, String motif) {
        // Result string
        StringBuilder result = new StringBuilder();
        int index = 0;
        // Loop through the DNA string
        for (int i = 0; i < motif.length(); i++) {
            // Find the index of the motif in the DNA string
            for (int j = index; j < DNA.length(); j++) {
                // If the character matches, add the index to the result string
                if (motif.charAt(i) == DNA.charAt(j)) {
                    // Add the index to the result string
                    result.append(j + 1 + " ");
                    // Set the index to the next character
                    index = j + 1;
                    // Break out of the loop (inner for loop)
                    break;
                }
            }
        }
        return result.toString();
    }

    /**
     * Parse the FASTA file into an ArrayList of Strings with the two strings being the two DNA sequences
     *
     * @param filename the name of the file to parse
     * @return an ArrayList of Strings
     */
    public static ArrayList<String> parseDNAFASTA(String filename) {
        ArrayList<String> DNAList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            StringBuilder DNAtoAdd = new StringBuilder();
            while ((line = br.readLine()) != null) {
                // If it's a header, add the DNA to the list and start a new DNA string
                if (line.startsWith(">")) {
                    if (DNAtoAdd.length() > 0) {
                        DNAList.add(DNAtoAdd.toString());
                        DNAtoAdd = new StringBuilder();
                    }
                } else {
                    // If it's not a header, add the line to the DNA string
                    DNAtoAdd.append(line);
                }
            }
            DNAList.add(DNAtoAdd.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DNAList;
    }
}
