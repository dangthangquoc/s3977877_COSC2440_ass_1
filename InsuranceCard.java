import java.util.Date;

public class InsuranceCard {

    private String cardNumber;
    private Customer cardHolder;
    private Customer policyOwner;
    private Date expirationDate;

    // Constructor
    public InsuranceCard(String cardNumber, Customer cardHolder, Customer policyOwner, Date expirationDate) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.policyOwner = policyOwner;
        this.expirationDate = expirationDate;
    }
    // Getters
    public String getCardNumber() {
        return cardNumber;
    }

    public Customer getCardHolder() {
        return cardHolder;
    }

    public Customer getPolicyOwner() {
        return policyOwner;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    // Setters
    public void setCardHolder(Customer cardHolder) {
        this.cardHolder = cardHolder;
    }

    public void setPolicyOwner(Customer policyOwner) {
        this.policyOwner = policyOwner;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    // Display information
    public String displayInfo() {
        return "Card Number: " + cardNumber + "\n" +
                "Card Holder: " + cardHolder.getFullName() + "\n" +
                "Policy Owner: " + policyOwner.getFullName() + "\n" +
                "Expiration Date: " + expirationDate;
    }

    public Date getIssueDate() {
        return expirationDate;
    }
}
