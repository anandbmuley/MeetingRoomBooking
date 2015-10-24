app.controller('MeroboSessionController',
		['$scope','$cookies','$interval','AuthenticationService','$rootScope',
		 function($scope,$cookies,$interval,authenticationService,$rootScope){
	
	if($cookies.get('sesslimit')!=undefined){
		$scope.mins = parseInt($cookies.get('sesslimit'));
		$scope.loginTimeMsg = 'Login Time : '+$cookies.get('loginTime');
		$scope.sessionMessage = 'Session Timeout : '+$scope.mins+' mins';
	}
	
	var timerHandler;
	
	$scope.startTimer = function(){
		timerHandler = $interval(function() {
			$scope.mins--;
			if($scope.mins==0){
				$scope.myStopFunction(timerHandler);
			}else{
				$scope.sessionMessage = 'Session would expire in '+$scope.mins+' mins';
			}
		},60*1000);
	}
	
	//$scope.startTimer();

	$scope.myStopFunction = function() {
		$interval.cancel(timerHandler);
		$scope.sessionMessage = 'Your session might have been expired.';
	}
	
	
	
	

	
	
	
}])