controllers.controller('TeamsController',
		['$scope','TeamService','$rootScope','$location','$cookies','AuthenticationService',
		 function($scope,teamService,$rootScope,$location,$cookies,authenticationService){
	$scope.pageTitle = 'Manage Teams';
	$scope.teams = [];
	teamService.fetchAll($scope.teams);

	authenticationService.validateCookie($rootScope,'/teams');
	
	$scope.selectTeam = function(team){
		$scope.selectedTeam = team;
	}
	
}]);