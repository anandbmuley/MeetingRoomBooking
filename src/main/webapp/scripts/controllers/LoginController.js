app.controller('LoginController',
		['$scope','AuthenticationService','$rootScope','$location',
		 function($scope,authenticationService,$rootScope,$location){
			
	authenticationService.validateCookie($rootScope);

	$scope.user = {};

	 function isEmptyString(data){
            return data == "" || data == undefined;
     }

	$scope.login = function(){
        try{
            $scope.validate();
            var user = $scope.user;
		    authenticationService.login(user.username,user.password,$scope,$rootScope,$location);
        }catch(ex){
            $scope.message = ex;
        }
	}

	$scope.validate = function(){
	    var user = $scope.user;
	    if(isEmptyString(user.username)){
	        throw "Please provide a username";
	    }else if(isEmptyString(user.password)){
	        throw "Please provide a password";
	    }
	}
	
	$scope.logout = function(){
		authenticationService.logout();
	}
	
}]);