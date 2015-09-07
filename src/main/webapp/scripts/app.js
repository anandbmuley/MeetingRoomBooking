var app = angular.module('MeetingRoomUI',[
    'ngRoute',
    'MRControllers',
    'ui.bootstrap']);

app.config(['$routeProvider',function($routeProvider){
	$routeProvider.
		when('/',{
			templateUrl : 'views/Home.html',
			controller : 'HomeController'
		}).
		when('/teams',{
			templateUrl : 'views/Teams.html',
			controller : 'TeamsController'
		});
}]);

var controllers = angular.module('MRControllers',[]);