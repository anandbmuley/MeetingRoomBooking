app.service('TeamService',['$http',function($http){
	
	var self = this;
	
	this.createTeamJSON = function(name){
		var team = {
				'name' : name
		}
		return team;
	}
	
	this.createTeam = function(scope){
		$http({
			url : 'rest/team/add',
			method : 'POST',
			headers : {
				'Content-type':'application/json'
			},
			data : self.createTeamJSON(scope.team.name)
		}).then(function(response,status){
			scope.success = true;
			scope.message = 'Team created successfully !';
			scope.team.name = '';
		},function(response,status){
			scope.success = false;
			scope.message = 'Something went wrong !';
		});
	}

	this.fetchAll = function(teams){
    		$http({
    			url : 'rest/team/list/members',
    			method : 'GET',
    			headers : {
    				'Content-type':'application/json'
    			}
    		}).then(function(response,status){
    			teams.data = response.data;
    		},function(response,status){
    			teams.message = 'Something went wrong!';
    		});
    }
	
	this.fetchTeamList = function(teams){
		$http({
			url : 'rest/team/list',
			method : 'GET',
			headers : {
				'Content-type':'application/json'
			}
		}).then(function(response,status){
			teams.data = response.data;
		},function(response,status){
			teams.message = 'Something went wrong!';
		});
	}

	this.fetchAllTeams = function(teams){
    		$http({
    			url : 'rest/team',
    			method : 'GET',
    			headers : {
    				'Content-type':'application/json'
    			}
    		}).then(function(response,status){
    			teams.data = response.data;
    		},function(response,status){
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
		}).then(function(response,status){
			team.data = response.data;
			team.message = 'Member added successfully!';
		},function(response,status){
			team.message = 'Something went wrong!';
		});
	}
	
}]);