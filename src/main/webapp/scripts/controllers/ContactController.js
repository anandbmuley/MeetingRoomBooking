app.controller('ContactController',['$scope','$rootScope','AuthenticationService',function($scope,$rootScope,authenticationService){
	
	authenticationService.validateCookie($rootScope,'/contact');
	
}]);