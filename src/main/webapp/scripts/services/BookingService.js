app.service('BookingService',['$http','$route',function($http,$route){

	var self = this;
	var message = '';
	
	this.getMessage = function(){
		return message;
	}
	
	this.cancelBooking = function(scope){
		$http({
			url : 'rest/booking/cancel?bid='+scope.bookingId,
			method : 'DELETE',
			headers : {
				'Content-type' : 'application/json'
			}
		}).success(function(data,status){
			scope.success = true;
			scope.bookingMessage = 'Booking Cancelled';
			self.getBookings(scope);
		}).error(function(data,status){
			scope.success = false;
			scope.bookingMessage = data;
		});
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
	
	this.bookRoom = function(booking,scope,$modalInstance){
		$http({
			method : 'POST',
			url : 'rest/booking/add',
			headers : {
				'Content-type':'application/json'
			},
			data :  booking
		}).success(function(data,status){
			scope.success=true;
			booking.message = 'Room booked successfully !';
			$modalInstance.close();
			$route.reload();
		}).error(function(data,status){
			var existingBooking = JSON.parse(data);
			scope.success = false;
			if(status == 400){
				scope.message = 'Booking Clashes. Please get in touch with '+existingBooking.bookedBy+' from Team '+existingBooking.teamName;
			}
			booking.message = 'Something went wrong !';
		});
	}
}]);