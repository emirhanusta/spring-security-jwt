package springsecurity.service;

import org.springframework.stereotype.Service;
import springsecurity.dto.UserResponse;
import springsecurity.exception.UserNotFoundException;
import springsecurity.model.User;
import springsecurity.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Constructs a new {@code UserService} with the specified {@link UserRepository}.
     *
     * @param userRepository The {@link UserRepository} used for user-related database operations.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Saves a user and returns the corresponding user response.
     *
     * @param user The {@link User} entity to be saved.
     * @return A {@link UserResponse} representing the saved user.
     */
    protected UserResponse saveUser(User user) {
        return UserResponse.from(userRepository.save(user));
    }

    /**
     * Retrieves user information by the specified username.
     *
     * @param username The username for which to retrieve user information.
     * @return A {@link UserResponse} representing the user.
     * @throws UserNotFoundException if the user with the specified username is not found.
     */
    protected UserResponse findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(UserResponse::from)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    /**
     * Checks if a user with the specified username exists.
     *
     * @param username The username to check for existence.
     * @return {@code true} if a user with the username exists; otherwise, {@code false}.
     */
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
