app.service('BookingService',['$http',function($http){
	this.getBookings = function(scope){
		$http({
			method : 'GET',
			url : 'booking/list',
			headers : {
				'Content-type':'application/json'
			}
		}).success(function(data,status){
			scope.bookings = data;
		}).error(function(data,status){
			
		});
	}
	
	this.bookRoom = function(){
		
	}
}]);