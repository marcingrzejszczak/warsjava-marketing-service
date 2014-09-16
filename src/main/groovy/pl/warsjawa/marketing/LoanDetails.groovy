package pl.warsjawa.marketing

public class LoanDetails {

    String jobPosition
    int loanAmount

    @Override
    public String toString() {
        return "LoanDetails{" +
                "jobPosition='" + jobPosition + '\'' +
                ", loanAmount=" + loanAmount +
                '}';
    }

}
