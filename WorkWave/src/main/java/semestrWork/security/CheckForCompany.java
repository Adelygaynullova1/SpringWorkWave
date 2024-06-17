package semestrWork.security;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;



public class CheckForCompany {
    public boolean checkInn(String inn) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8097/inncheck");
        boolean answer = false;
        String jsonBody = "{"
                + "\"inn\":\"" + inn + "\""
                + "}";

        try {

            httpPost.setHeader("Content-Type", "application/json");


            httpPost.setEntity(new StringEntity(jsonBody));


            HttpResponse response = httpClient.execute(httpPost);


            if (response.getStatusLine().getStatusCode() == 200) {
               answer = true;
            } else {
                System.out.println("Ошибка: " + response.getStatusLine());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
    }
}
