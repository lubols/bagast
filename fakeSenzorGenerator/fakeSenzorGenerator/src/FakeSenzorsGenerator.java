
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class FakeSenzorsGenerator {

    private final String USER_AGENT = "application/json";

//    public static void main(String[] args) throws Exception {
//        int[] temperatures = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        int[] windSpeed = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        int[] humidity = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        int[] pressure = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        int[] dustiness = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        RandomNumbers generator = new RandomNumbers();
//        FakeSenzorsGenerator http = new FakeSenzorsGenerator();
//
//        //System.out.println("Testing 1 - Send Http GET request");
//        //http.sendGet();
//        //System.out.println("\nTesting 2 - Send Http POST request");
//        while (true) {
//            //teploty
//            http.sendPost(temperatures,"teplota");
//            temperatures=generator.getRandNumbers(temperatures,-30,30);
//            //teploty
//            http.sendPost(windSpeed,"rychlost_vetra");
//            windSpeed=generator.getRandNumbers(windSpeed,0,20);
//            //teploty
//            http.sendPost(humidity,"vlhkost");
//            humidity=generator.getRandNumbers(humidity,0,100);
//            //teploty
//            http.sendPost(pressure,"tlak");
//            pressure=generator.getRandNumbers(pressure,80,140);
//            //teploty
//            http.sendPost(dustiness,"prasnost");
//            dustiness=generator.getRandNumbers(dustiness,0,100);
//            TimeUnit.SECONDS.sleep(5);
//        }
//    }
//
//    // HTTP GET request
//    private void sendGet() throws Exception {
//
//        String url = "http://localhost:8080/TestRestful/webresources/sensor/getSensorList";
//
//        URL obj = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//        // optional default is GET
//        con.setRequestMethod("GET");
//
//        //add request header
//        con.setRequestProperty("User-Agent", USER_AGENT);
//
//        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'GET' request to URL : " + url);
//        System.out.println("Response Code : " + responseCode);
//
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//
//        //print result
//        System.out.println(response.toString());
//
//    }

/**
 * Funkcia pošle hodnoty do databázy pomocou RESTu
 *
 * @param arr pole hodnôt senzora
 * @param type typ senzora pre ktorý sú hodnoty určené
 * @throws Exception 
 */
    void sendPost(int[] arr,String type) throws Exception {

        String url = "http://localhost:8080/TestRestful/webresources/value/updateValueByType?type="; //&value1=7&value2=6&value3=3
        url += type;
        for (int i = 0; i < arr.length; i++) {
            url += "&value" + (i + 1) + "=" + arr[i];
        }

        System.out.print(url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", USER_AGENT);
        //con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        //String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        //wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        //System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

    }

}
