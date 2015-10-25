controllers.controller('HomeController',
		['$scope','$modal','BookingService','$rootScope','$location','$cookies','AuthenticationService',
		 function($scope,$modal,bookingService,$rootScope,$location,$cookies,authenticationService){
	$scope.pageTitle = 'Today\'s Bookings';
	$rootScope.footerRelative = false;
	bookingService.getBookings($scope);
	$scope.message = bookingService.getMessage();
	
	authenticationService.validateCookie($rootScope,'/home');
	
	$scope.teams = teams;
	
	$scope.cancelBooking = function(bookingId){
		$scope.bookingId = bookingId;
		bookingService.cancelBooking($scope);
	}
	
	$scope.bookroomPopup = function(roomName){
		$scope.roomName = roomName;
		var bookRoomModal = $modal.open({
			animation : true,
			scope : $scope,
			templateUrl : 'BookRoomModal.html',
			controller : 'BookRoomModalController',
			size : 'md'
		});
	}
	
}]);