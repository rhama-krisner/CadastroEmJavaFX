package app.cadastro.api;

import app.cadastro.Pessoa;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class ApiClient {
    public static List<Pessoa> getPessoas() throws IOException {
        try {
            URL url = new URL("http://localhost:3000/pessoa");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                connection.disconnect();

                // Desserializar a resposta JSON manualmente
                JSONArray jsonArray = new JSONArray(response.toString());
                List<Pessoa> pessoas = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    long id = jsonObject.getLong("id");
                    String nome = jsonObject.getString("nome");
                    int idade = jsonObject.getInt("idade");
                    String cpf = jsonObject.getString("cpf");
                    String email = jsonObject.getString("email");
                    pessoas.add(new Pessoa(id, nome, cpf, idade,  email));
                }
                return pessoas;
            } else {
                System.out.println("Erro na solicitação GET. Código de resposta: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void postPessoa(String requestBody){
        try {
            URL url = new URL("http://localhost:3000/pessoa");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try(DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.writeBytes(requestBody);
                wr.flush();
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Codigo de resposta" + responseCode);

            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = bufferedReader.readLine()) != null){
                    response.append(inputLine);
                }

                System.out.println("Server: " + response.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
