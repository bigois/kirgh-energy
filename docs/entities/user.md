---
title: User
layout: default
nav_order: 1
parent: Entities
---

# User

The user represents the main entity of the application. It can be associated with another or an independent user. This entity is mandatory for the existence of the others, since they are direct or indirectly associated with the principal.

## Attribute table

| Attribute             | Type   | Description           | Required |
|:----------------------|:-------|:----------------------|:---------|
| cpf                   | String | User's CPF            | Yes      |
| name                  | String | Full name             | Yes      |
| email                 | String | Valid e-mail          | Yes      |
| birthDate             | Date   | Date of birth         | Yes      |
| gender                | String | User gender           | Yes      |
| relation/ownerId      | String | UUID from parent user | No       |
| relation/relationType | String | Relation description  | No       |

## Supported methods

<span class="fs-5 lh-default">
POST
</span>
{: .label .label-green }

## Request examples

### Parent user

```json
{
    "name": "João Ferreira da Silva",
    "birthDate": "2001-11-10",
    "gender": "M",
    "cpf": "39917627065",
    "email": "joao.ferreira@gmail.com"
}
```

### Child user
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
## Diagrams

![img.png](../images/diagram-user.png)