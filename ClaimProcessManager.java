import java.util.List;

public interface ClaimProcessManager {

        /**
         * Adds a new claim to the system.
         *
         * @param claim The claim object to be added.
         * @throws IllegalArgumentException if the claim is invalid.
         */
        void add(Claim claim) throws IllegalArgumentException;

        /**
         * Updates an existing claim in the system.
         *
         * @param claim The claim object with updated information.
         * @throws IllegalArgumentException if the claim is invalid or not found.
         */
        void update(Claim claim) throws IllegalArgumentException;

        /**
         * Deletes a claim from the system.
         *
         * @param claimId The ID of the claim to be deleted.
         * @throws IllegalArgumentException if the claim ID is invalid or not found.
         */
        void delete(String claimId) throws IllegalArgumentException;

        /**
         * Gets a specific claim by its ID.
         *
         * @param claimId The ID of the claim to retrieve.
         * @return The claim object with the specified ID, or null if not found.
         * @throws IllegalArgumentException if the claim ID is invalid.
         */
        Claim getOne(String claimId) throws IllegalArgumentException;

        /**
         * Gets a list of all claims in the system.
         *
         * @return A list of all claim objects.
         */
        List<Claim> getAll();

        /**
         * Adds a collection of claims to the system. (Optional, Approach 1)
         *
         * @param claims The list of claim objects to be added.
         * @throws IllegalArgumentException if any claim in the collection is invalid.
         */
        default void addAll(List<Claim> claims) throws IllegalArgumentException {
                for (Claim claim : claims) {
                        add(claim); // Call the existing add method for each claim
                }
        }
}
