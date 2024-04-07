public abstract class Customer {

    private String id;
    private String fullName;
    private InsuranceCard insuranceCard;

    public Customer(String id, String fullName, InsuranceCard insuranceCard) {
        this.id = id;
        this.fullName = fullName;
        this.insuranceCard = insuranceCard;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public InsuranceCard getInsuranceCard() {
        return insuranceCard;
    }

    public void setInsuranceCard(InsuranceCard insuranceCard) {
        this.insuranceCard = insuranceCard;
    }

    /**
     * Abstract method to be implemented by concrete subclasses (PolicyHolder/Dependent)
     * This method should return a concrete implementation of ClaimProcessManager for managing claims specific to the customer type.
     *
     * @return An implementation of ClaimProcessManager for managing claims.
     */
    public abstract ClaimProcessManager getClaims();

    /**
     * Abstract method to define how customer information is displayed in a sorted order.
     *
     * @param sortField The field to sort by (e.g., id, fullName).
     * @param order The sorting order (e.g., asc, desc).
     * @return A formatted string representing the customer information in sorted order.
     */
    public abstract String display(String sortField, String order);

    /**
     * Abstract method to define how customer information is saved to a text file.
     *
     * @param fileName The name of the file to save the report to.
     */
    public abstract void saveReport(String fileName);
}
