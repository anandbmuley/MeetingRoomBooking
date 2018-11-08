*** Settings ***
Library           Selenium2Library
Library           Screenshot

*** Test Cases ***
CreateTeam
    Open Browser    http://localhost:8091/MeetingRoomBooking/#/login    chrome
    Maximize Browser Window
    Click Link    create-team-link
    Screenshot.Set Screenshot Directory    /home/cts1/projects/personal/MeetingRoomBooking/functional-test
    Take Screenshot    name=Create Team Page
    Input Text    input-team-name    Avengers
    Click Button    create-team-btn
    Take Screenshot    name=Team Creation
    Close All Browsers
