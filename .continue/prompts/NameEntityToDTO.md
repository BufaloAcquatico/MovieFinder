---
name: NameEntityToDTO
description: Produce a DTO for an entity
invokable: true
---

The next step in the project is to curate DTOs and the mappers for the controllers to use for a specific entity. Produce sensible DTOs such  that it exists a DTO representing what the client views of an entity in a response like GET, DTOs for creating and updating separately, and if an entity has references to other entities such that the response DTO contains nested objects, produce the sensible nested DTOs. 

With those, produce the needed MapStruct mappers related to such DTOs. At this moment in time controllers are not to be examined, only concern the task about DTOs and mappers, and only for the specified entity unless another one is linked to it. 