import.com.trendmicro.deepsecurity.ApiClient;

/* class is an example of how to create a scheduled task to run recommendation scan for all computers in Deep Security
 * Manager using APIs
 */
 
 public class CreateScheduledTask {
 
    public static void main(String[] args) {
    //setup the connection to Deep Security manager
    // You need to enter your DSM URL
    ApiClient defaultClient = Configuration.getDefaultApi();
    // BasePath needs to be https://<your DSM FQDN or IP>:4119/api
    defaultClient.setBasePath("<Your Hostname>:4119/api");
    
    // Authentication
    // You need to enter your API Key
    ApiKeyAuth DefaultAuthentication = (ApiKeyAuth) defaultCilent.getAuthenication("DefaultAuthentication");
    DefaultAuthentication.setApiKey ("<Your API Key>");
    try {
       defaultClient.trustAllCertificates(false);
    } catch (Exception e) {
       System.err.println ("An exception occured when calling ApiClient.trustAllCertificates");
       e.printStackTrace();  
    }
      
    // Initialization
    // Here the new scheduled task will be setup up
    ScheduledTasksApi instance = new ScheduledTasksApi();
    ScheduledTask scheduledTask  = new ScheduledTask();
    // Set scheduled task name
    scheduledtask.setName ("API Created - Scheduled Recommenation Scan Task");
    // Set up the details - time when the task will run 
    ScheduleDetails scheduleDetails = new ScheduledDetails();
    scheduleDetails.setTimeZone("Europe/Berlin");
    scheduleDetails.setRecurrenceType(RecurrenceTypeEnum.WEEKLY);
    // Set the weekly parameters
    WeeklyScheduledParameters weeklyScheduleParameters = new WeeklyScheduleParameters();
    // set the schedule time , i.e. for start at 12/16/2018 at 1 AM
    String startDate = "2019-12-16 01:00:00";
    SimpleDateFormat dateFormatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
    try {
       Date parseDate = dateFormatter.parse(startDate);
       long startTime = parseDate.getTime();
       weeklyScheduleParameters.startTime(startTime);
    } catch (Exception e) {
       e.printStackTrace();
    } 
    // Set the task to run every 1 wwek
    // Set it for all computers
    ScanForRecommendationsTaskParameters scanForRecommndationTaskParameters = new ScanForRecommendationsTaskParameters();
    ComputerFilter computerFilter - new ComputerFilter();
    computerFilter.setType(ComputerFilter.TypeEnum.ALL_COMPUTERS);
    scanForRecommendationTaskParameters.setComputerFilter(computerFilter);
    scheduledTask.setScanForRecommendationTaskParameters(scanForRecommendationTaskParameters);
        
    String apiVersion = "v1";
    try {
       // create the new scheduled scan for recommenation task
       ScheduledTask result = instance.createScheduledTask(scheduledTask, apiVersion);
       system.out.println(result);
    } catch (ApiException e) {
       System.err.println("An exception occured when calling ScheduledTaskApi.createAdministrator");
       e.printStackTrace();
    } 
 }
