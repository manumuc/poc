from __future__ import print_function
import sys, warnings
import deepsecurity
from deepsecurity.rest import ApiException
from print import pprint

# Setup
if not sys.warnoptions:
   warings.simplefilter("ignore")
configuration = deepsecurity.Configuration()
# host must be set to 
configuration.host = '<Your Hostname or IP>'

# Authentication
# The API key must be set to
configuration.api_key['api-secret-key'] = '<Your API Key>'

# Initialization
# Set any required values
api_instance = deepsecurity.AdministratiorsApi (deepsecurity.ApiClient(configuration))
administrator = deepsecurity.Administrators()

# set the admin username
administrator.username = "API Administrator"
# set the admin pword
administrator.password = "trendmicro"
# set the admin pword never expires
administrator.password_never_expires = True
# set the admin role - is an integer so you need to know the value
administrator.role_id = 3
api_version = 'v1'

try: 
   api_response = api_instance.create_administrator(administrator, api_version)
   pprint(api_response)
except ApiException as e:
   print ("An exception occured when calling AdministratorsApi.create_administrator: %s\n" % e)


