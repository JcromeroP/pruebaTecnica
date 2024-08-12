##Pruebas con postman para Customer
##Create Customer
POST:
http://localhost:8080/api/v1/customer/create
JSON:
{
    "identificationTypeEnum": "CC",
    "identificationNumber": "1000381652",
    "customerName": "Camilo",
    "customerSurname": "Romeroo",
    "customerEmail": "jjjjjk@jjjj.com",
    "dateOfBirth": "2002-11-27"
}

##Update Customer
PATCH:
http://localhost:8080/api/v1/customer/update/1000381652
JSON:
{
      "identificationTypeEnum": "CC",
      "identificationNumber": "1000381652",
      "customerName": "Juan",
      "customerSurname": "Romero",
      "customerEmail": "jjjjj@jjjj.com",
      "dateOfBirth": "2002-11-26",
      "productEntities": [
        {
          "accountNumber": "5312345678",
          "accountState": "ACTIVE",
          "accountType": "SAVINGS",
          "balance": 53000.00,
          "gmfExempt": true
        }
      ]
    }
##Delete Customer
DELETE:
http://localhost:8080/api/v1/customer/delete/1000381653

##Pruebas con postman para Customer
##Create Customer
POST:
http://localhost:8080/api/v1/product/createAccount
JSON:
{
  "accountType": "SAVINGS",
  "balance": 5000.00,
  "gmfExempt": true,
  "customerId": 2
}
##Update AccountState
PATCH:
http://localhost:8080/api/v1/product/updateAccountState
JSON:
{
  "accountNumber": "5356525237",
  "accountState": "INACTIVE"
}

##Account Canceled
PATCH:
http://localhost:8080/api/v1/product/accountCanceled
JSON:
{
  "accountNumber": "5356525237",
  "accountState": "CANCELLED"
}

##Consign
POST:
http://localhost:8080/api/v1/product/consign/5356525237?balance=6000.00

##Withdraw
POST:
http://localhost:8080/api/v1/product/withdraw/5356525237?balance=7000.00

##Transfer
POST:
http://localhost:8080/api/v1/product/transfer?accountOrigin=3330275160&accountDestination=5356525237&balance=7000.00
