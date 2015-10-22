controllers.controller('TeamsController',
		['$scope','TeamService','$rootScope','$location','$cookies','AuthenticationService',
		 function($scope,teamService,$rootScope,$location,$cookies,authenticationService){
	$scope.pageTitle = 'Manage Teams';
	$scope.teams = [];
	teamService.fetchAll($scope.teams);

	authenticationService.validateCookie($rootScope,'/teams');
	
	$scope.createTeam = function(team){
		teamService.createTeam(team);
		team.name='';
		teamService.fetchAll($scope.teams);
	}
	
	$scope.selectTeam = function(team){
		$scope.selectedTeam = team;
	}
	
	$scope.addMember = function(team,member){
		teamService.addMember(team,member.name);
		member.name = '';
		teamService.fetchAll($scope.teams);
	}
	
	
}]);