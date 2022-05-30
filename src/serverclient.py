import json
import requests

#Prints out the request, response code, & response body
def results(theResponse):
    print (theResponse.request.method + " " + theResponse.request.url)
    print("Response Code: " + str(theResponse.status_code))
    obj = json.loads(theResponse.text)
    jsonStr = json.dumps(obj, indent=2)
    print ("Response Body: " + jsonStr)

#Test the POST Endpoint
json_data = {
    'paidAmt': '123456789',
    'interestRate': '1.20',
    'monthsLength': 12,
    'monthPayAmt': '987654321.99',
}
response = requests.post('https://loanstreetapi.azurewebsites.net/loans', json=json_data)
results(response)
#Test the GET by id Endpoint
response = requests.get('https://loanstreetapi.azurewebsites.net/loans/1')
results(response)
#Test the POST Endpoint
json_data = {
    'paidAmt': '999999999.99',
    'interestRate': '3.99',
    'monthsLength': 36,
    'monthPayAmt': '0.50',
}
response = requests.post('https://loanstreetapi.azurewebsites.net/loans', json=json_data)
results(response)
#Test the POST Endpoint
json_data = {
    'paidAmt': '0.99',
    'interestRate': '5.09',
    'monthsLength': 36,
    'monthPayAmt': '999999999999.99',
}
response = requests.post('https://loanstreetapi.azurewebsites.net/loans', json=json_data)
results(response)
#Test the GET all loans in database Endpoint
response = requests.get('https://loanstreetapi.azurewebsites.net/loans')
results(response)
#Test the PUT Endpoint
json_data = {
    'paidAmt': '0.00',
    'interestRate': '0.01',
    'monthsLength': 1,
    'monthPayAmt': '0.00',
}
response = requests.put('https://loanstreetapi.azurewebsites.net/loans/3', json=json_data)
results(response)