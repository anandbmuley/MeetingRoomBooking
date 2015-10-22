controllers.controller('HomeController',
		['$scope','$modal','BookingService','$rootScope','$location','$cookies','AuthenticationService',
		 function($scope,$modal,bookingService,$rootScope,$location,$cookies,authenticationService){
	$scope.pageTitle = 'Today\'s Bookings';
	bookingService.getBookings($scope);
	$scope.message = bookingService.getMessage();
	
	authenticationService.validateCookie($rootScope,'/home');
	
	$scope.teams = teams;
	
	$scope.addTeam = function(){
		
	}
	
	$scope.bookroomPopup = function(roomName){
		$scope.roomName = roomName;
		var bookRoomModal = $modal.open({
			animation : true,
			scope : $scope,
			templateUrl : 'BookRoomModal.html',
			controller : 'BookRoomModalController',
			size : 'sm'
		});
	}
	
}]);