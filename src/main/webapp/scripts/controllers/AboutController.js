app.controller('AboutController',['$scope','$rootScope','AuthenticationService',function($scope,$rootScope,authenticationService){
	
	authenticationService.validateCookie($rootScope,'/about');
	
}]);