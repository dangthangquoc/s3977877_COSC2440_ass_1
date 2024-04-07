/**
 * Dang Quoc Thang - s3977877
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ClaimList implements ClaimProcessManager {

    private List<Claim> claims;

    public ClaimList() {
        this.claims = new ArrayList<>();
    }

    @Override
    public void add(Claim claim) throws IllegalArgumentException {
        // Validate claim data
        validateClaim(claim);

        // Check for duplicate ID
        for (Claim existingClaim : claims) {
            if (existingClaim.getId().equals(claim.getId())) {
                throw new IllegalArgumentException("Claim with ID " + claim.getId() + " already exists.");
            }
        }

        claims.add(claim);
    }

    @Override
    public void update(Claim claim) throws IllegalArgumentException {
        // Validate claim data and existence
        validateClaim(claim);

        int index = -1;
        for (int i = 0; i < claims.size(); i++) {
            if (claims.get(i).getId().equals(claim.getId())) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new IllegalArgumentException("Claim not found with ID: " + claim.getId());
        }

        claims.set(index, claim);
    }

    @Override
    public void delete(String claimId) throws IllegalArgumentException {
        // Validate claim ID format
        validateClaimId(claimId);

        int index = -1;
        for (int i = 0; i < claims.size(); i++) {
            if (claims.get(i).getId().equals(claimId)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new IllegalArgumentException("Claim not found with ID: " + claimId);
        }

        claims.remove(index);
    }

    @Override
    public Claim getOne(String claimId) throws IllegalArgumentException {
        // Validate claim ID format
        validateClaimId(claimId);

        for (Claim claim : claims) {
            if (claim.getId().equals(claimId)) {
                return claim;
            }
        }
        return null;
    }

    @Override
    public List<Claim> getAll() {
        return new ArrayList<>(claims); // Return a copy to avoid modification of internal list
    }


    private void validateClaim(Claim claim) throws IllegalArgumentException {
        // Implement specific validation logic here:
        // - Check for null or empty claim ID
        // - Check if claim date is in the past
        // - Ensure insured person and card number are not null
        // - Validate document filenames (optional)
        // - Check claim amount is positive
        // - Ensure status is one of "New", "Processing", or "Done"

        // Example validation (replace with your specific checks)
        if (claim.getId() == null || claim.getId().isEmpty()) {
            throw new IllegalArgumentException("Claim ID cannot be null or empty.");
        }
        if (claim.getClaimDate().after(new Date())) {
            throw new IllegalArgumentException("Claim date cannot be in the future.");
        }
        if (claim.getInsuredPerson() == null || claim.getCardNumber() == null) {
            throw new IllegalArgumentException("Insured person and card number are required.");
        }
        if (claim.getClaimAmount() <= 0) {
            throw new IllegalArgumentException("Claim amount must be positive.");
        }
        if (!Arrays.asList("New", "Processing", "Done").contains(claim.getStatus())) {
            throw new IllegalArgumentException("Invalid claim status. Must be 'New', 'Processing', or 'Done'.");
        }
    }

    private void validateClaimId(String claimId) throws IllegalArgumentException {
        // Check if claim ID format is correct (f-numbers; 10 digits)
        if (!claimId.matches("f-\\d{10}")) {
            throw new IllegalArgumentException("Invalid claim ID format. Must be 'f-nnnnnnnnnn'.");
        }
    }
}
