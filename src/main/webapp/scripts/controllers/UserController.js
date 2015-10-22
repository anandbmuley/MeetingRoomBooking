app.controller('UserController',
		['$scope','AuthenticationService','TeamService',
		 function($scope,authenticationService,teamService){
	
	$scope.teams = {};
	teamService.fetchAll($scope.teams);
	
	$scope.create = function(){
		authenticationService.create($scope);
	}
	
}]);