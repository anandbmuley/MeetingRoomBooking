var app = angular.module('MeetingRoomUI',[
    'ngRoute',
    'MRControllers',
    'MRServices']);

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
var services = angular.module('MRServices',[]);