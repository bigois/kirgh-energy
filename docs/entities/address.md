---
title: Address
layout: default
nav_order: 2
parent: Entities
---

# Address
The address entity identifies a specific location, (e.g.: residence, building, or company) and must be associated with an already created user.
{: .fs-6 .fw-300 }

## Attributes table

| Attribute       | Type   | Description                                | Required |
|:----------------|:-------|:-------------------------------------------|:---------|
| zipCode         | String | Street ZIP code                            | Yes      |
| street          | String | Street or avenue                           | Yes      |
| number          | String | Numerical addresses assigned to properties | Yes      |
| city            | String | City                                       | Yes      |
| state           | String | State                                      | Yes      |
| parentId        | String | UUID from parent user                      | Yes      |

## Supported HTTP methods

<span class="fs-5 lh-default">
GET
</span>
{: .label .label-green }

<span class="fs-5 lh-default">
POST
</span>
{: .label .label-green }

<span class="fs-5 lh-default">
PUT
</span>
{: .label .label-green }

<span class="fs-5 lh-default">
DELETE
</span>
{: .label .label-green }

## Request examples

Simple address creation linked to an existing user (parent or not):

```json
{
    "street": "Rua America Paulista",
    "zipCode": "08421520",
    "number": 185,
    "city": "São Paulo",
    "state": "SP",
    "parentId": "6f007644-5bdf-4483-bf42-fb7412f66a45"
}
```

---

**GET ALL**

Get all addresses

**Example Request**

```url
curl -X GET 'https://kirgh-energy.up.railway.app/api/v1/addresses'
```

**Example Response**

```json
{
  "content": [
    {
      "id": "26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb",
      "zipCode": "03701010",
      "street": "Avenida Gabriela Mistral",
      "number": "670",
      "city": "São Paulo",
      "state": "SP"
    },
    {
      "id": "db403e6d-4023-46ba-95cb-c09001ab37a2",
      "zipCode": "03654010",
      "street": "Rua Alicante",
      "number": "966",
      "city": "São Paulo",
      "state": "SP"
    },
    {
      "id": "da3b763e-ac55-40ed-ba59-5c8a792aec63",
      "zipCode": "03642000",
      "street": "Rua José Fláviol",
      "number": "268",
      "city": "São Paulo",
      "state": "SP"
    }
  ],
  "pageable": {
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 20,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 1,
  "totalElements": 3,
  "last": true,
  "size": 20,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "numberOfElements": 3,
  "first": true,
  "empty": false
}
```
---

**GET BY ID**

Get the addresses by ID

**Example Request**

```url
curl -X GET 'https://kirgh-energy.up.railway.app/api/v1/addresses/26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb'
```

**Example Response**

```json
{
  "id": "26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb",
  "zipCode": "03701010",
  "street": "Avenida Gabriela Mistral",
  "number": "670",
  "city": "São Paulo",
  "state": "SP"
}
```
---

**GET all appliances of the addresses**

Get the address and all associated appliances

**Example Request**

```url
curl -X GET 'https://kirgh-energy.up.railway.app/api/v1/addresses/26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb/appliances'
```

**Example Response**

```json
{
  "addressData": {
    "id": "26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb",
    "zipCode": "03701010",
    "street": "Avenida Gabriela Mistral",
    "number": "670",
    "city": "São Paulo",
    "state": "SP"
  },
  "appliances": [
    {
      "id": "0415be05-b5e9-49b1-a51b-60ac820fb2e6",
      "name": "Fogão",
      "brand": "Atlas",
      "model": "Mônaco",
      "power": "V110"
    }
  ]
}
```
---

#### GET with Pagination and Filters

**PAGE AND SIZE**

Applying the parameters **page=0** and **size=2** results in returning the page with index 1 containing 2 records if they exist.

**Example Request**

```url
curl -X GET 'https://kirgh-energy.up.railway.app/api/v1/addresses?page=0&size=2'
```

**Example Response**

```json
{
  "content": [
    {
      "id": "26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb",
      "zipCode": "03701010",
      "street": "Avenida Gabriela Mistral",
      "number": "670",
      "city": "São Paulo",
      "state": "SP"
    },
    {
      "id": "db403e6d-4023-46ba-95cb-c09001ab37a2",
      "zipCode": "03654010",
      "street": "Rua Alicante",
      "number": "966",
      "city": "São Paulo",
      "state": "SP"
    }
  ],
  "pageable": {
    "sort": {
      "empty": true,
      "unsorted": true,
      "sorted": false
    },
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 2,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 2,
  "totalElements": 3,
  "last": false,
  "size": 2,
  "number": 0,
  "sort": {
    "empty": true,
    "unsorted": true,
    "sorted": false
  },
  "numberOfElements": 2,
  "first": true,
  "empty": false
}
```
---
**SORT**

Applying the **sort** parameter with a value for sorting, in our case "street", results in returning records sorted in alphabetical order by street.

**Example Request**

```url
curl -X GET 'https://kirgh-energy.up.railway.app/api/v1/addresses?sort=street'
```

**Example Response**

```json
{
  "content": [
    {
      "id": "26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb",
      "zipCode": "03701010",
      "street": "Avenida Gabriela Mistral",
      "number": "670",
      "city": "São Paulo",
      "state": "SP"
    },
    {
      "id": "db403e6d-4023-46ba-95cb-c09001ab37a2",
      "zipCode": "03654010",
      "street": "Rua Alicante",
      "number": "966",
      "city": "São Paulo",
      "state": "SP"
    },
    {
      "id": "da3b763e-ac55-40ed-ba59-5c8a792aec63",
      "zipCode": "03642000",
      "street": "Rua José Fláviol",
      "number": "268",
      "city": "São Paulo",
      "state": "SP"
    }
  ],
  "pageable": {
    "sort": {
      "empty": false,
      "unsorted": false,
      "sorted": true
    },
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 20,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 1,
  "totalElements": 3,
  "last": true,
  "size": 20,
  "number": 0,
  "sort": {
    "empty": false,
    "unsorted": false,
    "sorted": true
  },
  "numberOfElements": 3,
  "first": true,
  "empty": false
}
```
---

