---
name: Entity From SQL
description: Provided a MySQL DDL statement, generate a Java class that represents the table in the database.
invokable: true
---

Generate a Spring Boot JPA entity from the MySQL SQL schema provided below.
Requirements:
Do NOT write any import
Map the table and columns explicitly with @Table, @Column, etc.
Map primary keys and foreign keys correctly.
Use appropriate Java types for the SQL types.
Represent relationships with JPA associations (@ManyToOne, @OneToMany, @OneToOne, etc.) where appropriate.
Respect nullability, uniqueness, and constraints where they can be represented in JPA.
Use Lombok for boilerplate (@Getter, @Setter, @NoArgsConstructor, etc.).
Do not add fields, relationships, or business logic that are not supported by the SQL schema.
Return only the complete Java entity code.
Do not write comments, only the code

SQL schema: