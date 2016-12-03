app.service('ForgotPasswordService',['$http',function($http){

    var self = this;

    this.generatePassword = function(forgotPwd){
        $http({
            url : 'rest/user/'+forgotPwd.username+'/resetpassword',
            method : 'PUT',
            headers : {
                'Content-type' : 'application/json'
            }
        }).success(function(data,status){
            forgotPwd.success = true;
            forgotPwd.message = "Please use password '"+data.newPassword+"' to login";
        }).error(function(data,status){
            forgotPwd.success = false;
            forgotPwd.message = data;
        });
    }

}]);