package pl.warsjawa.marketing;

public class OfferMakerApi {
    public static final String API_URL = "/api" ;
    public static final String APPLICATION = "application";
    public static final String APP_NAME = "vnd.pl.warsjawa.marketing";
    public static final String JSON_V1 = ".v1+json";
    public static final String APP_JSON_V1 = APP_NAME + JSON_V1;

    public static final String API_VERSION_1 = APPLICATION + "/" + APP_JSON_V1;
    public static final String MARKETING_APPLICATION_ROOT_URL = "marketing";
    public static final String MARKETING_APPLICATION_URL = MARKETING_APPLICATION_ROOT_URL + "/{loanApplicationId}";
}
