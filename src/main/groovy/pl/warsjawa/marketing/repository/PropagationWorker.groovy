package pl.warsjawa.marketing.repository

import pl.warsjawa.marketing.domain.Offer

interface PropagationWorker {
    Offer prepareAdditionalOffer(String loanId, String loanDetails)
}