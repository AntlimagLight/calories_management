#### get All Meals
`curl -s http://localhost:8080/topjava_war_exploded/rest/profile/meals`

#### get Meals 100003
`curl -s http://localhost:8080/topjava_war_exploded/rest/profile/meals/100003`

#### filter Meals
`curl -s "http://localhost:8080/topjava_war_exploded/rest/profile/meals/filter?startDate=2020-01-30&startTime=07:00:00&endDate=2020-01-31&endTime=14:00:00"`

#### get Meals not found
`curl -s -v http://localhost:8080/topjava_war_exploded/rest/profile/meals/99999`

#### update Meals 100003
`curl -s -X PUT -d '{"dateTime":"2015-05-30T07:00", "description":"Updated food", "calories":600}' -H 'Content-Type: application/json' http://localhost:8080/topjava_war_exploded/rest/profile/meals/100003`

#### delete Meals 100003
`curl -s -X DELETE http://localhost:8080/topjava_war_exploded/rest/profile/meals/100003`

#### create Meals
`curl -s -X POST -d '{"dateTime":"2015-06-01T12:00","description":"New food","calories":500}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/topjava_war_exploded/rest/profile/meals`

