describe('Team Service Spec Suite',function(){

    var teamService,httpBackend;
    var basePath = "http://localhost:8090/MeetingRoomBooking/rest/";

    beforeEach(module('MeetingRoomUI'));

    inject(function($injector,TeamService){
    		teamService = TeamService;
    		scope = {};
    		httpBackend = $injector.get('$httpBackend');
    });

    it('fetchTeamList - should fetch team list',function(){
        // GIVEN
        var teams = {};
        console.log("BADKEND : "+httpBackend);
        httpBackend.when('GET',basePath+'rest/team/list').respond(200,{});

        // WHEN
        teamService.fetchTeamList(teams);
        httpBackend.flush();

        // THEN
        expect(teams.data).toBeDefined();
    });

});