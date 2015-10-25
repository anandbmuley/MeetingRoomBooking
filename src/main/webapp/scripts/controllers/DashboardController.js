app.controller('DashboardController',['$scope','BookingService',function($scope,bookingService){
	$scope.pageTitle = 'Today\'s Bookings';
	bookingService.getBookings($scope);
	$scope.teams = teams;
	
}]);