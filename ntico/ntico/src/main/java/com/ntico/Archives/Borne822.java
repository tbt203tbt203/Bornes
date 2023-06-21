package com.ntico.Archives;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class Borne822 {

    public static String getBorne822() throws IOException {
        try {
            URL url = new URL("https://api.charge.re/public/1/chargecontroller/822/?format=json");
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
                    String start = null;
                    if (jsonResponse.contains("\"start\":")) {
                        startIndex = jsonResponse.indexOf("\"start\":") + "\"start\":".length() + 1;
                        endIndex = jsonResponse.indexOf("\"", startIndex);
                        if (endIndex == -1) {
                            endIndex = jsonResponse.indexOf("}", startIndex);
                        }
                        start = jsonResponse.substring(startIndex, endIndex).trim();
                        if (start.equals("null")) {
                            start = null;
                        }
                    }
                    System.out.println(start);



                    String heureBizarre2 = start;
                    String formatEntree2 = "yyyy-MM-dd'T'HH:mm:ss[.SSSSSS][.SSSSS][.SSS][.SS][.S]X";
                    String formatSortie2 = "yyyy-MM-dd HH:mm:ss";

                    LocalDateTime dateTime2 = LocalDateTime.parse(heureBizarre2, DateTimeFormatter.ofPattern(formatEntree2));
                    LocalDateTime heureNormale2 = dateTime2.plusHours(2);
                    String heureNormaleStr2 = heureNormale2.format(DateTimeFormatter.ofPattern(formatSortie2));

                    System.out.println("Heure bizarre start : " + heureBizarre2);
                    System.out.println("Heure normale start  : " + heureNormaleStr2);



                    String timestamp = null;
                    if (jsonResponse.contains("\"timestamp\":")) {
                        startIndex = jsonResponse.indexOf("\"timestamp\":") + "\"timestamp\":".length() + 1;
                        endIndex = jsonResponse.indexOf("\"", startIndex);
                        if (endIndex == -1) {
                            endIndex = jsonResponse.indexOf("}", startIndex);
                        }
                        timestamp = jsonResponse.substring(startIndex, endIndex).trim();
                        if (timestamp.equals("null")) {
                            timestamp = null;
                        }
                    }
                    System.out.println(timestamp);



                    String heureBizarre = timestamp;
                    String formatEntree = "yyyy-MM-dd'T'HH:mm:ss[.SSSSSS][.SSSSS][.SSS][.SS][.S]X";
                    String formatSortie = "yyyy-MM-dd HH:mm:ss";

                    LocalDateTime dateTime = LocalDateTime.parse(heureBizarre, DateTimeFormatter.ofPattern(formatEntree));
                    LocalDateTime heureNormale = dateTime.plusHours(2);
                    String heureNormaleStr = heureNormale.format(DateTimeFormatter.ofPattern(formatSortie));

                    System.out.println("Heure bizarre timestamp: " + heureBizarre);
                    System.out.println("Heure normale timestamp: " + heureNormaleStr);


                    System.out.println("Test");
                    System.out.println("Heure du début de charge: " + heureNormaleStr2);
                    System.out.println("Heure actuelle: " + heureNormaleStr);
                    // Conversion et calcul de la durée de charge


                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime startTime = LocalDateTime.parse(heureNormaleStr2, formatter);
                    LocalDateTime endTime = LocalDateTime.parse(heureNormaleStr, formatter);
                    Duration duration = Duration.between(startTime, endTime);

                    long hours = duration.toHours();
                    long minutes = duration.toMinutesPart();
                    long seconds = duration.toSecondsPart();

                    System.out.println("En charge depuis: "+ hours + " heures, " + minutes + " minutes, " + seconds + " secondes.");


                    String power = null;
                    if (jsonResponse.contains("\"power\":")) {
                        startIndex = jsonResponse.indexOf("\"power\":") + "\"power\":".length();
                        endIndex = jsonResponse.indexOf("\"", startIndex);
                        if (endIndex == -1) {
                            endIndex = jsonResponse.indexOf("\"", startIndex);
                        }
                        power = jsonResponse.substring(startIndex, endIndex).trim();
                        if (power.equals("null")) {
                            power = null;
                        }
                        if (power.endsWith(",")) {
                            power = power.substring(0, power.length() - 1); // Enlève la virgule
                        }

                        if (power.equals("null")) {
                            power = null;
                        }
                    }


                    String energy_wh = null;
                    if (jsonResponse.contains("\"energy_wh\":")) {
                        startIndex = jsonResponse.indexOf("\"energy_wh\":") + "\"energy_wh\":".length() ;
                        endIndex = jsonResponse.indexOf("\"", startIndex);
                        if (endIndex == -1) {
                            endIndex = jsonResponse.indexOf("\"", startIndex);
                        }
                        energy_wh = jsonResponse.substring(startIndex, endIndex).trim();
                        if (energy_wh.equals("null")) {
                            energy_wh = null;
                        }
                        if (energy_wh.endsWith(",")) {
                            energy_wh = energy_wh.substring(0, energy_wh.length() - 1); // Enlève la virgule
                        }

                        if (energy_wh.equals("null")) {
                            energy_wh = null;
                        }
                    }
                    System.out.println("La puissance max de la borne est de: "+ energy_wh + " Wh");
                    System.out.println("La puissance distribué est de: "+ power + " Wh");



                    if (jsonResponse.contains("\"username\":")) {

                        startIndex = jsonResponse.indexOf("\"username\":") + "\"username\":".length();
                        endIndex = jsonResponse.indexOf(",", startIndex);
                        if (endIndex == -1) {
                            endIndex = jsonResponse.indexOf("}", startIndex);
                        }
                        username = jsonResponse.substring(startIndex, endIndex).trim();
                        if (username.equals("null")) {
                            username = null;
                        }
                    }


                    if (username != null && !username.equals("null")/* + power != null && !power.equals("null")+ energy_wh != null && !energy_wh.equals("null")+ timestamp != null && !timestamp.equals("null")+ start != null && !start.equals("null")*/) {
                        return "<br>&bull; La borne est libre ? <span style=\"color: red;\"> " + isActiveCharge + "</span><br>&bull; Utilisée par : " + username +/* "<br>&bull; Heure du début de charge: " + heureNormaleStr2 + "<br>&bull; Heure actuelle: " + heureNormaleStr+*/"<br>&bull; En charge depuis: "+ hours + " heures, " + minutes + " minutes, " + seconds + " secondes."   +  "<br>&bull; La puissance distribuée actuellement est de: "+ power + " Wh" +"<br>&bull; La puissance totale distribuée depuis le début de la charge est de: "+ energy_wh + " Wh" ;
                    } else {
                        return "<br>&bull; La borne est libre ? <span style=\"color: green;\">" + isActiveCharge + "</span><br>" ;
                    }
                }
            }

            return "<br>&bull; La borne est libre ? <span style=\"color: green;\">" + isActiveCharge + "</span><br>" ;







        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
