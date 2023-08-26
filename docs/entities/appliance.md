---
title: Appliance
layout: default
nav_order: 3
parent: Entities
---

# Appliance

The appliance entity represents an electrical device used in residences, buildings, or companies, to facilitate daily tasks.
This entity must be associated with an already created address.
{: .fs-6 .fw-300 }

## Attributes table

| Attribute | Type   | Description                                   | Required |
|:----------|:-------|:----------------------------------------------|:---------|
| name      | String | Name of appliance                             | Yes      |
| brand     | String | Street or avenue                              | Yes      |
| model     | String | Numbers and letters to identify an appliance  | Yes      |
| power     | String | Electric tension or potential difference unit | Yes      |
| addressId | String | UUID from address Id                          | Yes      |

## Supported HTTP methods

<span class="fs-5 lh-default">
POST
</span>
{: .label .label-green }

## Request examples

Simple appliance creation linked to an existing address:

```json
{
    "name": "Air conditioner",
    "brand": "Samsung",
    "model": "AR415",
    "power": "V220",
    "addressId": "26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb"
}
```

###
<span class="fs-5 lh-default">
GET
</span>
{: .label .label-green }

## Request examples

### GET

Get all appliance:

```url
curl -X https://kirgh-energy.up.railway.app/api/v1/appliance
```

If filters are required, follow the template:

```url
curl -X https://kirgh-energy.up.railway.app/api/v1/appliance?page=0&size=2&sort=name,asc
```

### GET bye ID

Get the appliance by ID:

```url
curl -X https://kirgh-energy.up.railway.app/api/v1/appliance/a818055c-24db-43a4-a6fc-7f02808fe1bb
```

##
<span class="fs-5 lh-default">
PUT
</span>
{: .label .label-green }

## Request examples

Name and brand fields of the appliance updated.

```json
{
    "name": "Ar Condicionado",
    "brand": "Samsung"
}
```

##
<span class="fs-5 lh-default">
DELETE
</span>
{: .label .label-green }

## Request examples

Delete an appliance using the provided ID:

```url
curl -X https://kirgh-energy.up.railway.app/api/v1/appliance/a818055c-24db-43a4-a6fc-7f02808fe1bb
```

## Diagrams

![diagram-appliance.png](https://github.com/bigois/kirgh-energy/blob/main/docs/images/diagram-appliance.png?raw=true)
