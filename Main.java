import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    private static final String filePath = "contas.csv";

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        String options[] = { "Cadastrar", "Visualizar", "Editar", "Apagar", "Sair" };
        int selectedOption = JOptionPane.showOptionDialog(
                null,
                "Selecione uma das opções abaixo.",
                "ConnectArt - CRUD",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        switch (selectedOption) {
            case 0 -> create();
            /*
             * case 1 -> read();
             * case 2 -> update();
             * case 3 -> delete();
             */
            case 4 -> {
                JOptionPane.showMessageDialog(null, "Encerrando...");
                System.exit(0);
            }
            default -> JOptionPane.showMessageDialog(null, "Opção inválida.");
        }
    }

    public static void create() {
        String options[] = { "Aluno", "Professor" };
        String name, email, password, accountType, subjects;
        int type = JOptionPane.showOptionDialog(
                null,
                "Você é:",
                "ConnectArt - CADASTRO",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        boolean _tryagain = true;

        do {
            name = JOptionPane.showInputDialog("Digite seu nome completo:");
            if (name == null)
                mainMenu();
            name = name.trim().replaceAll(",", "");
            if (name.length() < 3) {
                JOptionPane.showMessageDialog(null,
                        "O nome de usuário é inválido:\n É necessário ter ao menos 3 dígitos.");
            } else {
                _tryagain = false;
            }
        } while (_tryagain);

        _tryagain = true;
        do {
            email = JOptionPane.showInputDialog("Digite seu email:");
            if (email == null)
                mainMenu();
            email = email.trim().replaceAll(",", "");

            if (!EmailValidator.validarEmail(email)) {
                JOptionPane.showMessageDialog(null, "O email digitado é inválido: \nTente novamente.");
            } else {
                _tryagain = false;
            }
        } while (_tryagain);

        _tryagain = true;
        do {
            password = JOptionPane.showInputDialog("Digite sua senha:");
            if (password == null)
                mainMenu();
            password = password.trim().replaceAll(",", "");

            if (password.length() < 8) {
                JOptionPane.showMessageDialog(null,
                        "A senha digitada é inválida:\nÉ preciso ter ao menos 8 caracteres\nNão pode conter vírgula(,)");
            } else {
                _tryagain = false;
            }
        } while (_tryagain);

        if (type == 0) {
            accountType = "Aluno";
            subjects = "";
        } else {
            accountType = "Professor";
            _tryagain = true;
            do {
                subjects = JOptionPane.showInputDialog("Digite a matéria que você dará aula:");
                if (subjects == null)
                    mainMenu();
                subjects.trim().replaceAll(",", "");
                if (subjects.length() < 3) {
                    JOptionPane.showMessageDialog(null,
                            "A matéria digitada é inválida.\nÉ preciso ter ao menos 3 caracteres");
                } else {
                    _tryagain = false;
                }
            } while (_tryagain);
        }

        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.append(name).append(",").append(email).append(",").append(password).append(",").append(accountType)
                    .append(",").append(subjects).append("\n");
            JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar o arquivo.");
            e.printStackTrace();
        }

        mainMenu();
    }

    public static void read() {
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder registers = new StringBuilder("Registros:\n");
            String line;
            while((line = reader.readLine()) != null) {
                registers.append(line).append("\n");
            }
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo.");
            e.printStackTrace();
        }   

        boolean _tryagain = true;
        do {
            String searchQuery = JOptionPane.showInputDialog("Insira o email do usuário:");
            
        } while (_tryagain);
    }
}
