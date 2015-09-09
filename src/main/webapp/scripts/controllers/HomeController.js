controllers.controller('HomeController',['$scope','$modal','BookingService',function($scope,$modal,bookingService){
	$scope.pageTitle = 'Today\'s Bookings';
	bookingService.getBookings($scope);
	$scope.message = bookingService.getMessage();
	
	$scope.teams = teams;
	
	$scope.addTeam = function(){
		
	}
	
	$scope.bookroomPopup = function(){
		var bookRoomModal = $modal.open({
			animation : true,
			scope : $scope,
			templateUrl : 'BookRoomModal.html',
			controller : 'BookRoomModalController',
			size : 'sm'
		});
	}
	
}]);