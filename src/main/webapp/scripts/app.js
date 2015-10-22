var app = angular.module('MeetingRoomUI', 
		['ngRoute',
		 'MRControllers',
		 'ui.bootstrap',
		 'ngCookies']);

app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'Login.html',
		controller : 'LoginController'
	}).when('/login', {
		templateUrl : 'Login.html',
		controller : 'LoginController'
	}).when('/create', {
		templateUrl : 'CreateNew.html',
		controller : 'UserController'
	}).when('/home', {
		templateUrl : 'views/Home.html',
		controller : 'HomeController'
	}).when('/teams', {
		templateUrl : 'views/Teams.html',
		controller : 'TeamsController'
	});
} ]);

var controllers = angular.module('MRControllers', []);