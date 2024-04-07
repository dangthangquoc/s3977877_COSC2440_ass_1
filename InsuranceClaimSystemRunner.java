/**
 * Dang Quoc Thang - s3977877
 */
import java.io.IOException;
import java.text.ParseException;

public class InsuranceClaimSystemRunner {

    public static void main(String[] args) throws IOException, ParseException {
        // Create an instance of InsuranceClaimSystem (replace with your implementation details)
        ClaimProcessManager claimManager = new ClaimProcessManagerImpl(); // Assuming ClaimProcessManagerImpl provides claim management functionality
        FileIOUtil fileIOUtil = null; // Optional: Use a FileIOUtil implementation for file operations if needed

        InsuranceClaimSystem claimSystem = new InsuranceClaimSystem(claimManager, fileIOUtil);
        claimSystem.start();
    }
}
