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
		}).then(function(response,status){
			scope.success = true;
			scope.bookingMessage = 'Booking Cancelled';
			self.getBookings(scope);
		},function(response,status){
			scope.success = false;
			scope.bookingMessage = response.data;
		});
	}
	
	this.getBookings = function(scope){
		$http({
			method : 'GET',
			url : 'rest/booking/list',
			headers : {
				'Content-type':'application/json'
			}
		}).then(function(response,status){
			scope.bookings = response.data;
		},function(response,status){
			
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
		}).then(function(response,status){
			scope.success=true;
			booking.message = 'Room booked successfully !';
			$modalInstance.close();
			$route.reload();
		},function(response,status){
			var existingBooking = JSON.parse(response.data);
			scope.success = false;
			if(response.status == 400){
				scope.message = 'Booking Clashes. Please get in touch with '+existingBooking.bookedBy+' from Team '+existingBooking.teamName;
			}
			booking.message = 'Something went wrong !';
		});
	}
}]);