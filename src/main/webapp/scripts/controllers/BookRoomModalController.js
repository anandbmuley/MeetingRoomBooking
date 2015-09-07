controllers.controller('BookRoomModalController',['$scope','$modalInstance',function($scope,$modalInstance){
	
	$scope.hstep = 1;
	$scope.mstep = 15;
	
	$scope.ismeridian = true
	
	$scope.ok = function(){
		console.log('Booking room ST : '+$scope.startTime + " ET : "+$scope.endTime);
		$modalInstance.close();
	}
	
	$scope.cancel = function(){
		$modalInstance.dismiss();
	}
	
}]);