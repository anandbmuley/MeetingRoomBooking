app.service('TeamService',['$http',function($http){
	
	var self = this;
	
	this.createTeamJSON = function(name){
		var team = {
				'name' : name
		}
		return team;
	}
	
	this.createTeam = function(team){
		$http({
			url : 'team/add',
			method : 'POST',
			headers : {
				'Content-type':'application/json'
			},
			data : self.createTeamJSON(team.name)
		}).success(function(data,status){
			team.message = 'Team created successfully !';
		}).error(function(data,status){
			team.message = 'Something went wrong !';
		});
	}
	
	this.fetchAll = function(teams){
		$http({
			url : 'team/list',
			method : 'GET',
			headers : {
				'Content-type':'application/json'
			}
		}).success(function(data,status){
			teams.data = data;
		}).error(function(data,status){
			teams.message = 'Something went wrong!';
		});
	}
	
}]);