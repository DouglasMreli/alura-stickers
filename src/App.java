import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) throws Exception {
       
        // Fazer uma conexão HTTP e buscar os top 250 filmes
        
        String url = "https://imdb-api.com/en/API/Top250Movies/k_w4wtni0o";
        URI endereco = URI.create(url);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                            .newBuilder(endereco)
                            .GET()
                            .build();
        HttpResponse<String> response = client.send(
                                        request, 
                                        BodyHandlers.ofString());
        String body = response.body();

        // Extrair só os dados que interessam (titulo, poster, classificação)
        
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);


        // Exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();
        for(Map<String, String> filme: listaDeFilmes) {
            String urlImagem = filme.get("image");
            InputStream inputStream = 
                new URL(urlImagem).
                openStream();

            String nomeFilme = filme.get("title");
            String nomeArquivo = nomeFilme + ".png";

            geradora.Cria(inputStream, nomeArquivo);

            System.out.println("/saida/"+nomeFilme);
            System.out.println(filme.get("imDbRating"));
            System.out.println();
        }

    }
}
