controllers.controller('HomeController',['$scope','$modal','BookingService',function($scope,$modal,bookingService){
	$scope.pageTitle = 'Today\'s Bookings';
	bookingService.getBookings($scope);
	
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
	
	$scope.addTeam = function(){
		
	}
	
	$scope.bookroomPopup = function(){
		var bookRoomModal = $modal.open({
			animation : true,
			templateUrl : 'BookRoomModal.html',
			controller : 'BookRoomModalController',
			size : 'sm'
		});
	}
	
}]);