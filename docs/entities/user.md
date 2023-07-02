---
title: User
layout: default
nav_order: 1
parent: Entities
---

# User

The user represents the main entity of the application. It can be associated with another or an independent user. This entity is mandatory for the existence of the others, since they are direct or indirectly associated with the principal.

## Attribute Table

See the [Jekyll docs page about layouts] for an explanation of the general idea of layouts and how to specify them.

You can use [Jekyll's front matter defaults] to specify the same layout for many pages.

## Supported Methods


POST
{: .label .label-green }

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
### Parent User

It is a *responsive* layout: on medium and larger width displays, it displays a sidebar, including a navigation panel; on smaller width displays, the sidebar is automatically hidden under a button.

Each child (and grandchild) page of a top-level page has so-called *breadcrumbs*: links to its parent (and grandparent) pages. It shows the breadcrumbs above the main content of the page.

Each page that has child pages generally has a list of links to those pages (you can suppress it by `has_toc: false` in the front matter). It shows the list as a *table of contents* below the main content.

## The `minimal` layout

A child and grandchild page of this page use the minimal layout. This differs from the default layout by omitting the sidebar -- and thereby also the navigation panel. To navigate between pages with the minimal layout, you can use the breadcrumbs and the tables of contents.

## Other layouts

Just the Docs has further layouts: `about`, `home`, `page`, and `post`. Currently, they are all based on the `default` layout. See the [Jekyll docs about inheritance] for how to customize them.

[front matter]: https://jekyllrb.com/docs/front-matter/ "Jekyll docs about front matter"
[Jekyll docs page about layouts]: https://jekyllrb.com/docs/layouts/ "Jekyll docs about layouts"
[Jekyll's front matter defaults]: https://jekyllrb.com/docs/configuration/front-matter-defaults/ "Jekyll docs about front matter defaults"
[Jekyll docs about inheritance]: https://jekyllrb.com/docs/layouts/#inheritance "Jekyll docs about inheritance"