**ASC**

Applying the **asc** parameter in conjunction with the **sort** parameter results in returning records in ascending order, alphabetically sorted by street.

**Example Request**

```url
curl -X GET 'https://kirgh-energy.up.railway.app/api/v1/addresses?sort=street%2CASC'
```

**Example Response**

```json
{
  "content": [
    {
      "id": "26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb",
      "zipCode": "03701010",
      "street": "Avenida Gabriela Mistral",
      "number": "670",
      "city": "São Paulo",
      "state": "SP"
    },
    {
      "id": "db403e6d-4023-46ba-95cb-c09001ab37a2",
      "zipCode": "03654010",
      "street": "Rua Alicante",
      "number": "966",
      "city": "São Paulo",
      "state": "SP"
    },
    {
      "id": "da3b763e-ac55-40ed-ba59-5c8a792aec63",
      "zipCode": "03642000",
      "street": "Rua José Fláviol",
      "number": "268",
      "city": "São Paulo",
      "state": "SP"
    }
  ],
  "pageable": {
    "sort": {
      "empty": false,
      "unsorted": false,
      "sorted": true
    },
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 20,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 1,
  "totalElements": 3,
  "last": true,
  "size": 20,
  "number": 0,
  "sort": {
    "empty": false,
    "unsorted": false,
    "sorted": true
  },
  "numberOfElements": 3,
  "first": true,
  "empty": false
}
```
---

**DESC**

Applying the **desc** parameter in conjunction with the **sort** parameter results in returning records in descending order, alphabetically sorted by name

**Example Request**

```url
curl -X GET 'https://kirgh-energy.up.railway.app/api/v1/addresses?sort=street%2CDESC'
```

**Example Response**

```json
{
  "content": [
    {
      "id": "da3b763e-ac55-40ed-ba59-5c8a792aec63",
      "zipCode": "03642000",
      "street": "Rua José Fláviol",
      "number": "268",
      "city": "São Paulo",
      "state": "SP"
    },
    {
      "id": "db403e6d-4023-46ba-95cb-c09001ab37a2",
      "zipCode": "03654010",
      "street": "Rua Alicante",
      "number": "966",
      "city": "São Paulo",
      "state": "SP"
    },
    {
      "id": "26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb",
      "zipCode": "03701010",
      "street": "Avenida Gabriela Mistral",
      "number": "670",
      "city": "São Paulo",
      "state": "SP"
    }
  ],
  "pageable": {
    "sort": {
      "empty": false,
      "unsorted": false,
      "sorted": true
    },
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 20,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 1,
  "totalElements": 3,
  "last": true,
  "size": 20,
  "number": 0,
  "sort": {
    "empty": false,
    "unsorted": false,
    "sorted": true
  },
  "numberOfElements": 3,
  "first": true,
  "empty": false
}
```
---

**ALL RESOURCES COMBINED**

Applying a combination of parameters, as demonstrated in our example, we use **page**, **size**, **sort**, and **desc**. The result of this request will be page 0, containing 1 records, which are sorted in descending order based on the street, following the alphabetical sequence.

**Example Request**

```url
curl -X GET 'https://kirgh-energy.up.railway.app/api/v1/users?street=Rua&page=0&size=1&sort=street%2CDESC'
```

**Example Response**

```json
{
  "content": [
    {
      "id": "da3b763e-ac55-40ed-ba59-5c8a792aec63",
      "zipCode": "03642000",
      "street": "Rua José Fláviol",
      "number": "268",
      "city": "São Paulo",
      "state": "SP"
    }
  ],
  "pageable": {
    "sort": {
      "empty": false,
      "unsorted": false,
      "sorted": true
    },
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 1,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 2,
  "totalElements": 2,
  "last": false,
  "size": 1,
  "number": 0,
  "sort": {
    "empty": false,
    "unsorted": false,
    "sorted": true
  },
  "numberOfElements": 1,
  "first": true,
  "empty": false
}
```
---

<span class="fs-5 lh-default">
PUT
</span>
{: .label .label-green }

Updated fields of street and city for the address.

**Example Request**

```url
curl -X PUT 'https://kirgh-energy.up.railway.app/api/v1/addresses/26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb' \
--header 'Content-Type: application/json' \
--data-raw '{
    "street": "Rual cinco Abril",
    "city": "Sorocaba"
}'z
```

**Example Response**

```json
{
    "id": "26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb",
    "zipCode": "11750000",
    "street": "Rual cinco Abril",
    "number": "70",
    "city": "Sorocaba",
    "state": "RJ"
}
```
---
<span class="fs-5 lh-default">
DELETE
</span>
{: .label .label-green }

To delete a address, you need to pass the ID as a parameter. Example:

**Example Request**

```url
curl -X DEL 'https://kirgh-energy.up.railway.app/api/v1/addresses/db403e6d-4023-46ba-95cb-c09001ab37a2'
```

**Example Response**

```json
{
    "message": "address successfully deleted"
}
```
---

## Diagrams

![diagram-address.png](https://github.com/bigois/kirgh-energy/blob/main/docs/images/diagram-address.png?raw=true)
