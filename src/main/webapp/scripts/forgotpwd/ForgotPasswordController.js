controllers.controller('ForgotPasswordController',
            ['$scope','ForgotPasswordService',
            function($scope,forgotPasswordService){

    $scope.forgotPwd = {};

    $scope.generatePassword = function(){
        var forgotPwd = $scope.forgotPwd;
        if(forgotPwd.username==undefined || forgotPwd.username == ""){
            forgotPwd.message = "Please provide username";
        }else{
            forgotPasswordService.generatePassword($scope.forgotPwd);
        }
    }

}]);