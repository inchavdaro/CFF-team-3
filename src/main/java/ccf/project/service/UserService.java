package ccf.project.service;


import ccf.project.domain.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    /**
     * Persists a User in the db
     *
     * @param userModel the user to be persisted
     * @return if the operation was successful
     */
    Boolean insertUser(UserModel userModel);

    /**
     * Delete a User from the db
     *
     * @param username the username of the to be deleted User
     * @return if the operation was successful
     */
    Boolean deleteUser(String username);

    /**
     * Updates a User entry in db with new password if the correct old one provided
     *
     * @param username    the username of the user
     * @param password    the current password of the user
     * @param newPassword the new password of the user
     * @return if the operation was successful
     */
    Boolean changePassword(String username, String password, String newPassword);

    /**
     * Gets user from db by username if there is one
     *
     * @param username the username to be searched in the db
     * @return optional User
     */
    Optional<UserModel> getUserByName(String username);

    /**
     * Gets list of all emails for all Users with ADMIN role
     */
    List<String> getAllAdminEmails();

    /**
     * Gets list of all emails for all Users with Salesman role
     */
    List<String> getAllSalesmanEmails();
}
