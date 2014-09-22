package pl.warsjawa.marketing

import com.wordnik.swagger.annotations.Api
import com.wordnik.swagger.annotations.ApiOperation
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pl.warsjawa.marketing.domain.Offer
import pl.warsjawa.marketing.repository.OfferRepository
import pl.warsjawa.marketing.repository.PropagationWorker

import javax.validation.constraints.NotNull
import java.util.concurrent.Callable

import static OfferMakerApi.*

@CompileStatic
@Slf4j
@RestController
@RequestMapping(API_URL)
@Api(value = "marketingService", description = "Prepares additional offer for a given loan")
class MarketingController {

    private final PropagationWorker propagationWorker
    private final OfferRepository offerRepository

    @Autowired
    MarketingController(PropagationWorker propagationWorker, OfferRepository offerRepository) {
        this.propagationWorker = propagationWorker
        this.offerRepository = offerRepository
    }

    @RequestMapping(
            value = MARKETING_APPLICATION_URL,
            method = RequestMethod.PUT,
            consumes = API_VERSION_1,
            produces = API_VERSION_1)
    @ApiOperation(value = "Async preparation of additional offer",
            notes = "This will asynchronously prepares additional offer based on loan application and its status")
    Callable<Void> prepareMarketingOffer(@PathVariable @NotNull String loanApplicationId, @RequestBody @NotNull String loanDetails) {
        return {
            Offer offer = propagationWorker.prepareAdditionalOffer(loanApplicationId, loanDetails)
            offerRepository.saveOffer(offer)
        }
    }

    @RequestMapping(
            value = MARKETING_APPLICATION_URL,
            method = RequestMethod.GET,
            consumes = API_VERSION_1,
            produces = API_VERSION_1)
    @ApiOperation(value = "Gets additional offer for given loan application",
            notes = "This finds additional offer prepared specially for given loan application")
    Callable<Offer> prepareMarketingOffer(@PathVariable @NotNull String loanApplicationId) {
        return {
            offerRepository.findOfferById(loanApplicationId)
        }
    }
}
