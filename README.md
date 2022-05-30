# LoanStreet Development Project

A brief description of what this project does and who it's for

https://github.com/loanstreet-usa/api-interview
## Tech Stack

**Client:** N/A

**Server:** Java, Spring Boot


## Demo

https://loanstreetapi.azurewebsites.net/loans


## API Reference

#### Add a loan

```bash
  curl -X POST 'https://loanstreetapi.azurewebsites.net/loans' -H 'Content-Type: application/json' -d '{
    \"paidAmt\": \"123456789.00\",
    \"interestRate\": \"1.20\",
    \"monthsLength\": 12,
    \"monthPayAmt\": \"987654321.99\"
}'
```

#### Get all loans

```bash
  curl -X GET 'https://loanstreetapi.azurewebsites.net/loans'
```

| Parameter | Type     | Body (raw)                |
| :-------- | :------- | :------------------------- |
| N/A | `JSON` | None |

#### Get a loan

```bash
  curl -X GET 'https://loanstreetapi.azurewebsites.net/loans/{id}'
```

| Parameter | Type     | Example                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `JSON` | 'https://loanstreetapi.azurewebsites.net/loans/1' |

#### Update a loan

```bash
  curl -X PUT 'https://loanstreetapi.azurewebsites.net/loans/{id}' -H 'Content-Type: application/json' -d '{
	\"paidAmt\": \"0.00\",
    \"interestRate\": \"0.01\",
    \"monthsLength\": 1,
    \"monthPayAmt\": \"0.00\"
}'
```

| Parameter | Type     | Example                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `JSON` | 'https://loanstreetapi.azurewebsites.net/loans/1' |



## Support

For any questions, email ytrail@gmail.com
