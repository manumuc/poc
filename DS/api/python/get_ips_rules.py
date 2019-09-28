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
api_instance = deepsecurity.IntrusionPreventionRulesApi (deepsecurity.ApiClient(configuration))
api_version = 'v1'

try: 
   api_response = api_instance.list_intrusion_prevention_rules(api_version)
   pprint(api_response)
except ApiException as e:
   print ("An exception occurred when calling IntrusionPreventionRulesApi.list_intrusion_prevention_rules: %s\n" % e)
