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

	function getCookie(name){
	    return $cookies.getObject(name);
	}

	this.getUser = function(){
	    return getCookie('usr');
	}

	this.updateProfile = function(scope){
	    var profile = scope.profile;
	    $http({
	        method : "PUT",
	        url : "rest/user/"+profile.username,
	        data : {
	            password : profile.password,
	            name     : profile.name
	        }
	    }).then(function(response,status){
	        scope.success = true;
	        scope.message = "Details updated successfully!";
	    },function(response,status){
            scope.success = false;
            scope.message = "Something went wrong !";
	    });
	}

	this.login = function(username,password,$scope,$rootScope,$location,adminPasscode){
		$http({
			method : 'POST',
			url : 'rest/authentication/login',
			data : {
				username : username,
				password : password,
				adminPasscode : adminPasscode!=undefined?window.btoa(adminPasscode):null
			}
		}).then(function(response,status){
			addCookie($cookies,true,response.data);
			$rootScope.usr = response.data;
			$rootScope.authenticated = true;
			if(response.data.adminToken!=null){
				$rootScope.admin = true;
			}
			$location.path('/home');
		},function(errorResponse,status){
			$rootScope.authenticated = false;
			$scope.success = false;
			$scope.message = errorResponse.data;
		});
	}
	
	this.logout = function(){
		removeCookies();
		$location.path('/');
	}
	
	this.create = function($scope){
	    var user = $scope.user;
		$http({
			method : 'POST',
			url : 'rest/authentication/create',
			data : {
				username : user.username,
				password : user.password,
				name : user.name,
				teamName : user.teamName
			}
		}).then(function(response,status){
			$scope.message = 'Created Successfully!';
			$scope.success = true;
		},function(response,status){
			$scope.message = response.data.message;
			$scope.success = false;
		});
	}
	
	this.validateCookie = function($rootScope,page){
		if($cookies.get('auth') == undefined){
			$rootScope.authenticated = false;
			$location.path('/');
		}else{
			$rootScope.authenticated = $cookies.get('auth');
			if($rootScope.authenticated){
				$rootScope.usr = JSON.parse($cookies.get('usr'));
				$location.path(page);
			}else{
				$location.path('/');
			}
		}
	}
	
}])