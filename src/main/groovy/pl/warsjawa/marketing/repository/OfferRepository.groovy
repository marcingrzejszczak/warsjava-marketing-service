package pl.warsjawa.marketing.repository

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Repository
import pl.warsjawa.marketing.domain.Offer

@Repository
@CompileStatic
@TypeChecked
@Slf4j
public class OfferRepository {

    private final MongoTemplate mongoTemplate

    @Autowired
    OfferRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate
    }

    public void saveOffer(Offer offer) {
        mongoTemplate.save(offer)
    }

    public Offer findOfferById(String offerId) {
        return mongoTemplate.findById(offerId, Offer.class)
    }
}
