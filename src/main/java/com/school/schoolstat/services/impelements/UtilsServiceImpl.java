package com.school.schoolstat.services.impelements;

import org.springframework.stereotype.Service;

import java.util.Base64;


@Service
public class UtilsServiceImpl implements com.school.schoolstat.services.interfaces.UtilsService {

    @Override
    public String[] extractCredentials(String authString) {

        String[] credentials = null;
        // Header is in the format "Basic 5tyc0uiDat4"
        // We need to extract data before decoding it back to original string
        if (authString != null) {
            String[] authParts = authString.split("\\s+");
            String authInfo = authParts[1];
            // Decode the data back to original string
            byte[] decode = Base64.getDecoder().decode(authInfo.getBytes());
            String decodeString = new String(decode);

            credentials = decodeString.split(":");
        }

        return credentials;
    }
}