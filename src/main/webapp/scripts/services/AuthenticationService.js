app.service('AuthenticationService',['$http','$cookies','$location',function($http,$cookies,$location){

	function incrementDate(givenDate,mins){
		return new Date(givenDate.getTime() + mins*60000);
	}
	
	function addCookie($cookies,auth,data){
		$cookies.put('auth',auth,{expires:incrementDate(new Date(), 1)});
		$cookies.put('usr',JSON.stringify(data),{expires:incrementDate(new Date(), 1)});
	}
	
	this.login = function($scope,$rootScope,$location){
		$http({
			method : 'POST',
			url : 'rest/authentication/login',
			data : {
				username : $scope.username,
				password : $scope.password
			}
		}).success(function(data,status){
			addCookie($cookies,true,data);
			$rootScope.usr = data;
			$rootScope.authenticated = true;
			$location.path('/home');
		}).error(function(data,status){
			$rootScope.authenticated = false;
			$scope.success = false;
			$scope.message = data;
		});
	}
	
	this.create = function($scope){
		$http({
			method : 'POST',
			url : 'rest/authentication/create',
			data : {
				username : $scope.username,
				password : $scope.password,
				name : $scope.name,
				teamName : $scope.teamName
			}
		}).success(function(data,status){
			$scope.message = 'Created Successfully!';
			$scope.success = true;
		}).error(function(data,status){
			$scope.message = data.message;
			$scope.success = false;
		});
	}
	
	this.validateCookie = function($rootScope,page){
		if($rootScope.authenticated == undefined){
			$rootScope.authenticated = $cookies.get('auth');
			if($rootScope.authenticated){
				$rootScope.usr = JSON.parse($cookies.get('usr'));
				$location.path(page);
			}else{
				$location.path('/login');
			}
		}
	}
	
}])