package pl.warsjawa.marketing

import pl.warsjawa.base.MicroserviceMvcIntegrationSpec

class AcceptanceSpec extends MicroserviceMvcIntegrationSpec {

    def "should prepare offer for a perspective loan"() {
        given: "a loan application with job position 'IT' or 'Banking' with amount greater than or equal to 1000"
        when: 'preparing additional offer'
        then: 'new offer with credit card proposal should be stored in db'
    }

    def "should prepare offer for a premium loan"() {
        given: "a loan application with job position 'IT' or 'Banking' with amount greater than or equal to 2250"
        when: 'preparing additional offer'
        then: 'new offer with credit card and regular savings plan proposals should be stored in db'
    }

    def "should not prepare offer for a non perspective loan"() {
        given: "a loan application with job position 'IT' or 'Banking' with amount smaller than 1000"
        when: 'preparing additional offer'
        then: 'new offer with credit card and regular savings plan proposals should be stored in db'
    }

}
