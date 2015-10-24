controllers.controller('BookRoomModalController',
		['$scope','$modalInstance','BookingService','$filter',
		 '$rootScope','$cookies','AuthenticationService','$location',
		 function($scope,$modalInstance,bookingService,$filter,
				 $rootScope,$cookies,authenticationService,$location){
	
	$scope.min = new Date();
	$scope.min.setHours(8);
	$scope.min.setMinutes(0);
	
	$scope.max = new Date();
	$scope.max.setHours(20);
	$scope.max.setMinutes(0);
	
	$scope.startTime = $scope.min;
	$scope.endTime = new Date();
	
	$scope.endTime.setHours($scope.startTime.getHours()+1);
	$scope.endTime.setMinutes($scope.startTime.getMinutes()+1);
	
	$scope.hstep = 1;
	$scope.mstep = 1;
	
	$scope.ismeridian = true
	
	$scope.bookIt = function(teamName,bookedBy){
		authenticationService.validateCookie($rootScope,'/home');
		$scope.bookingModel = {
				bookedBy : $rootScope.usr.name,
				startTime : $filter('date')($scope.startTime,'hh:mm a'),
				endTime : $filter('date')($scope.endTime,'hh:mm a'),
				teamName : $rootScope.usr.teamName,
				roomName : $scope.roomName
		}
		bookingService.bookRoom($scope.bookingModel,$scope,$modalInstance);
	}
	
	$scope.cancel = function(){
		$modalInstance.dismiss();
	}
	
}]);