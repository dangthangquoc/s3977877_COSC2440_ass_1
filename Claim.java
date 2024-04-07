import java.util.Date;
import java.util.List;

public class Claim {

    public String getClaim;
    private String id;
    private Date claimDate;
    private Customer insuredPerson;
    private String cardNumber;
    private Date examDate;
    private List<String> documents;
    private double claimAmount;
    private ClaimStatus status;
    private String receiverBank;
    private String receiverName;
    private String receiverAccountNumber;

    public boolean isValidIdFormat(String id) {
        // Implement validation logic here
        // Example: Check if the ID starts with "f-" and has 10 following digits
        return id.startsWith("f-") && id.length() == 13 && id.substring(2).matches("\\d{10}");
    }

    public boolean isValidDocumentFormat(String document) {
        // Implement validation logic here
        // Example: Check if the document format matches ClaimId_CardNumber_DocumentName.pdf
        String[] parts = document.split("_");
        return parts.length == 3 && parts[2].endsWith(".pdf");
    }


    // Constructor
    public Claim(String id, Date claimDate, Customer insuredPerson, String cardNumber, Date examDate,
                 List<String> documents, double claimAmount, String receiverBank,
                 String receiverName) {
        if (!isValidIdFormat(id)) {
            throw new IllegalArgumentException("Invalid ID format. Must be 'f-' followed by 10 numbers.");
        }
        for (String document : documents) {
            if (!isValidDocumentFormat(document)) {
                throw new IllegalArgumentException("Invalid document format. Must be ClaimId_CardNumber_DocumentName.pdf");
            }
        }
        this.id = id;
        this.claimDate = claimDate;
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = examDate;
        this.documents = documents;
        this.claimAmount = claimAmount;
        this.status = status;
        this.receiverBank = receiverBank;
        this.receiverName = receiverName;
        this.receiverAccountNumber = receiverAccountNumber;
    }

    // Getters
    public String getId() {
        return id;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public Customer getInsuredPerson() {
        return insuredPerson;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Date getExamDate() {
        return examDate;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public String getReceiverBank() {
        return receiverBank;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public void setInsuredPerson(Customer insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }

    public void setReceiverBank(String receiverBank) {
        this.receiverBank = receiverBank;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public void setReceiverAccountNumber(String receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public boolean isNew() {
        return status == ClaimStatus.New;
    }

    public boolean isProcessing() {
        return status == ClaimStatus.Processing;
    }

    public boolean isDone() {
        return status == ClaimStatus.Done;
    }

}
