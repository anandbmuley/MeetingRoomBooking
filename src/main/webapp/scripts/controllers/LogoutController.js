app.controller('LogoutController',
		['$scope','AuthenticationService','$rootScope',
		 function($scope,authenticationService,$rootScope){
			
	authenticationService.validateCookie($rootScope);
	authenticationService.logout();
	
}]);