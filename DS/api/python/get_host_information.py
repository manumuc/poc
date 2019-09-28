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
api_instance = deepsecurity.ComputersApi (deepsecurity.ApiClient(configuration))
api_version = 'v1'
overrides = False

try: 
   api_response = api_instance.create_computers(api_version, overrides)
   pprint(api_response)
except ApiException as e:
   print ("An exception occurred when calling ComputersApi.list_computers: %s\n" % e)
