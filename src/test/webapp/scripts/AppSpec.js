describe('App Test Suite',function(){

    var route, $rootScope, $location, $httpBackend;

      beforeEach(function(){
        module('MeetingRoomUI');
      });

    inject(function($injector,$route,$controller){
       $route = $injector.get('$route');
       $rootScope = $injector.get('$rootScope');
       $location = $injector.get('$location');
       $httpBackend = $injector.get('$httpBackend');
    });

    it('Should validate configs',function(){
        // App Name is defined
        expect(app.name).toBe('MeetingRoomUI');

        // Module dependencies are defined
        expect(app.requires.length).toBe(4);
        expect(app.requires.indexOf("ngRoute")).not.toBe(-1);
        expect(app.requires.indexOf("MRControllers")).not.toBe(-1);
        expect(app.requires.indexOf("ui.bootstrap")).not.toBe(-1);
        expect(app.requires.indexOf("ngCookies")).not.toBe(-1);

        // Controllers should be defined
        expect(controllers).toBeDefined();
    });

    it('Should validate routes',function(){

//        expect(route.routes['/'].controller).toBe('');

    });

});