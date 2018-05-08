# |Info|
# Written by Bryan O'Connell, November 2014
# Purpose: Sample of a functional test script for a RESTful API.
#
# Thanks to contributors on the 'jsonplaceholder' project for making a publicly
# accesible and generic REST API (which is used in the examples below).
#    - http://jsonplaceholder.typicode.com
#    - https://github.com/typicode/jsonplaceholder
#
# |Info|

#------------------------------------------------------------------------------
# FUNCTIONS:

function CreateTimestamp
{
    $TimeInfo = New-Object System.Globalization.DateTimeFormatInfo;
    $Timestamp = Get-Date -Format $TimeInfo.SortableDateTimePattern;
    $Timestamp = $Timestamp.Replace(":", "-");

    return $Timestamp;
}

function WriteToLog
{ param([string]$TextToWrite)

    $TextToWrite | Out-File $LOG_FILE -Append;
}

function LogErrorMessage
{ param([string]$ResultMsg, [string]$ErrorMsg)

    WriteToLog $ResultMsg;
    WriteToLog ("Error Message - " + $ErrorMsg);
    WriteToLog ""; #whitespace
}

function RunRoute_GET
{ param([string]$ApiRoute, [int]$SecondsAllowed)

    Write-Host "Testing $ApiRoute";
    $ResponseData = New-Object PSObject;

    Try
    {
        $ResponseData = (Invoke-RestMethod -Uri $ApiRoute -Method Get -DisableKeepAlive -TimeoutSec $SecondsAllowed);
        #WriteToLog ("Results for $ApiRoute - (GET) PASS");
    }
    Catch
    {
        LogErrorMessage ("Results for $ApiRoute - (GET) FAIL") $_.Exception.Message;
    }

    return $ResponseData;
}

function RunRoute_DELETE
{ param([string]$ApiRoute, [int]$SecondsAllowed)

    Write-Host "Testing $ApiRoute";
    $DeleteWasMade = $False;

    Try
    {
        Invoke-WebRequest -Uri $ApiRoute -Method Delete -DisableKeepAlive -TimeoutSec $SecondsAllowed;
        #WriteToLog ("Results for $ApiRoute - (DELETE) PASS");
        $DeleteWasMade = $True;
    }
    Catch
    {
        LogErrorMessage ("Results for $ApiRoute - (DELETE) FAIL") $_.Exception.Message;
    }

    return $DeleteWasMade;
}

function RunRoute_POST
{ param([string]$ApiRoute, [object]$BodyContent, [int]$SecondsAllowed)

    Write-Host "Testing $ApiRoute";
    $ResponseData = New-Object PSObject;

    Try
    {
        $ResponseData = (Invoke-RestMethod -Uri $ApiRoute -Method Post -Body $BodyContent -ContentType $CONTENT_TYPE -DisableKeepAlive -TimeoutSec $SecondsAllowed);
        #WriteToLog ("Results for $ApiRoute - (POST) PASS");
    }
    Catch
    {
        LogErrorMessage ("Results for $ApiRoute - (POST) FAIL") $_.Exception.Message;
    }

    return $ResponseData;
}

function RunRoute_PUT
{ param([string]$ApiRoute, [object]$BodyContent, [int]$SecondsAllowed)

    Write-Host "Testing $ApiRoute";
    $UpdateWasMade = $false;

    Try
    {
        Invoke-WebRequest -Uri $ApiRoute -Method Put -Body $BodyContent -ContentType $CONTENT_TYPE -DisableKeepAlive -TimeoutSec $SecondsAllowed;
        #WriteToLog ("Results for $ApiRoute - (PUT) PASS");
        $UpdateWasMade = $true;
    }
    Catch
    {
        LogErrorMessage ("Results for $ApiRoute - (PUT) FAIL") $_.Exception.Message;
    }

    return $UpdateWasMade;
}

#------------------------------------------------------------------------------
# CONSTANTS:

Set-Variable TIME_STAMP (CreateTimestamp) -Option ReadOnly -Force;
Set-Variable LOG_FILE ("Post_" + ($TIME_STAMP + ".log")) -Option ReadOnly -Force;

Set-Variable BASE_URL ("http://localhost:8080") -Option ReadOnly -Force;
Set-Variable CONTENT_TYPE ("application/json") -Option ReadOnly -Force;

#------------------------------------------------------------------------------

# Timer will measure total runtime of the testing process.
$Timer = [System.Diagnostics.Stopwatch]::StartNew();


#POST example - assign a new Todo to the User:

for($i=1; $i -le 1000; $i++)
  {
  $isbn = Get-Random -Minimum 1000 -Maximum 1500;
  $NewBook = @"
  {
    "isbn": $isbn,
    "title": "New title",
    "pages": 803,
    "authors": [
        57
    ],
    "publisher": 14
  }
"@;
  $Book = RunRoute_POST -ApiRoute ($BASE_URL + "/book") -BodyContent ($NewBook) -SecondsAllowed 0;
  RunRoute_GET -ApiRoute ($BASE_URL + "/book/" + $isbn) -SecondsAllowed 0;

  RunRoute_DELETE -ApiRoute ($BASE_URL + "/book/" + $isbn) -SecondsAllowed 0;

  }



$Timer.Stop();
$RunTime = ("Test is complete. Total run time: " + $Timer.Elapsed.ToString())
Write-Host ($RunTime);
WriteToLog $RunTime;
