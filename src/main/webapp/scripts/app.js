var app = angular.module('MeetingRoomUI',[
    'ngRoute',
    'MRControllers']);

app.config(['$routeProvider',function($routeProvider){
	$routeProvider.
		when('/',{
			templateUrl : 'views/Home.html',
			controller : 'HomeController'
		}).
		when('/book',{
			templateUrl : 'views/Booking.html',
			controller : 'BookingController'
		});
}]);

var controllers = angular.module('MRControllers',[]);