/**
 * Dang Quoc Thang - s3977877
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class InsuranceClaimSystem {
    private Scanner scanner;
    private ClaimProcessManager claimManager;
    private FileIOUtil fileIOUtil; // Optional for file operations

    public InsuranceClaimSystem(ClaimProcessManager claimManager, FileIOUtil fileIOUtil) {
        this.scanner = new Scanner(System.in);
        this.claimManager = claimManager;
        this.fileIOUtil = fileIOUtil; // Optional for file operations
    }

    public void start() throws IOException, ParseException {
        // Optional: Load data from files using fileIOUtil
        if (fileIOUtil != null) {
            List<Claim> claims = fileIOUtil.loadClaims();
            for (Claim claim : claims) {
                claimManager.add(claim); // Add each claim individually
            }
        }

        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            processChoice(choice);
        } while (choice != 0);

        // Optional: Save data to files using fileIOUtil
        if (fileIOUtil != null) {
            fileIOUtil.saveClaims(claimManager.getAll()); // Save all claims from the ClaimProcessManager
        }
    }

    private void displayMenu() {
        System.out.println("\nInsurance Claim System");
        System.out.println("1. Add New Claim");
        System.out.println("2. Update Claim");
        System.out.println("3. Delete Claim");
        System.out.println("4. View Claim Details");
        System.out.println("5. List All Claims");
        System.out.println("6. Search Claims (by ID, insured person name, etc.)"); // Optional advanced feature
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private void processChoice(int choice) throws IOException, ParseException {
        switch (choice) {
            case 1:
                handleAddClaim();
                break;
            case 2:
                handleUpdateClaim();
                break;
            case 3:
                handleDeleteClaim();
                break;
            case 4:
                handleViewClaimDetails();
                break;
            case 5:
                handleListAllClaims();
                break;
            case 6:
                // Optional advanced feature: Implement claim search functionality
                System.out.println("Search functionality not yet implemented.");
                break;
            case 0:
                System.out.println("Exiting system...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private Claim createClaimFromUserInput() {
        // Prompt user for each required claim field

        System.out.print("Enter Claim ID (f-nnnnnnnnnn): ");
        String claimId = scanner.nextLine();

        System.out.print("Enter Claim Date (YYYY-MM-DD): ");
        String claimDateString = scanner.nextLine();
//        Date claimDate = parseDate(claimDateString); // Implement date parsing logic

        System.out.print("Enter Insurance Card Number: ");
        String cardNumber = scanner.nextLine();

        System.out.print("Enter Exam Date (YYYY-MM-DD): ");
        String examDateString = scanner.nextLine();
//        Date examDate = parseDate(examDateString); // Implement date parsing logic

        // Allow user to add multiple document filenames (optional)
        List<String> documents = getDocumentFilenames();

        System.out.print("Enter Claim Amount (positive number): ");
        double claimAmount = scanner.nextDouble();
        return null;
    }

    private void handleAddClaim() {
        System.out.println("\nAdding New Claim");

        // Collect claim information from user input
        Claim claim = createClaimFromUserInput();

        try {
            claimManager.add(claim);
            System.out.println("Claim added successfully!");
        } catch (IllegalArgumentException e) {
            System.err.println("Error adding claim: " + e.getMessage());
        }
    }

    private List<String> getDocumentFilenames() {
        System.out.println("Enter document filenames (separated by commas, or 'end' to finish):");
        List<String> documentFilenames = new ArrayList<>();
        try {
            File filename = new File("filename.txt");
            if (filename.createNewFile()) {
                System.out.println("File created: " + filename.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter("file_2.txt");
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String filename;

        do {
            filename = scanner.nextLine();
            if (!filename.equalsIgnoreCase("end") && !filename.isEmpty()) {
                documentFilenames.add(filename);
            }
        } while (!filename.equalsIgnoreCase("end"));

        return documentFilenames;
    }

//    private Date parseDate(String dateStrDateString) {
//        throw new UnsupportedOperationException("Custom parseDate not implemented yet.");
//    }

    private void handleUpdateClaim() throws IOException, ParseException {
        System.out.println("\nUpdating Claim");

        // Prompt user for the ID of the claim to update
        System.out.print("Enter Claim ID to Update: ");
        String claimId = scanner.nextLine();

        Claim claim = claimManager.getOne(claimId);
        if (claim == null) {
            System.out.println("Claim not found with ID: " + claimId);
            return;
        }

        // Prompt user for fields to update (e.g., claim date, exam date, claim amount, etc.)
        System.out.println("Update the following fields (leave blank to keep unchanged):");
        System.out.printf("Claim Date (YYYY-MM-DD) [Current: %s", claim.getClaim);
    }
    private void handleDeleteClaim() throws IOException {
        System.out.println("\nDeleting Claim");

        // Prompt user for the ID of the claim to delete
        System.out.print("Enter Claim ID to Delete: ");
        String claimId = scanner.nextLine();

        try {
            claimManager.delete(claimId);
            System.out.println("Claim deleted successfully.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error deleting claim: " + e.getMessage());
        }
    }
    private void handleViewClaimDetails() throws IOException, ParseException {
        System.out.println("\nView Claim Details");

        // Prompt user for the ID of the claim to view
        System.out.print("Enter Claim ID to View: ");
        String claimId = scanner.nextLine();

        Claim claim = claimManager.getOne(claimId);
        if (claim == null) {
            System.out.println("Claim not found with ID: " + claimId);
            return;
        }

        // Display claim details in a user-friendly format
        System.out.println("Claim Details:");
        System.out.println("  - ID: " + claim.getId());
        System.out.println("  - Claim Date: " + claim.getClaimDate()); // Assuming claimDate has a getter
        System.out.println("  - Insured Person:");
        System.out.println("      - Name: " + claim.getInsuredPerson().getFullName()); // Assuming claim has an insuredPerson with a getName() method
    }
    private void handleListAllClaims() {
        System.out.println("\nList All Claims");

        // Retrieve all claims from the claim manager
        List<Claim> claims = claimManager.getAll();

        if (claims.isEmpty()) {
            System.out.println("No claims found in the system.");
        } else {
            System.out.println("List of Claims:");
            for (Claim claim : claims) {
                // Display claim details in a concise format (e.g., ID, claim date)
                System.out.println("  - ID: " + claim.getId() + ", Claim Date: " + claim.getClaimDate()); // Assuming claimDate has a getter
            }
        }
    }
    public static void main(String[] args) throws IOException, ParseException {
        // Create ClaimProcessManager and FileIOUtil instances (if needed)
        ClaimProcessManager claimManager = new ClaimProcessManagerImpl(); // Use your implementation
        FileIOUtil fileIOUtil = null; // Optional: Use a FileIOUtil implementation if needed

        InsuranceClaimSystem claimSystem = new InsuranceClaimSystem(claimManager, fileIOUtil);
        claimSystem.start();
    }
}


