controllers.controller('HomeController',['$scope','BookingService',function($scope,bookingService){
	$scope.pageTitle = 'Today\'s Bookings';
	bookingService.getBookings($scope);
	
	$scope.addTeam = function(){
		
	}
	
}]);