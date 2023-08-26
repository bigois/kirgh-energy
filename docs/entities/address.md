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
###
<span class="fs-5 lh-default">
GET
</span>
{: .label .label-green }

## Request examples

### GET

Get all addresses:

```url
curl -X https://kirgh-energy.up.railway.app/api/v1/addresses
```

### GET bye ID

Get the addresses by ID:

```url
curl -X https://kirgh-energy.up.railway.app/api/v1/addresses/26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb
```

### GET all appliances of the addresses

Get the address and all associated appliances:

```url
curl -X https://kirgh-energy.up.railway.app/api/v1/addresses/26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb/appliances
```
##
<span class="fs-5 lh-default">
PUT
</span>
{: .label .label-green }

## Request examples

Street and address number fields updated.

```json
{
    "street": "Avenida Gabriela Mistral",
    "number": "670"
}
```

##
<span class="fs-5 lh-default">
DELETE
</span>
{: .label .label-green }

## Request examples

Delete an address using the provided ID:

```url
curl -X https://kirgh-energy.up.railway.app/api/v1/addresses/26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb
```

## Diagrams

![diagram-address.png](https://github.com/bigois/kirgh-energy/blob/main/docs/images/diagram-address.png?raw=true)
