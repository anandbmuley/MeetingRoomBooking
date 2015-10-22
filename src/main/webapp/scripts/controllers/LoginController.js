app.controller('LoginController',
		['$scope','AuthenticationService','$rootScope','TeamService','$location','$cookies','$location',
		 function($scope,authenticationService,$rootScope,teamService,$location,$cookies,$location){
			
	authenticationService.validateCookie($rootScope);
	$scope.teams = {};
	teamService.fetchAll($scope.teams);
			
	$scope.login = function(){
		authenticationService.login($scope,$rootScope,$location);
	}
	
	$scope.create = function(){
		authenticationService.create($scope);
	}
	
}]);