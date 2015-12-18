# NetworkMonitoring
Network Monitoring Application sample.

Today most of the application rely on Internet Connection for regular updates on application data,
or to execute long running download operation.

Therefore, it is essential to monitor the internet connection in order to perform these operations.

This is sample to demonstrate how to check if Internet connection is available or not and also
monitores any change in the connectivity.

ConnectivityBroadCast has been created for this purpose.
Register this receiver onResume method of the activity and unregister onPause method. 
This will be give two callbacks

1. networkUnavailable()
2. networkAvailable()

Implement these two methods in the activity. 
This receiver will tell you the state of Internet connection at the time of launch of the activity for which you have register,
as well as any change in Internet connectivity while interacting with the activity.
