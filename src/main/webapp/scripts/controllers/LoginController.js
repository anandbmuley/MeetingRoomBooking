app.controller('LoginController',
		['$scope','AuthenticationService','$rootScope','$location',
		 function($scope,authenticationService,$rootScope,$location){
			
	authenticationService.validateCookie($rootScope);
			
	$scope.login = function(username,password){
		authenticationService.login(username,password,$scope,$rootScope,$location);
	}
	
	$scope.logout = function(){
		authenticationService.logout();
	}
	
}]);