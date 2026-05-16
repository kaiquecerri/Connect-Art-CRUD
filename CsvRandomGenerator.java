import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.JOptionPane;

public class CsvRandomGenerator {
    private static final String filePath = "accounts.csv";
    private static final String[] names = {
        "Lucas", "Miguel", "Arthur", "Heitor", "Theo", "Davi", "Gabriel", "Bernardo", 
        "Samuel", "João", "Pedro", "Matheus", "Rafael", "Enzo", "Nicolas", "Guilherme",
        "Benjamin", "Joaquim", "Lorenzo", "Henrique", "Felipe", "Daniel", "Murilo", 
        "Leonardo", "Pietro", "Vinicius", "Eduardo", "Isaac", "Caio", "Antônio", "Bryan", 
        "Yuri", "Otávio", "Ryan", "Emanuel", "André", "Thiago", "Bruno", "Victor", 
        "Gustavo", "Diego", "Alexandre", "Carlos", "Fernando", "Igor", "Lucca", "Cauã", 
        "Ian", "Kevin", "Nathan", "Raul", "Rodrigo", "Sérgio", "Vitor", "Wesley", "Alan", 
    };

    private static final String[] lastnames = {
        "Silva", "Santos", "Oliveira", "Souza", "Rodrigues", "Ferreira", "Alves",
        "Pereira", "Lima", "Gomes", "Costa", "Ribeiro", "Martins", "Carvalho", 
        "Almeida", "Lopes", "Soares", "Fernandes", "Vieira", "Barbosa", "Rocha", 
        "Dias", "Monteiro", "Mendes", "Freitas", "Cardoso", "Ramos", "Araújo", 
        "Teixeira", "Correia", "Moreira", "Nunes", "Machado", "Melo", "Castro", 
        "Campos", "Batista", "Azevedo", "Cavalcanti", "Moura", "Rezende", "Farias", 
        "Pinto", "Leite", "Coelho", "Andrade", "Cunha", "Borges", "Santana", 
        "Bezerra", "Tavares", "Aguiar", "Fonseca", "Peixoto", "Duarte", "Moraes", 
        "Sales",
    };

    private static final String[] emailDomains = {
        "gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "icloud.com", 
        "uol.com.br"
    };

    private static final String[] subjects = {
        "Música", "Desenho", "Pintura", "Fotografia", "Dança", "Teatro", "Cinema", 
        "Escultura",
        "Literatura", "Artes Visuais", "Design Gráfico", "Moda", "Arquitetura", 
        "Culinária", "Jardinagem", "Escrita Criativa",
        "Música Clássica", "Música Popular", "Dança Contemporânea", "Dança de Salão", 
        "Teatro Musical", "Teatro de Rua",
        "Cinema de Animação", "Cinema Documentário", "Escultura em Argila", 
        "Escultura em Madeira", "Literatura Infantojuvenil"
    };

    public static void popularArquivoCsv() {
        Random randon = new Random();
        try (FileWriter writer = new FileWriter(filePath)) {
            for (int i = 0; i < 100; i++) {
                String name = names[randon.nextInt(names.length)];
                String lastname = lastnames[randon.nextInt(lastnames.length)];
                String emailDomain = emailDomains[randon.nextInt(emailDomains.length)];
                String subject = subjects[randon.nextInt(subjects.length)];
                String email = name.toLowerCase() + "." + lastname.toLowerCase() + "@" + emailDomain;
                String password = "password" + (i + 1);
                String type = randon.nextBoolean() ? "Professor" : "Aluno"; 
                String line = String.join(",", name + " " + lastname, email, password, type, subject) + "\n";
                writer.write(line);
            }
            JOptionPane.showMessageDialog(null, "Arquivo CSV gerado com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao gerar o arquivo CSV: " + e.getMessage());
        }
    };

    public static void main(String[] args) {
        popularArquivoCsv();
    }
}