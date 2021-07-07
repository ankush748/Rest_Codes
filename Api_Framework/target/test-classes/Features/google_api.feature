Feature: Validate Add/Delete API implementation

Scenario Outline: Verify if Place is being successfully added using AddPlace API
Given Add Place Payload with "<name>" "<accuracy>" and "<phone>"
When user calls "AddPlaceAPI" with Post HTTP request
Then the API call returns success with status code 200 and scope value is "APP"

Examples: 
					|name      | accuracy | phone      |
					|new Street| 50       | 87825858555|
					|new Street1| 40       | 97425858655|
					|new Street3| 60       | 98526368555|
					|new Street4| 70       | 97325558555|
					|new Street5| 50       | 97595858555|
					