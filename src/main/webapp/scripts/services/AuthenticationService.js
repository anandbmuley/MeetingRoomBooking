app.service('AuthenticationService',['$http','$cookies','$location',function($http,$cookies,$location){

	
	function incrementDate(givenDate,mins){
		return new Date(givenDate.getTime() + mins*60000);
	}
	
	function addCookie($cookies,auth,data){
		var timeInMins = data.cookieTimeout;
		$cookies.put('sesslimit',timeInMins,{expires:incrementDate(new Date(), timeInMins)});
		$cookies.put('loginTime',data.loginTime,{expires:incrementDate(new Date(), timeInMins)});
		$cookies.put('auth',auth,{expires:incrementDate(new Date(), timeInMins)});
		$cookies.put('usr',JSON.stringify(data),{expires:incrementDate(new Date(), timeInMins)});
	}
	
	function removeCookies(){
		$cookies.remove('sesslimit');
		$cookies.remove('loginTime');
		$cookies.remove('auth');
		$cookies.remove('usr');
	}
	
	this.login = function(username,password,$scope,$rootScope,$location){
		$http({
			method : 'POST',
			url : 'rest/authentication/login',
			data : {
				username : username,
				password : password
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
	
	this.logout = function(){
		removeCookies();
		$location.path('/login');
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
		if($cookies.get('auth') == undefined){
			$rootScope.authenticated = false;
			$location.path('/login');
		}else{
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