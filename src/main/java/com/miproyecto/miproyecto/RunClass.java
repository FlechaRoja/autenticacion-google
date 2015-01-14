/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miproyecto.miproyecto;

import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.model.Accounts;
import com.miproyecto.credenciales.OAuth2Client;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 *
 * @author Katherine
 */
public class RunClass {
    
    //Objeto tipo analytics
    private static Analytics analytics;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.security.GeneralSecurityException
     */
    public static void main(String[] args) throws IOException, GeneralSecurityException {
        
            //autoriza y obtiene el objeto tipo analytics
            analytics = OAuth2Client.getServicioAnalytics();
            
            //Consulta de cuentas 
            //(Generalmente trae solo una que es la cuenta que se crea para nuestro proyecto EN ANALYTICS)
            Accounts cuentas = analytics.management().accounts().list().execute();
            
            /*Imprimiendo el resultado vamos a darnos cuenta que el ID que aparece consola coincide con
            el ID que aparece en el dashboard de Analytics como ID de la cuenta, si es asi, 
            Felicidades!!!! se ha autenticado exitosamente en los servidores de Google y puede 
            empezar a usar los datos y servicios de sus APIs
            */
            
            System.out.println("ID de la cuenta "+cuentas.getItems().get(0).getId());

    }
    
}
