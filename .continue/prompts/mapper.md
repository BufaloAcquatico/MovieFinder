---
name: Mapper
description: Mapper from Entity and DTO
invokable: true
---

Generate a MapStruct mapper using exactly this structure.

Replace the placeholder class names with the actual Entity and DTO class names provided in the context.

```java
package <mapper-package>;

import <entity-package>.<Entity>;
import <request-package>.<CreateRequest>;
import <request-package>.<UpdateRequest>;
import <response-package>.<Response>;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface <Entity>Mapper {

    <Response> toResponse(<Entity> entity);

    <Entity> toEntity(<CreateRequest> request);

    void updateEntity(
            <UpdateRequest> request,
            @MappingTarget <Entity> entity
    );
}
```

Rules:

* Follow the exact structure above.
* Replace only the placeholders with the corresponding classes from the provided context.
* Do not add `@Mapping` annotations for fields with matching names.
* Do not add `@Mapping` annotations unless they are actually required because source and target property names differ.
* Do not add other methods.
* Do not add business logic.
* Do not generate the MapStruct `*Impl` class; MapStruct generates it automatically.
* Use the existing package structure from the project.
