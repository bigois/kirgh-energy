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
POST
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

## Diagrams

![diagram-address.png](https://github.com/bigois/kirgh-energy/blob/main/docs/images/diagram-address.png?raw=true)
