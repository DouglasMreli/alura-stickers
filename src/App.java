import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
       
        // Fazer uma conexão HTTP e buscar os top 250 filmes
        
        // String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoIMDB();

        String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD-JamesWebbSpaceTelescope";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
        
        var http = new ClienteHTTP();
        String json = http.buscaDados(url);

        // Exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();
        for(int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);
            
            InputStream inputStream = 
                new URL(conteudo.urlImagem()).
                openStream();

            String titulo = conteudo.titulo();
            String nomeArquivo = "../saida/"+ titulo + ".png";
            
            geradora.Cria(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println();
        }

    }
}
