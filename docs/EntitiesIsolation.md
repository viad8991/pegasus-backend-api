```
public class UserController {
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        UserDTO userDTO = userService.createUser(userRequest);
        return new ResponseEntity<>(new UserResponse(userDTO), HttpStatus.CREATED);
    }
}

public class UserService {
    public UserDTO create(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser);
    }
}

public class UserRepository {
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }
}
```

```
public class UserRequest {
    private Long ids;
}

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

public class UserDTO {
    private Long id;
    public UserDTO(User user) {
        this.id = user.getId();
    }
}

public class UserResponse {
    private Long id;
    public UserResponse(UserDTO userDTO) {
        this.id = userDTO.getId();
    }
}

```

```kotlin
data class EntityRequest(val field1: String, val field2: Int)
data class EntityDto(val field1: String, val field2: Int)
data class Entity(val field1: String, val field2: Int)
data class EntityResponse(val field1: String, val field2: Int)

fun EntityRequest.toDto() = EntityDto(field1, field2)
fun EntityDto.toEntity() = Entity(field1, field2)
fun Entity.toDto() = EntityDto(field1, field2)
fun EntityDto.toResponse() = EntityResponse(field1, field2)



object EntityMapper {
    fun toDto(request: EntityRequest) = EntityDto(request.field1, request.field2)
    fun toEntity(dto: EntityDto) = Entity(dto.field1, dto.field2)
    fun toDto(entity: Entity) = EntityDto(entity.field1, entity.field2)
    fun toResponse(dto: EntityDto) = EntityResponse(dto.field1, dto.field2)
}
```

```java
public class EntityRequest {
    private String field1;
    private int field2;

    // getters and setters
}

public class EntityDto {
    private String field1;
    private int field2;

    // getters and setters
}

public class Entity {
    private String field1;
    private int field2;

    // getters and setters
}

public class EntityResponse {
    private String field1;
    private int field2;

    // getters and setters
}

public class Mapper {
    public static EntityDto toDto(EntityRequest request) {
        return new EntityDto(request.getField1(), request.getField2());
    }

    public static Entity toEntity(EntityDto dto) {
        return new Entity(dto.getField1(), dto.getField2());
    }

    public static EntityDto toDto(Entity entity) {
        return new EntityDto(entity.getField1(), entity.getField2());
    }

    public static EntityResponse toResponse(EntityDto dto) {
        return new EntityResponse(dto.getField1(), dto.getField2());
    }
}
```