---
layout: default
title: Entities
nav_order: 2
has_children: true
permalink: /docs/entities
---

# Entities

Entity classes that generate business tables, defining the data structure and relationships, and also facilitating data transportation between different layers (e.g., from repository to service and from service to controller).
{: .fs-6 .fw-300 }

### Supported methods

Our API supports the following methods HTTP:

- POST - Retrieves data from an API;
- GET - sends new data to an API;
- PUT - update existing data;
- DELETE - removes existing data;

### Filtering and Pagination

For requests made through the GET method, we provide the capability to apply filters and pagination. This offers greater control over the returned data, resulting in improved performance and reduced loading times.

Refer to the documentation of each method to learn how to use these features. Filters help restrict results, and pagination makes it easier to handle large amounts of data by displaying them in smaller chunks. Learn how to use these features to make the most of the API.

