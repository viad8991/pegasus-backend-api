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

