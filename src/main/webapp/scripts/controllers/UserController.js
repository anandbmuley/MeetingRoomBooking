app.controller('UserController',
		['$scope','AuthenticationService','TeamService',
		 function($scope,authenticationService,teamService){
	$scope.teams = {};
	teamService.fetchAll($scope.teams);
	$scope.user = {};
	
	$scope.create = function(){
	    try{
            $scope.isFormValid();
            authenticationService.create($scope);
            $scope.user = {};
	    }catch(ex){
	        $scope.message = ex;
	    }
	}

    function isEmptyString(data){
        return data == "" || data == undefined;
    }

	$scope.isFormValid = function(){
        var isValid = false;
        var user = $scope.user;
        if(isEmptyString(user.name)){
            throw "Name is required";
        }else if(isEmptyString(user.username)){
            throw "Username is required";
        }else if(isEmptyString(user.password)){
            throw "Password is required";
        }else if(isEmptyString(user.teamName)){
            throw "Please select a team";
        }
	}


	
}]);