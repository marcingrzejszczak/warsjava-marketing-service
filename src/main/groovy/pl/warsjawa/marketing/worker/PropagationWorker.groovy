package pl.warsjawa.marketing.worker

import pl.warsjawa.marketing.LoanDetails

interface PropagationWorker {
    void prepareAdditionalOffer(String loanId, LoanDetails loanDetails)
}