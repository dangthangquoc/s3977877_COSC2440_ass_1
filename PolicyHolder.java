import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class PolicyHolder extends Customer {

    public List<Customer> dependents;

    public PolicyHolder(String id, String fullName, InsuranceCard insuranceCard) {
        super(id, fullName, insuranceCard);
        this.dependents = new ArrayList<>();
    }

    public List<Customer> getDependents() {
        return Collections.unmodifiableList(dependents); // Return unmodifiable list for data protection
    }

    public void addDependent(Customer dependent) {
        this.dependents.add(dependent);
    }

    @Override
    public ClaimProcessManager getClaims() {
        // Implement logic to manage claim list specific to PolicyHolders
        // This could involve accessing claims from a central repository or a dedicated list within PolicyHolder
        return new ClaimList(); // Replace with your concrete implementation of ClaimProcessManager
    }

    // Implement display() and saveReport() methods as needed (optional)
}
