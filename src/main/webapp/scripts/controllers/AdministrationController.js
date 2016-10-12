app.controller('AdministrationController', 
		[ '$scope', '$rootScope','TeamService','AuthenticationService','$location', 
		function($scope, $rootScope, teamService,authenticationService,$location) {
	$scope.pageTitle = 'Administration';
	$scope.teams = [];
	teamService.fetchAll($scope.teams);
	
//	authenticationService.validateCookie($rootScope,'/admin');
	$scope.login = function(username,password,adminPasscode){
		authenticationService.login(username,password,$scope,$rootScope,$location,adminPasscode);
	}
	
	
}]);