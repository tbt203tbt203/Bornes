package com.ntico.service;

import com.ntico.repository.BornesRepository;
import com.ntico.repository.UtilisateursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class LogUsername {

    @Autowired
    private UtilisateursRepository utilisateursRepository;

    @Autowired
    private BornesRepository bornesRepository;







        public static String getBorneUsername(String borneNumber) throws IOException {
            try {
                URL url = new URL("https://api.charge.re/public/1/chargecontroller/"+ borneNumber +"/?format=json");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
                    String newUrl = connection.getHeaderField("Location");
                    URL redirectUrl = new URL(newUrl);
                    connection = (HttpURLConnection) redirectUrl.openConnection();
                    connection.setRequestMethod("GET");
                    responseCode = connection.getResponseCode();
                    System.out.println("Rep code : " + responseCode);
                } else {
                    System.out.println("Rep code2 : " + responseCode);
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                connection.disconnect();

                String jsonResponse = response.toString();
                System.out.println("Ce que la borne indique : " + jsonResponse);

                String isActiveCharge = "Non";
                String username = null;

                if (jsonResponse.contains("\"active_charge\":")) {
                    int startIndex = jsonResponse.indexOf("\"active_charge\":") + "\"active_charge\":".length();
                    int endIndex = jsonResponse.indexOf(",", startIndex);
                    if (endIndex == -1) {
                        endIndex = jsonResponse.indexOf("}", startIndex);
                    }
                    String activeChargeValue = jsonResponse.substring(startIndex, endIndex).trim();
                    if (activeChargeValue.equals("null")) {
                        isActiveCharge = "Oui";
                    }else{



                        if (jsonResponse.contains("\"username\":")) {

                            startIndex = jsonResponse.indexOf("\"username\":") + "\"username\":".length();
                            endIndex = jsonResponse.indexOf(",", startIndex);
                            if (endIndex == -1) {
                                endIndex = jsonResponse.indexOf("}", startIndex);
                            }
                            username = jsonResponse.substring(startIndex, endIndex).trim();
                            if (username.equals("null")) {
                                username = null;
                            }else {
                                username = username.replace("\"", "");
                            }
                            return username;
                        }else {
                            return null;

                        }




                    }

                }


                return username;




            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }


    }


