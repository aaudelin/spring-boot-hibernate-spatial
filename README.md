# Spring Boot - GeoDB Example

This project is an implementation example of H2 - Geo DB.

It uses Spring Boot 2 with Hibernate 5.4.6 and H2 1.4.

## H2

```xml
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
```

**NB:** Sometimes, according to the version you use for hibernate-spatial you might encounter the error : 

` ClassNotFoundException : org.locationtech.jts.geom.CoordinateSequence `

This error happens because of incompatible versions between hibernate-spatial and H2. Thus, make sure to use H2 with lower version 1.3.X

## Liquibase

Liquibase enable schema updates thanks to xml files.

### Dependency
```xml
    <dependency>
        <groupId>org.liquibase</groupId>
        <artifactId>liquibase-core</artifactId>
    </dependency>
```

### Usage 

```xml
    <changeSet id="all" author="aaudelin" context="all">
        <createTable tableName="coordinate">
            ...
            <column name="wgs84" type="geometry"/>
        </createTable>

        <insert tableName="coordinate">
            ...
            <column name="wgs84">POINT(-0.369643801033 43.2946162781)</column>
        </insert>
    </changeSet>
```

Use type `Geometry` with liquibase to enable the storage of a `Geometry` object in H2 DB.
To insert a geometry use a subtype of `Geometry` like `Point` or `Line`

## Hibernate

To enable the interpretation of geometry objects, H2 must be configured with a proper dialect.

```properties
spring.jpa.properties.hibernate.dialect = org.hibernate.spatial.dialect.h2geodb.GeoDBDialect
```

Add the dependency to maven
```xml
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-spatial</artifactId>
    </dependency>
```

This dependency enables the deserialization of the Geometry object stored in the H2 database.
The Geometry is converted to org.locationtech.jts.geom.Point.
