app.service('BookingService',['$http',function($http){
	
	var message = '';
	
	this.getMessage = function(){
		return message;
	}
	
	this.getBookings = function(scope){
		$http({
			method : 'GET',
			url : 'rest/booking/list',
			headers : {
				'Content-type':'application/json'
			}
		}).success(function(data,status){
			scope.bookings = data;
		}).error(function(data,status){
			
		});
	}
	
	this.bookRoom = function(booking){
		$http({
			method : 'POST',
			url : 'rest/booking/add',
			headers : {
				'Content-type':'application/json'
			},
			data :  booking
		}).success(function(data,status){
			booking.message = 'Room booked successfully !';
		}).error(function(data,status){
			booking.message = 'Something went wrong !';
		});
	}
}]);