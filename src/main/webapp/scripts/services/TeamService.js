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
			url : 'rest/team/add',
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
			url : 'rest/team/list',
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
	
	this.addMember = function(team,memberName){
		$http({
			url : 'rest/team/addmember',
			method : 'POST',
			headers : {
				'Content-type' : 'application/json'
			},
			data : {
				'id':team.id,
				'memberName':memberName
			}
		}).success(function(data,status){
			team.data = data;
			team.message = 'Member added successfully!';
		}).error(function(data,status){
			team.message = 'Something went wrong!';
		});
	}
	
}]);