---
title: User
layout: default
nav_order: 1
parent: Entities
---

# User

The user represents the main entity of the application. It can be associated with another or an independent user. This entity is mandatory for the existence of the others, since they are direct or indirectly associated with the principal.

## Attribute Table

| Attribute    | Type   | Description   | Required | Filter |
|:-------------|:-------|:--------------|:---------|:-------|
| cpf          | String | User's CPF    | Yes      |Yes     |
| name         | String | Full name     | Yes      |Yes     |
| email        | String | Valid e-mail  | Yes      |Yes     |
| birthDate    | Date   | Date of birth | Yes      |Yes     |
| gender       | String | User gender   | Yes      |Yes     |
| ownerId      | String | User gender   | Yes      |Yes     |
| relationType | String | User gender   | Yes      |Yes     |

## Supported Methods

<span class="fs-5 lh-default">
POST
{: .label .label-green }


## Request examples

### Parent User

```json
{
    "name": "João Ferreira da Silva",
    "birthDate": "2001-11-10",
    "gender": "M",
    "cpf": "39917627065",
    "email": "joao.ferreira@gmail.com"
}
```

### Child User
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
### Diagrams

It is a *responsive* layout: on medium and larger width displays, it displays a sidebar, including a navigation panel; on smaller width displays, the sidebar is automatically hidden under a button.

Each child (and grandchild) page of a top-level page has so-called *breadcrumbs*: links to its parent (and grandparent) pages. It shows the breadcrumbs above the main content of the page.
Each child (and grandchild) page of a top-level page has so-called *breadcrumbs*: links to its parent (and grandparent) pages. It shows the breadcrumbs above the main content of the page.

