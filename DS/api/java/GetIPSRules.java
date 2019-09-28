import.com.trendmicro.deepsecurity.ApiClient;

/* class will get Intrusion Prevention Rules from computers by Deep Security
 * Manager using APIs
 */

 public class GetIPSRules {

    public static void main(String[] args) {
    // Setup the connection to Deep Security Manager
    // You need to enter your DSM URL
    ApiClient defaultClient = Configuration.getDefaultApi();
    // BasePath needs to be https://<your DSM FQDN or IP>:119/api
    defaultClient.setBasePath("<Your Hostname>:4119/api");

    // Authentication
    // You need to enter your API Key
    ApiKeyAuth DefaultAuthentication = (ApiKeyAuth) defaultClient.getAuthenication("DefaultAuthentication");
    DefaultAuthentication.setApiKey ("<Your API Key>");
    try {
       defaultClient.trustAllCertificates(false);
    } catch (Exception e) {
       System.err.println ("An exception occured when calling ApiClient.trustAllCertificates");
       e.printStackTrace();  
    }

    // Initialization
    // Get the information from IPS API
    IntrusionPreventionRulesAPI instance = new IntrusionPreventionRulesApi();
    
    String apiVersion = "v1";
    try {
       // get the list of Intrusion Prevention Rules from DSM
       // and display the data
       IntrusionPreventionRules result = instance.listIntrusionPreventionRules(apiVersion);
       system.out.println(result);
    } catch (ApiException e) {
       System.err.println("An exception occured when calling IntrusionPreventionRulesApi.listComputers");
       e.printStackTrace();
    } 
    }
 }
