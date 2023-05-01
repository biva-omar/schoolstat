package com.school.schoolstat.services.interfaces;

import com.school.schoolstat.models.dto.requests.UpdateRequest;
import org.springframework.http.ResponseEntity;


public interface AuthService {

    /**
     * Service de connexion d'un utilisateur donné
     *
     * @param username
     * @param password
     * @return un objet JwtResponse
     */
    public ResponseEntity<?> login(String username, String password);

    /**
     * Service permettant de déconnecter un utilisateur donné
     *
     * @param username
     * @param sessionId
     * @return un booléen
     */
    public boolean logout(String username, String sessionId);

    /**
     * Service permettant de valider le code OTP envoyé à l'utilisateur
     *
     * @param otpCode
     * @param username
     * @return un booléen
     */
    public boolean validate(String otpCode, String username);

    /**
     * Permet d'ajouter un rôle à un compte donné
     *
     * @param username
     * @param rolename
     * @return un booléen
     */
    public boolean AddRoleToUser(String username, String rolename);

    /**
     * Permet de retirer un rôle à un utilisateur donné
     *
     * @param username
     * @param rolename
     * @return u booléen
     */
    public boolean RemoveRoleToUser(String username, String rolename);

    /**
     * Service permettant à un utilisateur donné de récupérer son compte après
     * avoir oublié son mot de passe
     *
     * @param username
     * @return un booleen
     */
    public boolean forgotPassword(String username);

    /**
     * Service permettant à un utilisateur de changer son mot de passe oublier apres demande de l'otp
     *
     * @param username
     * @param otpCode
     * @param newPassword
     * @return un booleen
     */
    public boolean resetPassword(String username, String OtpCode, String newPassword);

    /**
     * Service permettant à un utilisateur de changer son mot de passe
     * connaissant son ancien mot de passe
     *
     * @param request
     * @return un booleen
     */
    public boolean updatePassword(UpdateRequest request);

    /**
     * Service permettant de bloquer ou débloquer un compte utilisateur donné
     *
     * @param username
     * @param status
     * @return un booléen
     */
    public Boolean EnabledOrDisabledAccount(String username, boolean status);

    /**
     * Service permettant de checker un token
     *
     * @param username
     * @param token
     * @return un booleén
     */
    public boolean isExpireToken(String username, String token);

    /**
     * verifie si deux mot de passe correspond
     * @param sendPassword
     * @param registerPassword
     * @return
     */
    public boolean checkPassworkMatch(String sendPassword,String registerPassword);

}
