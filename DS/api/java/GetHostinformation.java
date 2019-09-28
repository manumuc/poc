import.com.trendmicro.deepsecurity.ApiClient;

/* class will get detailed information on all of the hosts in known by Deep Security
 * Manager using APIs
 */

 public class GetHostInfromation {

    public static void main(String[] args) {
    // Setup the connection to Deep Security manager
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
    // Set any required information
    ComputersApi instance = new ComputerApi();
    Boolean overrides = false;
    String apiVersion = "v1";
    try {
       // get the list of computers in DSM
       // and display the data
       Computers result = instance.listComputers(overrides, apiVersion);
       system.out.println(result);
    } catch (ApiException e) {
       System.err.println("An exception occured when calling ComputersApi.listComputers");
       e.printStackTrace();
    } 
    }
 }
