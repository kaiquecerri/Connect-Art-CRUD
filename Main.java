/*
    NOME DO PROJETO: CONNECTART
    GRUPO:
    ANDREW
    LUISA SANTOS
    ISABELI
    FABRICIO
    KAIQUE
*/


import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String filePath = "accounts.csv";

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
            case 1 -> read();
            case 2 -> update();
            case 3 -> delete();
            case 4 -> {
                JOptionPane.showMessageDialog(null, "Encerrando...");
                System.exit(0);
            }
            case -1 -> {
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
                options[0]
        );

        if(type == -1) mainMenu();

        name = InputValidator.inputValidation(
            "Digite o seu nome completo:",
            "O nome precisa ter ao menos 3 caracteres",
            3
        );

        email = InputValidator.emailValidation();

        password = InputValidator.inputValidation(
            "Digite sua senha:",
            "A senha precisa ter ao menos 8 caracteres",
            8
        );

        if (type == 0) {
            accountType = "Aluno";
            subjects = InputValidator.inputValidation(
                "Digite a matéria que você quer aprender:",
                "A materia precisa ter ao menos 3 caracteres",
                3
            );
        } else {
            accountType = "Professor";
            subjects = InputValidator.inputValidation(
                "Digite a matéria que você dará aula:",
                "A materia precisa ter ao menos 3 caracteres",
                3
            );
        }

        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.append(name).append(",").append(email).append(",").append(password).
            append(",").append(accountType).append(",").append(subjects).append("\n");
            JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar o arquivo.");
            e.printStackTrace();
        }

        mainMenu();
    }

    public static void read() {
        String searchEmail = JOptionPane.showInputDialog("Digite o email do usuário (ou digite todos):");

        if(searchEmail == null) {
            mainMenu();
            return;
        }

        if(searchEmail.trim().toLowerCase().equals("todos")) {
            try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) { 
                StringBuilder registers = new StringBuilder("Registros:\n"); 
                String line; 

                while((line = reader.readLine()) != null) { 
                    registers.append(line).append("\n"); 
                } 

                JOptionPane.showMessageDialog(null, registers.toString()); 
            } catch(IOException e) { 
                JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo."); 
                e.printStackTrace(); 
            } 

            mainMenu();
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean found = false;

            while((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if(data[1].equalsIgnoreCase(searchEmail)) {
                    String message =
                        "Nome: " + data[0] +
                        "\nEmail: " + data[1] +
                        "\nSenha: " + data[2] +
                        "\nCargo: " + data[3];

                    if(data.length > 4) {
                        message += "\nMatéria: " + data[4];
                    }

                    JOptionPane.showMessageDialog(null, message);

                    found = true;
                    break;
                }
            }

            if(!found) {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
                read();
            }

        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo.");
            e.printStackTrace();
        }

        mainMenu();
    }

    public static void update() {
        List<String> registers = readRegisters();
        if(registers.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado.");
            mainMenu();
        }

        String searchEmail = JOptionPane.showInputDialog("Digite o email do usuário:");
        if(searchEmail == null) mainMenu();
        boolean found = false;
        for (int i = 0; i < registers.size(); i++) {
            String data[] = registers.get(i).split(",");
            if(data[1].equalsIgnoreCase(searchEmail)){
                String newName = InputValidator.inputValidation(
                    "Digite o novo nome completo:",
                    "O nome precisa ter ao menos 3 caracteres",
                    3,
                    data[0]
                );

                String newEmail = InputValidator.emailValidation(data[1]);

                String newPassword = InputValidator.inputValidation(
                    "Digite sua nova senha:",
                    "A senha precisa ter ao menos 8 caracteres",
                    3,
                    data[2]
                );

                String newSubjects = "";

                if(data[3] == "Professor") {
                    newSubjects = InputValidator.inputValidation(
                        "Digite a matéria que você dará aula:",
                        "A materia precisa ter ao menos 3 caracteres",
                        3,
                        data[4]
                    );
                } else {
                    newSubjects = InputValidator.inputValidation(
                        "Digite a matéria que você quer aprender:",
                        "A materia precisa ter ao menos 3 caracteres",
                        3,
                        data[4]
                    );  
                }

                registers.set(i, newName + "," + newEmail + "," + newPassword + "," + data[3] + "," + newSubjects);
                found = true;
                break;
            }
        }

        if(found) {
            writeRegisters(registers);
            JOptionPane.showMessageDialog(null, "Usuário atualizado");
            mainMenu();
        } else {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
            update();
        }
    }

    private static void delete() {
        List<String> registers = readRegisters();
        if(registers.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado.");
            mainMenu();
        }

        String searchEmail = JOptionPane.showInputDialog("Digite o email do usuário:");
        if(searchEmail == null) mainMenu();

        boolean found = registers.removeIf(register -> register.split(",")[1].equalsIgnoreCase(searchEmail));
        if(found) {
            writeRegisters(registers);  
            JOptionPane.showMessageDialog(null, "Usuário apagado com sucesso.");
            mainMenu();
        } else {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado");
            delete();
        }
    }

    private static List<String> readRegisters() {
        List<String> registers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                registers.add(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo.");
            e.printStackTrace();
        }
        return registers;
    }

    private static void writeRegisters(List<String> registers) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String register : registers) {
                writer.append(register).append("\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo.");
            e.printStackTrace();
        }
    }

    public static boolean emailExits(String email) {
        List<String> registers = readRegisters();
        if(registers.isEmpty()) {
            return false;
        }

        boolean found = false;
        for (int i = 0; i < registers.size(); i++) {
            String data[] = registers.get(i).split(",");
            if(data[1].equalsIgnoreCase(email)){
                found = true;
                break;
            }
        }

        return found;
    }
}
