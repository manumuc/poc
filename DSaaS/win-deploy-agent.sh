von <powerpoint bis 
Readme: 
Direkter Download von der Trend Micro Webseite:
https://files.trendmicro.com/products/deepsecurity/en/11.0/DS_Agent-Windows_11.0_U8_readme.txt
\d9rhttps://files.trendmicro.com/products/deepsecurity/en/11.0/Agent-Windows-11.0.0-662.x86_64.zip

ASSI Schaefer
manuela_rotter@trendmicro.de
Sapient!23345



Download vom der Deep Security SaaS Webkonsole (Account muss eingerichtet sein)
https://app.deepsecurity.trendmicro.com:443/software/agent/Windows/x86_64/agent.msi

SSI Schaefer
accountname: (Passwort hat sich nicht veraendert


Voraussetzungen:
- gehen Sie sicher, dass sie mit der Testinstanz ins internet kommen und die folgende SaaS Web Konsole mit ihrem Browswer erreichen koennen:
https://app.deepsecurity.trendmicro.com:443
- Pruefen Sie ob sie im Browser im Vorherigen Punkt einen Proxy genutzt haben. 

Installation des Agentenpacketes lokal auf der zu schuetzenden Testinstanz:
1, Laden Sie folgendes Packete herunter, speichern Sie es in ihrem Temp Verzeichnis und installieren Sie es auf der Zielinstanz
https://app.deepsecurity.trendmicro.com:443/software/agent/Windows/x86_64/agent.msi
2, Fuehren das heruntergeladene Packet aus: agent.msi und folgend den Anweisungen.
3, Fuehren Sie auf der Command line folgenden Befehle aus:
REM  "dsm://agents.deepsecurity.trendmicro.com:443/ Proxyport://{0}/ 192.168.1.2:8080
REM reset des Agenten 
"c:\Program Files\Trend Micro\Deep Security Agent\dsa_control" -r
REM setzten des Proxies um den Saas Deep Security Manager zu erreichen  
"c:\Program Files\Trend Micro\Deep Security Agent\dsa_control" -x "dsm_proxy://192.168.1.2:8080"
REM setzten des Proxies um die Saas Deep Security Update Source (Relay) zu erreichen  
"c:\Program Files\Trend Micro\Deep Security Agent\dsa_control" -y "Proxyport://{0}/ 192.168.1.2:8080"
REM Aktivieren des DS Agenten am Saas Deep Security Manager im SSI Schaefer Tenant und Zuweisung der Windows Policy "Windows_WAMAS"
"c:\Program Files\Trend Micro\Deep Security Agent\dsa_control" -a "dsm://agents.deepsecurity.trendmicro.com:443/" "tenantID:640D2625-3F9B-E39C-BC65-7D6C2AF65F20" "token:FFDAB59B-404A-0768-5C42-7E4ACE777046" "policyid:67"

Kontrolle am DSM ob neue Instanz auftaucht 
Download folgender

alles 
---
<powershell>
#requires -version 4.0

# PowerShell 4 or up is required to run this script
# This script detects platform and architecture.  It then downloads and installs the relevant Deep Security Agent package

if (-NOT ([Security.Principal.WindowsPrincipal] [Security.Principal.WindowsIdentity]::GetCurrent()).IsInRole([Security.Principal.WindowsBuiltInRole] "Administrator")) {
   Write-Warning "You are not running as an Administrator. Please try again with admin privileges."
   exit 1
}

$managerUrl="https://app.deepsecurity.trendmicro.com:443/"

$env:LogPath = "$env:appdata\Trend Micro\Deep Security Agent\installer"
New-Item -path $env:LogPath -type directory
Start-Transcript -path "$env:LogPath\dsa_deploy.log" -append

echo "$(Get-Date -format T) - DSA download started"
if ( [intptr]::Size -eq 8 ) { 
   $sourceUrl=-join($managerUrl, "software/agent/Windows/x86_64/") }
else {
   $sourceUrl=-join($managerUrl, "software/agent/Windows/i386/") }
echo "$(Get-Date -format T) - Download Deep Security Agent Package" $sourceUrl

$ACTIVATIONURL="dsm://agents.deepsecurity.trendmicro.com:443/"

# Proxy_Addr_Port and Proxy_User/Proxy_Password define proxy for software download and Agent activation
$Proxy_Addr_Port="192.168.1.2:8080"

# Relay_Proxy_Addr_Port and Relay_Proxy_UserPass define proxy for Agent and Relay communication
$Relay_Proxy_Addr_Port="192.168.1.2:8080"

$WebClient = New-Object System.Net.WebClient
$WebProxy = New-Object System.Net.WebProxy($Proxy_Addr_Port, $true)
$WebClient.Proxy = $WebProxy

[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12;

Try
{
     $WebClient.DownloadFile($sourceUrl,  "$env:temp\agent.msi")
} Catch [System.Net.WebException]
{
      echo " Please check that your Deep Security Manager TLS certificate is signed by a trusted root certificate authority."
      exit 2;
}

if ( (Get-Item "$env:temp\agent.msi").length -eq 0 ) {
    echo "Failed to download the Deep Security Agent. Please check if the package is imported into the Deep Security Manager. "
 exit 1
}
echo "$(Get-Date -format T) - Downloaded File Size:" (Get-Item "$env:temp\agent.msi").length

echo "$(Get-Date -format T) - DSA install started"
echo "$(Get-Date -format T) - Installer Exit Code:" (Start-Process -FilePath msiexec -ArgumentList "/i $env:temp\agent.msi /qn ADDLOCAL=ALL /l*v `"$env:LogPath\dsa_install.log`"" -Wait -PassThru).ExitCode 
echo "$(Get-Date -format T) - DSA activation started"

Start-Sleep -s 50
& $Env:ProgramFiles"\Trend Micro\Deep Security Agent\dsa_control" -r
& $Env:ProgramFiles"\Trend Micro\Deep Security Agent\dsa_control" -x ([string]::Format("dsm_proxy://{0}/", $Proxy_Addr_Port))
& $Env:ProgramFiles"\Trend Micro\Deep Security Agent\dsa_control" -y ([string]::Format("relay_proxy://{0}/", $Relay_Proxy_Addr_Port))
& $Env:ProgramFiles"\Trend Micro\Deep Security Agent\dsa_control" -a $ACTIVATIONURL "tenantID:640D2625-3F9B-E39C-BC65-7D6C2AF65F20" "token:FFDAB59B-404A-0768-5C42-7E4ACE777046" "policyid:67"
#& $Env:ProgramFiles"\Trend Micro\Deep Security Agent\dsa_control" -a dsm://agents.deepsecurity.trendmicro.com:443/ "tenantID:640D2625-3F9B-E39C-BC65-7D6C2AF65F20" "token:FFDAB59B-404A-0768-5C42-7E4ACE777046" "policyid:67"
Stop-Transcript
echo "$(Get-Date -format T) - DSA Deployment Finished"
</powershell>
----
