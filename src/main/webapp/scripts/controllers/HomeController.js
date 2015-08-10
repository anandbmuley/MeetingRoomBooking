controllers.controller('HomeController',['$scope','BookingService',function($scope,bookingService){
	$scope.pageTitle = 'Home Page';
	bookingService.getBookings($scope);
	
	$scope.addTeam = function(){
		
	}
	
}]);