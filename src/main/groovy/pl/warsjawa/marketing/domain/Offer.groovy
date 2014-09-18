package pl.warsjawa.marketing.domain

import groovy.transform.CompileStatic
import groovy.transform.Immutable
import groovy.transform.TypeChecked
import org.springframework.data.annotation.Id

@TypeChecked
@CompileStatic
class Offer {
    final @Id String id
    final Person person
    final String loanDecision, offerText

    Offer(String id, Person person, String loanDecision, String offerText) {
        this.id = id
        this.person = person
        this.loanDecision = loanDecision
        this.offerText = offerText
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id='" + id + '\'' +
                ", person=" + person +
                ", loanDecision='" + loanDecision + '\'' +
                ", offerText='" + offerText + '\'' +
                '}';
    }
}
