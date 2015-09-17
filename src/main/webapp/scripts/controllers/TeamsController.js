controllers.controller('TeamsController',['$scope','TeamService',function($scope,teamService){
	$scope.pageTitle = 'Manage Teams';
	$scope.teams = teamService.fetchAll();
	
	
	$scope.createTeam = function(team){
		teamService.createTeam(team);
	}
	
	
}]);