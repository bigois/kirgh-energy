---
title: User
layout: default
nav_order: 1
parent: Entities
---

# User

The user represents the main entity of the application. It can be associated with another or an independent user. This entity is mandatory for the existence of the others, since they are direct or indirectly associated with the principal.
{: .fs-6 .fw-300 }

## Attributes table

| Attribute             | Type   | Description           | Required |
|:----------------------|:-------|:----------------------|:---------|
| cpf                   | String | User's CPF            | Yes      |
| name                  | String | Full name             | Yes      |
| email                 | String | Valid e-mail          | Yes      |
| birthDate             | Date   | Date of birth         | Yes      |
| gender                | String | User gender           | Yes      |
| relation/ownerId      | String | UUID from parent user | No       |
| relation/relationType | String | Relation description  | No       |

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

### Request examples

#### Parent user

User without relationship to another user: 

```json
{
    "name": "João Ferreira da Silva",
    "birthDate": "2001-11-10",
    "gender": "M",
    "cpf": "39917627065",
    "email": "joao.ferreira@gmail.com"
}
```

#### Child user

User related to other existing one (makes possible to extend parent user's addresses and appliances):

```json
{
    "name": "Victória Oliveira da Silva",
    "birthDate": "2008-11-10",
    "gender": "F",
    "cpf": "54793753000",
    "email": "victoria.oliviera@outlook.com",
    "relation": {
        "ownerId": "26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb", 
        "relationType": "Daughter"
    }
}
```

---
<span class="fs-5 lh-default">
GET
</span>
{: .label .label-green }

**GET ALL**

Get all users

**Example Request**

```url
curl -X GET 'https://kirgh-energy.up.railway.app/api/v1/users'
```

**Example Response**

```json
{
  "content": [
    {
      "id": "6f007644-5bdf-4483-bf42-fb7412f66a45",
      "cpf": "29081928619",
      "name": "Renata Luzia Francisca Porto",
      "email": "renataluziaporto@asconinternet.com.br",
      "birthDate": "1957-04-08T03:00:00.000+00:00",
      "gender": "F"
    },
    {
      "id": "ab8ea442-d9bb-466d-8d47-3853091d545d",
      "cpf": "25276887560",
      "name": "Caroline Larissa Assunção",
      "email": "caroline_larissa_assuncao@infonet.com.br",
      "birthDate": "1983-02-09T03:00:00.000+00:00",
      "gender": "F"
    },
    {
      "id": "9072068f-2a55-4db0-8578-e4d73fc4f668",
      "cpf": "51032295287",
      "name": "Isabelly Brenda Rebeca Lima",
      "email": "isabellybrendalima@munhozengenharia.com.br",
      "birthDate": "1995-01-07T02:00:00.000+00:00",
      "gender": "F"
    }
  ],
  "pageable": {
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "pageSize": 3,
    "pageNumber": 0,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalElements": 15,
  "totalPages": 1,
  "size": 3,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "first": true,
  "numberOfElements": 15,
  "empty": false
}
```
---

**GET BY ID**

Get the user by ID

**Example Request**

```url
curl -X GET 'https://kirgh-energy.up.railway.app/api/v1/users/6f007644-5bdf-4483-bf42-fb7412f66a45'
```

**Example Response**

```json
{
  "content": [
    {
      "id": "6f007644-5bdf-4483-bf42-fb7412f66a45",
      "cpf": "29081928619",
      "name": "Renata Luzia Francisca Porto",
      "email": "renataluziaporto@asconinternet.com.br",
      "birthDate": "1957-04-08T03:00:00.000+00:00",
      "gender": "F"
    }
  ],
  "pageable": {
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "pageSize": 1,
    "pageNumber": 0,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalElements": 15,
  "totalPages": 1,
  "size": 1,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "first": true,
  "numberOfElements": 15,
  "empty": false
}
```
---

**GET user Address**

Get all users based on the provided ID, and the associated address if it exists

**Example Request**

```url
curl -X GET 'https://kirgh-energy.up.railway.app/api/v1/users/6d25d927-0199-4123-ba73-877d161bf98e/addresses'
```

**Example Response**

```json
{
  "userData": {
    "id": "6d25d927-0199-4123-ba73-877d161bf98e",
    "cpf": "29436331002",
    "name": "Diego Ruan Viana",
    "email": "diego.ruan.viana@bhcervejas.com.br",
    "birthDate": "1983-04-09T03:00:00.000+00:00",
    "gender": "M"
  },
  "userRelation": [
    {
      "id": "6f007644-5bdf-4483-bf42-fb7412f66a45",
      "relationType": "Son"
    }
  ],
  "addresses": [
    {
      "addressData": {
        "id": "26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb",
        "zipCode": "03701010",
        "street": "Avenida Gabriela Mistral",
        "number": "670",
        "city": "São Paulo",
        "state": "SP"
      },
      "appliances": [}
      ]
    }
  ]
}
```
---

#### GET with Pagination and Filters

**PAGE AND SIZE**

Applying the parameters **page=0** and **size=3** results in returning the page with index 1 containing 3 records if they exist.

**Example Request**

```url
curl -X GET 'https://kirgh-energy.up.railway.app/api/v1/users?page=0&size=3'
```

**Example Response**

```json
{
  "content": [
        {
            "id": "6f007644-5bdf-4483-bf42-fb7412f66a45",
            "cpf": "29081928619",
            "name": "Renata Luzia Francisca Porto",
            "email": "renataluziaporto@asconinternet.com.br",
            "birthDate": "1957-04-08T03:00:00.000+00:00",
            "gender": "F"
        },
        {
            "id": "ab8ea442-d9bb-466d-8d47-3853091d545d",
            "cpf": "25276887560",
            "name": "Caroline Larissa Assunção",
            "email": "caroline_larissa_assuncao@infonet.com.br",
            "birthDate": "1983-02-09T03:00:00.000+00:00",
            "gender": "F"
        },
        {
            "id": "67459848-3af5-4c99-9276-543c331adcc1",
            "cpf": "76290569236",
            "name": "Elza Flávia da Conceição",
            "email": "elza_daconceicao@flir.com.br",
            "birthDate": "2003-04-19T03:00:00.000+00:00",
            "gender": "F"
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
        "pageSize": 3,
        "unpaged": false,
        "paged": true
    },
    "last": false,
    "totalPages": 5,
    "totalElements": 15,
    "size": 3,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 3,
    "empty": false
}
```
---
**SORT**

Applying the **sort** parameter with a value for sorting, in our case "name", results in returning records sorted in alphabetical order by name.

**Example Request**

```url
curl -X GET 'https://kirgh-energy.up.railway.app/api/v1/users?sort=name'
```

**Example Response**

```json
{
  "content": [
    {
      "id": "f9fde553-945f-483a-b38b-4e3c9968112c",
      "cpf": "05088017294",
      "name": "Alice Brenda Carvalho",
      "email": "alice_carvalho@vivalle.com.br",
      "birthDate": "1965-03-14T02:00:00.000+00:00",
      "gender": "F"
    },
    {
      "id": "ab8ea442-d9bb-466d-8d47-3853091d545d",
      "cpf": "25276887560",
      "name": "Caroline Larissa Assunção",
      "email": "caroline_larissa_assuncao@infonet.com.br",
      "birthDate": "1983-02-09T03:00:00.000+00:00",
      "gender": "F"
    },
    {
      "id": "6d25d927-0199-4123-ba73-877d161bf98e",
      "cpf": "29436331002",
      "name": "Diego Ruan Viana",
      "email": "diego.ruan.viana@bhcervejas.com.br",
      "birthDate": "1983-04-09T03:00:00.000+00:00",
      "gender": "M"
    }
  ],
  "pageable": {
    "sort": {
      "empty": false,
      "sorted": true,
      "unsorted": false
    },
    "offset": 0,
    "pageSize": 20,
    "pageNumber": 0,
    "unpaged": false,
    "paged": true
  },
  "last": true,
  "totalElements": 15,
  "totalPages": 1,
  "size": 20,
  "number": 0,
  "sort": {
    "empty": false,
    "sorted": true,
    "unsorted": false
  },
  "first": true,
  "numberOfElements": 15,
  "empty": false
}
```
---

**ASC**

Applying the **asc** parameter in conjunction with the **sort** parameter results in returning records in ascending order, alphabetically sorted by name.

**Example Request**

```url
curl -X GET 'https://kirgh-energy.up.railway.app/api/v1/users?sort=name%2CASC'
```

**Example Response**

```json
{
  "content": [
    {
      "id": "f9fde553-945f-483a-b38b-4e3c9968112c",
      "cpf": "05088017294",
      "name": "Alice Brenda Carvalho",
      "email": "alice_carvalho@vivalle.com.br",
      "birthDate": "1965-03-14T02:00:00.000+00:00",
      "gender": "F"
    },
    {
      "id": "ab8ea442-d9bb-466d-8d47-3853091d545d",
      "cpf": "25276887560",
      "name": "Caroline Larissa Assunção",
      "email": "caroline_larissa_assuncao@infonet.com.br",
      "birthDate": "1983-02-09T03:00:00.000+00:00",
      "gender": "F"
    },
    {
      "id": "6d25d927-0199-4123-ba73-877d161bf98e",
      "cpf": "29436331002",
      "name": "Diego Ruan Viana",
      "email": "diego.ruan.viana@bhcervejas.com.br",
      "birthDate": "1983-04-09T03:00:00.000+00:00",
      "gender": "M"
    }
  ],
  "pageable": {
    "sort": {
      "empty": false,
      "sorted": true,
      "unsorted": false
    },
    "offset": 0,
    "pageSize": 20,
    "pageNumber": 0,
    "unpaged": false,
    "paged": true
  },
  "last": true,
  "totalElements": 15,
  "totalPages": 1,
  "size": 3,
  "number": 0,
  "sort": {
    "empty": false,
    "sorted": true,
    "unsorted": false
  },
  "first": true,
  "numberOfElements": 15,
  "empty": false
}
```
---

**DESC**

Applying the **desc** parameter in conjunction with the **sort** parameter results in returning records in descending order, alphabetically sorted by name

**Example Request**

```url
curl -X GET 'https://kirgh-energy.up.railway.app/api/v1/users?sort=name%2CDESC'
```

**Example Response**

```json
{
  "content": [
    {
      "id": "6d25d927-0199-4123-ba73-877d161bf98e",
      "cpf": "29436331002",
      "name": "Diego Ruan Viana",
      "email": "diego.ruan.viana@bhcervejas.com.br",
      "birthDate": "1983-04-09T03:00:00.000+00:00",
      "gender": "M"
    },
    {
      "id": "ab8ea442-d9bb-466d-8d47-3853091d545d",
      "cpf": "25276887560",
      "name": "Caroline Larissa Assunção",
      "email": "caroline_larissa_assuncao@infonet.com.br",
      "birthDate": "1983-02-09T03:00:00.000+00:00",
      "gender": "F"
    }
    {
      "id": "f9fde553-945f-483a-b38b-4e3c9968112c",
      "cpf": "05088017294",
      "name": "Alice Brenda Carvalho",
      "email": "alice_carvalho@vivalle.com.br",
      "birthDate": "1965-03-14T02:00:00.000+00:00",
      "gender": "F"
    }
  ],
  "pageable": {
    "sort": {
      "empty": false,
      "sorted": true,
      "unsorted": false
    },
    "offset": 0,
    "pageSize": 20,
    "pageNumber": 0,
    "unpaged": false,
    "paged": true
  },
  "last": true,
  "totalElements": 15,
  "totalPages": 1,
  "size": 20,
  "number": 0,
  "sort": {
    "empty": false,
    "sorted": true,
    "unsorted": false
  },
  "first": true,
  "numberOfElements": 15,
  "empty": false
}
```
---

**ALL RESOURCES COMBINED**

Applying a combination of parameters, as demonstrated in our example, we use **page**, **size**, **sort**, and **desc**. The result of this request will be page 2, containing 2 records, which are sorted in descending order based on the name, following the alphabetical sequence.

**Example Request**

```url
curl -X GET 'https://kirgh-energy.up.railway.app/api/v1/users?gender=F&page=2&size=2&sort=name%2CDESC'
```

**Example Response**

```json
{
  "content": [
    {
      "id": "67459848-3af5-4c99-9276-543c331adcc1",
      "cpf": "76290569236",
      "name": "Elza Flávia da Conceição",
      "email": "elza_daconceicao@flir.com.br",
      "birthDate": "2003-04-19T03:00:00.000+00:00",
      "gender": "F"
    },
    {
      "id": "ab8ea442-d9bb-466d-8d47-3853091d545d",
      "cpf": "25276887560",
      "name": "Caroline Larissa Assunção",
      "email": "caroline_larissa_assuncao@infonet.com.br",
      "birthDate": "1983-02-09T03:00:00.000+00:00",
      "gender": "F"
    }
  ],
  "pageable": {
    "sort": {
      "empty": false,
      "sorted": true,
      "unsorted": false
    },
    "offset": 4,
    "pageSize": 2,
    "pageNumber": 2,
    "unpaged": false,
    "paged": true
  },
  "last": false,
  "totalElements": 7,
  "totalPages": 4,
  "size": 2,
  "number": 2,
  "sort": {
    "empty": false,
    "sorted": true,
    "unsorted": false
  },
  "first": false,
  "numberOfElements": 2,
  "empty": false
}
```
---

<span class="fs-5 lh-default">
PUT
</span>
{: .label .label-green }

Updated fields of Name and Email for the user.

**Example Request**

```url
curl -X PUT 'https://kirgh-energy.up.railway.app/api/v1/users/6f007644-5bdf-4483-bf42-fb7412f66a45' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "João Ferreira da Silva",
    "email": "joao.ferreira@gmail.com"
}'z
```

**Example Response**

```json
{
    "id": "6f007644-5bdf-4483-bf42-fb7412f66a45",
    "cpf": "29436331002",
    "name": "João Ferreira da Silva",
    "email": "joao.ferreira@gmail.com",
    "birthDate": "1983-04-09T03:00:00.000+00:00",
    "gender": "M"
}
```
---
<span class="fs-5 lh-default">
DELETE
</span>
{: .label .label-green }

To delete a user, you need to pass the ID as a parameter. Example:

**Example Request**

```url
curl -X DEL 'https://kirgh-energy.up.railway.app/api/v1/users/d8535003-7f96-448e-9bbd-7d9026a696f6'
```

**Example Response**

```json
{
    "message": "user successfully deleted"
}
```
---

## Diagrams

![diagram-user.png](https://github.com/bigois/kirgh-energy/blob/main/docs/images/diagram-user.png?raw=true)
