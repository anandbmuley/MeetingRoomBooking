var app = angular.module('MeetingRoomUI', 
		['ngRoute',
		 'MRControllers',
		 'ui.bootstrap',
		 'ngCookies']);

app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'views/Login.html',
		controller : 'LoginController'
	}).when('/login', {
		templateUrl : 'views/Login.html',
		controller : 'LoginController'
	}).when('/logout', {
		templateUrl : 'views/Login.html',
		controller : 'LogoutController'
	}).when('/create', {
		templateUrl : 'views/CreateNew.html',
		controller : 'UserController'
	}).when('/createteam', {
		templateUrl : 'views/CreateTeam.html',
		controller : 'CreateTeamController'
	}).when('/home', {
		templateUrl : 'views/Home.html',
		controller : 'HomeController'
	}).when('/teams', {
		templateUrl : 'views/Teams.html',
		controller : 'TeamsController'
	}).when('/about', {
		templateUrl : 'views/About.html',
		controller : 'AboutController'
	}).when('/contact', {
		templateUrl : 'views/Contact.html',
		controller : 'ContactController'
	});
} ]);

var controllers = angular.module('MRControllers', []);