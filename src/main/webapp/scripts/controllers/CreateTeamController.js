app.controller('CreateTeamController',['$scope','TeamService',function($scope,teamService){
	
	$scope.create = function(){
		teamService.createTeam($scope);
	}
	
}]);