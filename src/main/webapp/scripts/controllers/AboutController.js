app.controller('AboutController',['$scope','$rootScope','AuthenticationService',function($scope,$rootScope,authenticationService){
	$scope.pageTitle = 'About';
	$rootScope.footerRelative = true;
}]);