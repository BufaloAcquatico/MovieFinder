---
name: DTO
description: DTO from current file
invokable: true
---

Generate the DTOs required for the current domain entity.

Use the existing project structure, DTOs, naming conventions, validation conventions, and mapping approach as the source of truth. Inspect existing DTOs before creating new ones and reuse/extend the established conventions rather than introducing a new pattern.

DTO structure:

dto.response: one response DTO representing the entity.

dto.request: separate create and update request DTOs when their requirements differ.

Follow the project's existing naming convention, e.g. GenreResponse, CreateGenreRequest, UpdateGenreRequest.

Do not create duplicate DTOs if an appropriate existing DTO already exists.

Include only fields that should be exposed through the API; do not blindly copy every entity field.

Do not expose JPA relationships as entities.

Apply Bean Validation annotations to request DTOs where appropriate, following existing project conventions.

Create/update DTOs should reflect the actual operation: fields that are immutable, generated, or server-controlled should not be accepted unnecessarily.

Preserve consistent Java types and nullability/validation semantics with the domain model and existing DTOs.

Do not add unnecessary DTO variants unless the domain or API requirements justify them.

Before creating anything, inspect the relevant entity and existing DTOs to determine what is actually needed.

Create or modify only the DTOs required for this entity. Do not implement controllers, services, repositories, mappers, or unrelated code.

Do not use classes, the code must use record

Do not write comments