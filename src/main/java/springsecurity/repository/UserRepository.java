package springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springsecurity.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Finds a user by the specified username.
     *
     * @param username The username of the user to find.
     * @return An {@link Optional} containing the found user or an empty {@link Optional} if not found.
     */
    Optional<User> findByUsername(String username);

    /**
     * Checks if a user with the specified username exists.
     *
     * @param username The username to check for existence.
     * @return {@code true} if a user with the username exists; otherwise, {@code false}.
     */
    boolean existsByUsername(String username);
}
