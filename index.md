---
layout: default
title: Home
nav_order: 1
description: "Solution for tracking appliance and home electronic energy use through web APIs"
permalink: /
---

# Energy tracking easy to use
{: .fs-9 }

Don't waste time and start reaping the benefits of energy usage tracking. Our API is ready to help you understand, control, and save energy in your home or business.
{: .fs-6 .fw-300 }

[Get started now](#getting-started){: .btn .btn-primary .fs-5 .mb-4 .mb-md-0 .mr-2 }
[View it on GitHub][KIRGH Energy repo]{: .btn .fs-5 .mb-4 .mb-md-0 }

---

## How it works

KIRGH Energy is a set of web APIs that help you tracking the energy usage from your appliances.

For make it real, we developed three APIs (users, addresses, appliances) that are prepared to receive JSON messages and generate insights about your home or company energy usage.

## Getting started

If you also believe that talking is cheap (so "show me the code"), click on link below to access our Swagger API docs:

<span class="fs-3">
[Show me the code!][Swagger API]{: .btn }
</span>

## About the project

Just the Docs is &copy; 2017-{{ "now" | date: "%Y" }} by [Patrick Marsceill](http://patrickmarsceill.com).

### License

Just the Docs is distributed by an [MIT license](https://github.com/just-the-docs/just-the-docs/tree/main/LICENSE.txt).

### Creators

When contributing to this repository, please first discuss the change you wish to make via issue,
email, or any other method with the owners of this repository before making a change. Read more about becoming a contributor in [our GitHub repo](https://github.com/just-the-docs/just-the-docs#contributing).

#### Thank you to the contributors of Just the Docs!

<ul class="list-style-none">
{% for contributor in site.github.contributors %}
  <li class="d-inline-block mr-1">
     <a href="{{ contributor.html_url }}"><img src="{{ contributor.avatar_url }}" width="32" height="32" alt="{{ contributor.login }}"></a>
  </li>
{% endfor %}
</ul>

[KIRGH Energy repo]: https://github.com/bigois/kirgh-energy
[Swagger API]: https://petstore.swagger.io