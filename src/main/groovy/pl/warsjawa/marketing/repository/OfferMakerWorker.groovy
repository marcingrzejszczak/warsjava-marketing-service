package pl.warsjawa.marketing.repository

import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient
import groovy.json.JsonSlurper
import groovy.transform.PackageScope
import groovy.util.logging.Slf4j
import pl.warsjawa.marketing.domain.Offer
import pl.warsjawa.marketing.domain.Person

@Slf4j
@PackageScope
class OfferMakerWorker implements PropagationWorker {

    private final ServiceRestClient serviceRestClient

    OfferMakerWorker(ServiceRestClient serviceRestClient) {
        this.serviceRestClient = serviceRestClient
    }

    @Override
    Offer prepareAdditionalOffer(String loanId, String loanDetails) {
        log.info('Some action has been taken' + loanDetails)

        def json = new JsonSlurper().parseText(loanDetails)

        String additionalOffer = doCreateAdditionalOffer(json.decision)

        Person person = new Person(json.person.firstName, json.person.lastName)
        return new Offer(loanId, person, json.decision, additionalOffer)
    }

    String doCreateAdditionalOffer(String decision) {
        switch (decision) {
            case 'granted':
                return 'We have an extra offer for you! You can have our special credit card now with interest rate as low as 39,99%/month only! The offer is valid today only - so hurry up and contact our sales at extortioners.com'
            case 'on_hold':
                return "We are sorry but we need to make additional checks before granting you the loan. We will contact you soon."
            default:
                return 'We cannot grant you a loan at the moment. To increase your rating you can open a new bank account with our partner - GreedyConsultants.ru'
        }
    }
}
