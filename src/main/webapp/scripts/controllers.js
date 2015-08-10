var meetingRoomUI = angular.module('MeetingRoomUI', []);

meetingRoomUI.controller('meetingcontroller1', function ($scope) {
  $scope.teams = [
    {'name': 'Nexus S',
     'snippet': 'Fast just got faster with Nexus S.'},
    {'name': 'Motorola XOOM™ with Wi-Fi',
     'snippet': 'The Next, Next Generation tablet.'},
    {'name': 'MOTOROLA XOOM™',
     'snippet': 'The Next, Next Generation tablet.'}
  ];
});