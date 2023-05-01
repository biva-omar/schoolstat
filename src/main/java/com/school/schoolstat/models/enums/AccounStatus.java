package com.school.schoolstat.models.enums;

public enum AccounStatus {

    /**
     * authentification  d'un compte utilisateur par le phone et email
     */
    VALIDATE,
    /**
     * authentification d'un compte utilisateur par le phone uniquement
     */
    PHONE_VALIDATE,
    /**
     * authentification d'un compte utilisateur par l'email uniquement
     */
    EMAIL_VALIDATE,
    /**
     *  suspension d'un utulisateur
     */
    //SUSPEND,

    /**
     * en attente de validation des information de l'utilisateur pas l'admin ou pas une autre entiter
     */
    AWAITING_VALIDATION,

    /**
     *  en attente du code otp d'authentification de l'email et du telephone
     */
    AWAITING_EMAIL_PHONE_OTP_CODE,

    /**
     * en attente du code otp d'authentification par email
     */
    AWAITING_EMAIL_OTP_CODE,
    /**
     * en attente du code otp d'authentification par telephone
     */
    AWAITING_PHONE_OTP_CODE,
    //----------------------uniquement lors du reset----------------//
    /**
     * en attente du code otp d'authentification par  l'email ou par telephone, il permet
     * indiquer que le status du compte a ete a l'etat valide lors du reset
     * NB: CE STATUS EST UTILISER UNIQUEMENT LORS D"UN RESET D"UN MOT DE PASSE
     */
    AWAITING_OTP_CODE,
    /**
     * en attente du code otp d'authentification par l'email ou par telephone, il permet d'indiquer que le status
     * du compte etait soit a l'etat PHONE_VALIDATE et l'utilisateur a demande le reset du passeword par email soit a l'etat
     *  EMAIL_VALIDATE et l'utilisateur a demande le reset du password par phone
     * NB: CE STATUS EST UTILISER UNIQUEMENT LORS D"UN RESET D"UN MOT DE PASSE
     */
    AWAITING_VALIDATE_OTP_CODE
}
