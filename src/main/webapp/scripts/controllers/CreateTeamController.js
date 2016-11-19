app.controller('CreateTeamController',['$scope','TeamService',function($scope,teamService){

	$scope.team = {};

	$scope.create = function(){
	    var team = $scope.team;
	    if(team.name == "" || team.name == undefined){
	        $scope.message = "Please provide a team name";
	    }else{
		    teamService.createTeam($scope);
	    }
	}
	
}]);