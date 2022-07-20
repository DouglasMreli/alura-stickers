import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

    
    public void Cria(InputStream inputStream, String nomeArquivo) throws Exception {

        // Leitura da imagem
        BufferedImage imagemOriginal= ImageIO.read(inputStream);
 
        // Gerar nova imagem em memória com transparência
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura+200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
        
        // Copiar a imagem original para a nova em memória
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);
        
        // Configurar a fonte do texto
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);
        // Escrever uma frase na nova imagem
        graphics.drawString("TOPZERA", 160 , novaAltura-100);
        // Escrever a imagem em um arquivo  
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }

}
