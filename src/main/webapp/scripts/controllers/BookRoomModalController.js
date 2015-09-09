controllers.controller('BookRoomModalController',
		['$scope','$modalInstance','BookingService','$filter',function($scope,$modalInstance,bookingService,$filter){
	
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
	$scope.mstep = 15;
	
	$scope.ismeridian = true
	
	$scope.team = {
			members : ['Rohit Mehra']	
	};
		
	
	$scope.bookIt = function(teamName,bookedBy){
		$scope.bookingModel = {
				bookedBy : $scope.bookedBy,
				startTime : $filter('date')($scope.startTime,'hh:mm a'),
				endTime : $filter('date')($scope.endTime,'hh:mm a'),
				teamName : $scope.teamName
		}
		bookingService.bookRoom($scope.bookingModel);
		$modalInstance.close();
	}
	
	$scope.cancel = function(){
		$modalInstance.dismiss();
	}
	
}]);