import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// Assuming classes like Customer, Claim, ClaimProcessManager (ClaimList) are already defined

public class FileIOUtil {

    private static final String CLAIMS_FILE = "claims.txt"; // Replace with your desired filename
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd"); // Date format for parsing

    public List<Claim> loadClaims() throws IOException, ParseException {
        List<Claim> claims = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CLAIMS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                // Extract information from CSV parts
                String claimId = parts[0];
                String claimDateString = parts[1];
                String insuredPersonId = parts[2];
                String cardNumber = parts[3];
                String examDateString = parts[4];
                // Assuming document filenames are in a comma-separated list from the 5th position onwards
                String documentString = line.substring(5);

                // Parse date strings
                Date claimDate = DATE_FORMAT.parse(claimDateString);
                Date examDate = DATE_FORMAT.parse(examDateString);

                // Retrieve insured person based on ID (implement logic to search for customer)
                Customer insuredPerson = getInsuredPerson(insuredPersonId);

                // Parse document filenames (optional)
                List<String> documents = parseDocumentFilenames(documentString);

                // Create a claim object
                Claim claim = new Claim(claimId, claimDate, insuredPerson, cardNumber, examDate, documents, 0.0, "New", "");

                claims.add(claim);
            }
        }
        return claims;
    }

    public void saveClaims(List<Claim> claims) throws IOException {
        try (FileWriter writer = new FileWriter(CLAIMS_FILE)) {
            for (Claim claim : claims) {
                String line = formatClaimForCsv(claim);
                writer.write(line + "\n");
            }
        }
    }

    private Customer getInsuredPerson(String insuredPersonId) {
        // Implement logic to search for and retrieve a Customer object based on the ID
        // This might involve interacting with another data source (e.g., customer list)
        System.out.println("** Functionality to retrieve customer by ID not yet implemented. Claim will be saved without insured person details. **");
        return null;
    }

    private List<String> parseDocumentFilenames(String documentString) {
        List<String> documents = new ArrayList<>();
        if (!documentString.isEmpty()) {
            documents.addAll(Arrays.asList(documentString.split(",")));
        }
        return documents;
    }

    private String formatClaimForCsv(Claim claim) {
        StringBuilder line = new StringBuilder();
        line.append(claim.getId()).append(",");
        line.append(DATE_FORMAT.format(claim.getClaimDate())).append(",");
        line.append(claim.getInsuredPerson() != null ? claim.getInsuredPerson().getId() : "").append(","); // Handle null insured person
        line.append(claim.getCardNumber()).append(",");
        line.append(DATE_FORMAT.format(claim.getExamDate())).append(",");
        // Join document filenames with commas
        line.append(String.join(",", claim.getDocuments()));
        return line.toString();
    }
}
