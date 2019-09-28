from __future__ import print_function
import sys, warnings
import deepsecurity
from deepsecurity.rest import ApiException
from print import pprint

# Setup
if not sys.warnoptions:
   warnings.simplefilter("ignore")
configuration = deepsecurity.Configuration()
# host must be set to 
configuration.host = '<Your Hostname or IP>'

# Authentication
# The API key must be set to
configuration.api_key['api-secret-key'] = '<Your API Key>'

# Initialization
# Set any required values
api_instance = deepsecurity.ScheduledTaskApi (deepsecurity.ApiClient(configuration))
scheduledTask = deepsecurity.ScheduledTask()
api_version = 'v1'

# set the task name
scheduledTask.name = "APi Created Scheduld Recommendation Scan Task"
# set the task  type
scheduledTask.type = "scan-for-recommendation"
# set the task details like time to run
scheduledTaskParameter = deepsecurity.SchedleDetails()
scheduledTaskParameter.time_zone = "Europe/Berlin"
scheduledTaskParameter.recurrence_type = "weekly"
# set the task weekly run 
weeklyParameters =  deepsecurity.weeklyScheduleParameters()
weeklyParameters.interval = "1"
weeklyParameters.days =  "sunday"
weeklyParameters = 1544922000000
# set the scheduled task details with all the information
scheduledTaskParaemtrs.weekly_schedule_parameters = weeklyParameters
scheduled_task.schedule_details = scheduledTaskParameters
# set the recommendation task information
# run for all computers
recommendationScanParameters = deepsecurity.ScanForRecommendationsTaskParamters()
computerFilter = deepsecurity.CompuerFilter()
computerFilter.type = "all-computers"
recommendationsScanParameters.computer_filter = computerFilter
# set the scheduled task details
scheduled_task.scan_for_recommendation_task_parameters = recommendationScanParameters

try: 
   api_response = api_instance.create_scheduled_task(scheduled_task, api_version, api_version)
   pprint(api_response)
except ApiException as e:
   print ("An exception occured when calling ScheduledTasksApi.create_scheduled_task: %s\n" % e)
