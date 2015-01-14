/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miproyecto.credenciales;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/**
 * Esta clase se encarga de realizar la comunicacion entre la app y los servidores de Google por medio de una
 * credencial construida previamente. Ademas una vez cargada la credencial, se procede a hacer la autenticacion
 * por medio de un objeto tipo Analytics, concretando el permiso con la credencial anteriormente construida.
 * @author Katherine
 */
public class OAuth2Client {
    
    private static Analytics servicioAnalytics;
    private static GoogleCredential googleCredencial;

    //Este metodo se encarga de cargar el objeto credencial 
    private static void obtenerServicioAutenticacion() throws IOException, GeneralSecurityException {
        googleCredencial = new GoogleCredential.Builder()
                .setTransport(OAuth2ClientCredenciales.getHTTP_TRANSPORT())
                .setJsonFactory(OAuth2ClientCredenciales.getJSON_FACTORY())
                .setServiceAccountId(OAuth2ClientCredenciales.getSERVICE_ACCOUNT_EMAIL())
                .setServiceAccountScopes(Arrays.asList(AnalyticsScopes.ANALYTICS_READONLY))
                .setServiceAccountPrivateKeyFromP12File(new File(OAuth2ClientCredenciales.getPATH_P12KEY_SERVICE_ACCOUNT()))
                .build();
    }

    //Este mwtodo se encarga de cargar el objeto analytics tomando en cuenta la credencial que se contruyo anteriormente
    public static Analytics getServicioAnalytics() throws IOException, GeneralSecurityException {
        obtenerServicioAutenticacion();
        servicioAnalytics = new Analytics.Builder(
                OAuth2ClientCredenciales.getHTTP_TRANSPORT(), OAuth2ClientCredenciales.getJSON_FACTORY(), googleCredencial)
                .setApplicationName(OAuth2ClientCredenciales.getAPPLICATION_NAME())
                .build();
        return servicioAnalytics;
    }
    
}
