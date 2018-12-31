Scenario:
Given a chrometest with testcaseName firstTest
And open Netstorm ProductUI for http://10.10.30.106
When login with username aditya and password cavisson for TestLogin
Then home page login name should be aditya for casename LoginStepValidation
Given create a scenario with name test_Biswajit_Newdfghjfghj4 project default subproject default casename CreateScenarioTest
And add group with groupname G1 , script hpd_tours , project default , subproject default , casename AddGroup
Then quit chrometest
