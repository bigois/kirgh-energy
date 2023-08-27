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














---
<span class="fs-5 lh-default">
GET
</span>
{: .label .label-green }

**GET ALL**

Get all appliances

**Example Request**

```url
curl -X GET 'https://kirgh-energy.up.railway.app/api/v1/appliances'
```

**Example Response**

```json
{
  "content": [
    {
      "id": "a818055c-24db-43a4-a6fc-7f02808fe1bb",
      "name": "Ar Condicionado",
      "brand": "Samsung",
      "model": "AR12BVHZCWK",
      "power": "V110"
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
    "pageSize": 20,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 1,
  "totalElements": 1,
  "last": true,
  "size": 20,
  "number": 0,
  "sort": {
    "empty": true,
    "unsorted": true,
    "sorted": false
  },
  "numberOfElements": 1,
  "first": true,
  "empty": false
}
```
---

**GET BY ID**

Get the appliance by ID

**Example Request**

```url
curl -X GET 'https://kirgh-energy.up.railway.app/api/v1/appliances/a818055c-24db-43a4-a6fc-7f02808fe1bb'
```

**Example Response**

```json
{
  "id": "a818055c-24db-43a4-a6fc-7f02808fe1bb",
  "name": "Ar Condicionado",
  "brand": "Samsung",
  "model": "AR12BVHZCWK",
  "power": "V110"
}
```
---

#### GET with Pagination and Filters

**PAGE AND SIZE**

Applying the parameters **page=0** and **size=2** results in returning the page with index 1 containing 2 records if they exist.

**Example Request**

```url
curl -X GET 'https://kirgh-energy.up.railway.app/api/v1/appliances?page=0&size=2&sort=name,asc'
```

**Example Response**

```json
{
  "content": [
    {
      "id": "a818055c-24db-43a4-a6fc-7f02808fe1bb",
      "name": "Ar Condicionado",
      "brand": "Samsung",
      "model": "AR12BVHZCWK",
      "power": "V110"
    },
    {
      "id": "6dcd8370-35b0-49af-b738-b0484c776909",
      "name": "Geladeira",
      "brand": "Consul",
      "model": "FMA512",
      "power": "V110"
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
    "pageSize": 2,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 1,
  "totalElements": 2,
  "last": true,
  "size": 2,
  "number": 0,
  "sort": {
    "empty": false,
    "unsorted": false,
    "sorted": true
  },
  "numberOfElements": 2,
  "first": true,
  "empty": false
}
```
---
**SORT**

Applying the **sort** parameter with a value for sorting, in our case "brand", results in returning records sorted in alphabetical order by brand.

**Example Request**

```url
curl -X GET 'https://kirgh-energy.up.railway.app/api/v1/appliances?sort=brand'
```

**Example Response**

```json
{
  "content": [
    {
      "id": "6dcd8370-35b0-49af-b738-b0484c776909",
      "name": "Geladeira",
      "brand": "Consul",
      "model": "FMA512",
      "power": "V110"
    },
    {
      "id": "a818055c-24db-43a4-a6fc-7f02808fe1bb",
      "name": "Ar Condicionado",
      "brand": "Samsung",
      "model": "AR12BVHZCWK",
      "power": "V110"
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
  "totalElements": 2,
  "last": true,
  "size": 20,
  "number": 0,
  "sort": {
    "empty": false,
    "unsorted": false,
    "sorted": true
  },
  "numberOfElements": 2,
  "first": true,
  "empty": false
}
```
---

**ASC**

Applying the **asc** parameter in conjunction with the **sort** parameter results in returning records in ascending order, alphabetically sorted by brand.

**Example Request**

```url
curl -X GET 'https://kirgh-energy.up.railway.app/api/v1/appliances?sort=brand%2CASC'
```

**Example Response**

