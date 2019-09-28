import.com.trendmicro.deepsecurity.ApiClient;

/* class is an example of how to create a new administrator account in Deep Security
 * Manager using APIs
 */
 
 public class CreateNewAdministror {
 
    public static void main(String[] args) {
    //setup the connection to Deep Security manager
    // You need to enter your DSM URL
    ApiClient defaultClient = Configuration.getDefaultApi();
    //BasePath needs to be https://<your DSM FQDN or IP>:4119/api
    defaultClient.setBasePath("<Your Hostname>:4119/api");
    
    //Authentication
    // You need to enter your API Key
    ApiKeyAuth DefaultAuthentication = (ApiKeyAuth) defaultCilent.getAuthenication("DefaultAuthentication");
    DefaultAuthentication.setApiKey ("<Your API Key>");
    try {
       defaultClient.trustAllCertificates(false);
    } catch (Exeption e) {
       System.err.println ("An exeption occured when calling ApiClient.trustAllCertificates");
       e.printStackTrace();  
    }
      
    //Initialization
    // Here the new administrator account information is set up
    AdministratorsApi instance = new AdministratorApi();
    Administrator administrator = new Administrator();
    //Set admin account  username
    administrator.username ("API Administrator");
    //Set admin account pword   
    administrator.password ("trendmicro");
    //Set the admin account pword to never expire
    administrator.passwordNeverExpires(true);
    //Set the admin role
    // using the role ID as an integer - which is needed upfront to set the desired role
    administrator.roleID(3);
    String apiVersion = "v1";
    try {
       //we create the new admin and print the details once the account is created
       Administrator result = instance.createAdministrator(administrator, apiVersion);
       system.out.println(result);
    } catch (ApiException e) {
       System.err.println("An exeption occured when calling Administrator's Api.createAdministrator");
       e.printStackTrace();
    } 
    }
 }
