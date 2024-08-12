# Pruebas con postman para Customer

1.  Create Customer
Method: POST 
URL: http://localhost:8080/api/v1/customer/create
 JSON: 
 {
"identificationTypeEnum":  "CC",
"identificationNumber":  "1006069291",
"customerName":  "Camilo",
"customerSurname":  "Romero",
"customerEmail":  "jjjjjk@jjjj.com",
"dateOfBirth":  "2002-11-26"
}

2.  Update Customer
Method: PATCH 
URL: http://localhost:8080/api/v1/customer/update/1000381652
 JSON: 
{
"identificationTypeEnum":  "CC",
"identificationNumber":  "1006069291",
"customerName":  "Juan",
"customerSurname":  "Romero",
"customerEmail":  "jjjjj@jjjj.com",
"dateOfBirth":  "2002-11-26",
"productEntities":  [
		{
	"accountNumber":  "5312345678",
	"accountState":  "ACTIVE",
	"accountType":  "SAVINGS",
	"balance":  53000.00,
	"gmfExempt":  true
		}
	]
}

3.  Delete Customer
Method: DELETE 
URL: http://localhost:8080/api/v1/customer/delete/1006069291

# Pruebas con postman para Product

1.  Create Account
Method: POST 
URL: http://localhost:8080/api/v1/product/createAccount
 JSON: 
{
"accountType":  "SAVINGS",
"balance":  5000.00,
"gmfExempt":  true,
"customerId":  1
}

2. Update AccountState
Method: PATCH
URL: http://localhost:8080/api/v1/product/updateAccountState
 JSON: 
{
"accountNumber":  "5356525237",
"accountState":  "INACTIVE"
}

3. account Canceled
Method: PATCH
URL: http://localhost:8080/api/v1/product/accountCanceled
 JSON: 
{
"accountNumber":  "5356525237",
"accountState":  "CANCELLED"
}

4. consign
Method: POST
URL: http://localhost:8080/api/v1/product/consign/5356525237?balance=6000.00

5. consign
Method: POST
URL: http://localhost:8080/api/v1/product/consign/5356525237?balance=6000.00

6. withdraw
Method: POST
URL: http://localhost:8080/api/v1/product/withdraw/5356525237?balance=7000.00

7. transfer
Method: POST
URL: http://localhost:8080/api/v1/product/transfer?accountOrigin=3330275160&accountDestination=5356525237&balance=7000.00
