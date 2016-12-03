*** Settings ***
Library           Selenium2Library

*** Test Cases ***
CreateTeam
    Open Browser    http://localhost:8090/MeetingRoomBooking/#/login    chrome
