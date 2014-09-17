package pl.warsjawa.marketing.worker

import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient
import groovy.transform.CompileStatic
import groovy.transform.PackageScope
import groovy.util.logging.Slf4j
import pl.warsjawa.marketing.LoanDetails

@CompileStatic
@Slf4j
@PackageScope
class OfferMakerWorker implements PropagationWorker {

    private final ServiceRestClient serviceRestClient

    OfferMakerWorker(ServiceRestClient serviceRestClient) {
        this.serviceRestClient = serviceRestClient
    }

    @Override
    void prepareAdditionalOffer(String loanId, String loanDetails) {
        log.info('Some action has been taken' + loanDetails)
    }

}