```json
{
  "content": [
    {
      "id": "7ac5630a-ac69-4df9-9d1e-3adf94a06e6b",
      "name": "Fogão",
      "brand": "Brastemp",
      "model": "JHTF11",
      "power": "V220"
    },
    {
      "id": "6dcd8370-35b0-49af-b738-b0484c776909",
      "name": "Geladeira",
      "brand": "Consul",
      "model": "FMA512",
      "power": "V110"
    },
    {
      "id": "64d8b99b-e8e9-478e-879b-798e7f87720d",
      "name": "Televisão 42",
      "brand": "LG",
      "model": "42",
      "power": "V110"
    },
    {
      "id": "a818055c-24db-43a4-a6fc-7f02808fe1bb",
      "name": "Ar Condicionado",
      "brand": "Samsung",
      "model": "AR12BVHZCWK",
      "power": "V110"
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
  "totalElements": 4,
  "last": true,
  "size": 20,
  "number": 0,
  "sort": {
    "empty": false,
    "unsorted": false,
    "sorted": true
  },
  "numberOfElements": 4,
  "first": true,
  "empty": false
}
```
---

**DESC**

Applying the **desc** parameter in conjunction with the **sort** parameter results in returning records in descending order, alphabetically sorted by brand

**Example Request**

```url
curl -X GET 'https://kirgh-energy.up.railway.app/api/v1/appliances?sort=brand%2CDESC'
```

**Example Response**

```json
{
  "content": [
    {
      "id": "a818055c-24db-43a4-a6fc-7f02808fe1bb",
      "name": "Ar Condicionado",
      "brand": "Samsung",
      "model": "AR12BVHZCWK",
      "power": "V110"
    },
    {
      "id": "64d8b99b-e8e9-478e-879b-798e7f87720d",
      "name": "Televisão 42",
      "brand": "LG",
      "model": "42",
      "power": "V110"
    },
    {
      "id": "6dcd8370-35b0-49af-b738-b0484c776909",
      "name": "Geladeira",
      "brand": "Consul",
      "model": "FMA512",
      "power": "V110"
    },
    {
      "id": "7ac5630a-ac69-4df9-9d1e-3adf94a06e6b",
      "name": "Fogão",
      "brand": "Brastemp",
      "model": "JHTF11",
      "power": "V220"
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
  "totalElements": 4,
  "last": true,
  "size": 20,
  "number": 0,
  "sort": {
    "empty": false,
    "unsorted": false,
    "sorted": true
  },
  "numberOfElements": 4,
  "first": true,
  "empty": false
}
```
---

**ALL RESOURCES COMBINED**

Applying a combination of parameters, as demonstrated in our example, we use **page**, **size**, **sort**, and **desc**. The result of this request will be page 0, containing 1 records, which are sorted in descending order based on the brand, following the alphabetical sequence.

**Example Request**

```url
curl -X GET 'https://kirgh-energy.up.railway.app/api/v1/appliances?street=Rua&page=0&size=1&sort=brand%2CDESC'
```

**Example Response**

```json
{
  "content": [
    {
      "id": "64d8b99b-e8e9-478e-879b-798e7f87720d",
      "name": "Televisão 42",
      "brand": "LG",
      "model": "42",
      "power": "V110"
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
  "totalPages": 1,
  "totalElements": 1,
  "last": true,
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

Updated fields of name and brand for the appliance.

**Example Request**

```url
curl -X PUT 'https://kirgh-energy.up.railway.app/api/v1/appliances/a818055c-24db-43a4-a6fc-7f02808fe1bb' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Ar condicionado inverter",
    "city": "LG"
}'z
```

**Example Response**

```json
{
  "id": "a818055c-24db-43a4-a6fc-7f02808fe1bb",
  "name": "Ar condicionado inverter",
  "brand": "LG",
  "model": "AR12BVHZCWK",
  "power": "V110"
}
```
---
<span class="fs-5 lh-default">
DELETE
</span>
{: .label .label-green }

To delete a appliance, you need to pass the ID as a parameter. Example:

**Example Request**

```url
curl -X DEL 'https://kirgh-energy.up.railway.app/api/v1/appliances/6dcd8370-35b0-49af-b738-b0484c776909'
```

**Example Response**

```json
{
    "message": "appliance successfully deleted"
}
```
---








## Diagrams

![diagram-appliance.png](https://github.com/bigois/kirgh-energy/blob/main/docs/images/diagram-appliance.png?raw=true)
