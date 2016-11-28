controllers.controller('ManageProfileController',
                ['$scope','$rootScope','AuthenticationService',
                function($scope,$rootScope,authenticationService){

    $scope.pageTitle = 'Manage Profile';
    $scope.profile = authenticationService.getUser();

    authenticationService.validateCookie($rootScope,'/manageprofile');

    $scope.updateProfile = function(){
        authenticationService.updateProfile($scope);
    }

}]);