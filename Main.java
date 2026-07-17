/**import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
public class Main {

    public static class GUI extends JFrame{
        private final Arena arena;
        
        public GUI(Arena arena) {
            this.arena = arena;
            setTitle("Arena de Batalha de RPG");
            setSize(700, 700);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel painelPrincipal = new JPanel(new GridLayout(7, 1, 10, 10));
            painelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

            //------ Botões do menu ------
            JButton CadastrarPersonagem = new JButton("1. Cadastrar personagem (atributos padrão por tipo)");
            painelPrincipal.add(CadastrarPersonagem);

            JButton CadastrarPersonagemPersonalizado = new JButton("2. Cadastrar personagem (atributos personalizados)");
            painelPrincipal.add(CadastrarPersonagemPersonalizado);

            JButton ListarPersonagens = new JButton("3. Listar personagens");
            painelPrincipal.add(ListarPersonagens);

            JButton Batalhar = new JButton("4. Batalhar");
            painelPrincipal.add(Batalhar);

            JButton HistoricoBatalhas = new JButton("5. Histórico de batalhas");
            painelPrincipal.add(HistoricoBatalhas);

            JButton EstatisticasArena = new JButton("6. Estatísticas da arena");
            painelPrincipal.add(EstatisticasArena);

            JButton Sair = new JButton("0. Sair");
            painelPrincipal.add(Sair);

            //------ Ações dos botões ------
            // 1. Cadastrar personagem (atributos padrão por tipo)
            CadastrarPersonagem.addActionListener((ActionEvent e) -> {
                String nome = JOptionPane.showInputDialog("Digite o nome do personagem:");
                if (nome == null) return;

                String[] tipos = {"Guerreiro", "Mago", "Arqueiro", "Paladino", "Viking"};
                JComboBox<String> tipoComboBox = new JComboBox<>(tipos);
                int clique = JOptionPane.showConfirmDialog(null, tipoComboBox, "Escolha a classe do personagem", JOptionPane.OK_CANCEL_OPTION);

                if (clique == JOptionPane.OK_OPTION) {
                    String tipoEscolhido = (String) tipoComboBox.getSelectedItem();
                    arena.cadastrarPersonagem(nome, tipoEscolhido);
                    JOptionPane.showMessageDialog(null, "Personagem " + nome + " cadastrado como " + tipoEscolhido + "!");
                }
            });

            //2. Cadastrar personagem (atributos personalizados)
            CadastrarPersonagemPersonalizado.addActionListener((ActionEvent e) -> {
                String nome = JOptionPane.showInputDialog("Digite o nome do personagem:");
                if (nome == null) return;

                String[] tipos = {"Guerreiro", "Mago", "Arqueiro", "Paladino", "Viking"};
                JComboBox<String> tipoComboBox = new JComboBox<>(tipos);
                int clique = JOptionPane.showConfirmDialog(null, tipoComboBox, "Escolha a classe do personagem", JOptionPane.OK_CANCEL_OPTION);

                if (clique == JOptionPane.OK_OPTION) {
                    String tipoEscolhido = (String) tipoComboBox.getSelectedItem();
                    try {
                        int vida = Integer.parseInt(JOptionPane.showInputDialog("Digite a vida do personagem:"));
                        int ataque = Integer.parseInt(JOptionPane.showInputDialog("Digite o ataque do personagem:"));
                        int defesa = Integer.parseInt(JOptionPane.showInputDialog("Digite a defesa do personagem:"));
                        arena.cadastrarPersonagem(nome, tipoEscolhido, vida, ataque, defesa);
                        JOptionPane.showMessageDialog(null, "Personagem personalizado " + nome + " (" + tipoEscolhido + ") criado com sucesso!");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Erro: Você deve digitar apenas números para vida, ataque e defesa.");
                    }
                }
            });
            ListarPersonagens.addActionListener((ActionEvent e) -> {
                arena.listarPersonagens();
            });

            //4. Batalhar
            Batalhar.addActionListener((ActionEvent e) -> {
                if (arena.getPersonagens().size() < 2) {
                    JOptionPane.showMessageDialog(null, "Cadastre pelo menos 2 personagens para batalhar.");
                    return;
                }
                arena.listarPersonagens();
                try {
                    int idx1 = Integer.parseInt(JOptionPane.showInputDialog("Digite o índice do personagem 1:"));
                    int idx2 = Integer.parseInt(JOptionPane.showInputDialog("Digite o índice do personagem 2:"));
                    arena.realizarBatalha(idx1, idx2);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Índices inválidos. Operação cancelada.");
                }
            });

            HistoricoBatalhas.addActionListener((ActionEvent e) -> {
                arena.listarBatalhas();
            });
            EstatisticasArena.addActionListener((ActionEvent e) -> {
                Personagem.exibirEstatisticas();
            });
            Sair.addActionListener((ActionEvent e) -> {
                System.exit(0);
            });
            add(painelPrincipal);
            setVisible(true);

        }

    }   
    public static void main(String[] args) {
        Arena arena = new Arena();
        new GUI(arena);
    }

}
**/

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Main {

    public static class GUI extends JFrame {
        private final Arena arena;
        
        public GUI(Arena arena) {
            this.arena = arena;
            setTitle("Arena de Batalha de RPG");
            setSize(700, 700);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel painelPrincipal = new JPanel(new GridLayout(7, 1, 10, 10));
            painelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

            JButton btnCadastrar = new JButton("1. Cadastrar personagem (padrão)");
            JButton btnCadastrarPers = new JButton("2. Cadastrar personagem (personalizado)");
            JButton btnListar = new JButton("3. Listar personagens");
            JButton btnBatalhar = new JButton("4. Batalhar");
            JButton btnHistorico = new JButton("5. Histórico de batalhas");
            JButton btnEstatisticas = new JButton("6. Estatísticas da arena");
            JButton btnSair = new JButton("0. Sair");

            painelPrincipal.add(btnCadastrar);
            painelPrincipal.add(btnCadastrarPers);
            painelPrincipal.add(btnListar);
            painelPrincipal.add(btnBatalhar);
            painelPrincipal.add(btnHistorico);
            painelPrincipal.add(btnEstatisticas);
            painelPrincipal.add(btnSair);

            // Método auxiliar para criar instâncias com base na string do JComboBox
            // ISSO SUBSTITUI O SEU ANTIGO SWITCH GIGANTE NA CLASSE PERSONAGEM!
            java.util.function.Function<String[], Personagem> criarPersonagem = (args) -> {
                String nome = args[0];
                String tipo = args[1];
                
                // Se args tiver tamanho 2, é padrão. Se tiver 5, é personalizado.
                if (args.length == 2) {
                    if (tipo.equals("Guerreiro")) return new Guerreiro(nome);
                    if (tipo.equals("Mago")) return new Mago(nome);
                    // Adicione os outros tipos conforme você cria as classes!
                    return new Guerreiro(nome); // fallback
                } else {
                    int v = Integer.parseInt(args[2]);
                    int a = Integer.parseInt(args[3]);
                    int d = Integer.parseInt(args[4]);
                    if (tipo.equals("Guerreiro")) return new Guerreiro(nome, v, a, d);
                    if (tipo.equals("Mago")) return new Mago(nome, v, a, d);
                    return new Guerreiro(nome, v, a, d); // fallback
                }
            };

            btnCadastrar.addActionListener((ActionEvent e) -> {
                String nome = JOptionPane.showInputDialog("Digite o nome do personagem:");
                if (nome == null || nome.trim().isEmpty()) return;

                String[] tipos = {"Guerreiro", "Mago"}; // Adicione os outros na array
                JComboBox<String> tipoCombo = new JComboBox<>(tipos);
                if (JOptionPane.showConfirmDialog(null, tipoCombo, "Escolha a classe", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                    Personagem p = criarPersonagem.apply(new String[]{nome, (String) tipoCombo.getSelectedItem()});
                    String msg = arena.cadastrarPersonagem(p);
                    JOptionPane.showMessageDialog(null, msg);
                }
            });

            btnCadastrarPers.addActionListener((ActionEvent e) -> {
                String nome = JOptionPane.showInputDialog("Digite o nome:");
                if (nome == null || nome.trim().isEmpty()) return;

                String[] tipos = {"Guerreiro", "Mago"}; 
                JComboBox<String> tipoCombo = new JComboBox<>(tipos);
                if (JOptionPane.showConfirmDialog(null, tipoCombo, "Escolha a classe", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                    try {
                        String v = JOptionPane.showInputDialog("Vida:");
                        String a = JOptionPane.showInputDialog("Ataque:");
                        String d = JOptionPane.showInputDialog("Defesa:");
                        Personagem p = criarPersonagem.apply(new String[]{nome, (String) tipoCombo.getSelectedItem(), v, a, d});
                        String msg = arena.cadastrarPersonagem(p);
                        JOptionPane.showMessageDialog(null, msg);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Erro: Digite apenas números!");
                    }
                }
            });

            btnListar.addActionListener((ActionEvent e) -> {
                exibirTextoLongo("Lista de Personagens", arena.listarPersonagens());
            });

            btnBatalhar.addActionListener((ActionEvent e) -> {
                if (arena.getQuantidadePersonagens() < 2) {
                    JOptionPane.showMessageDialog(null, "Cadastre pelo menos 2 personagens.");
                    return;
                }
                String n1 = JOptionPane.showInputDialog("Digite o NOME do primeiro lutador:");
                String n2 = JOptionPane.showInputDialog("Digite o NOME do segundo lutador:");
                if (n1 != null && n2 != null) {
                    String log = arena.realizarBatalha(n1, n2);
                    exibirTextoLongo("Log da Batalha", log);
                }
            });

            btnHistorico.addActionListener((ActionEvent e) -> {
                exibirTextoLongo("Histórico", arena.listarBatalhas());
            });

            btnEstatisticas.addActionListener((ActionEvent e) -> {
                JOptionPane.showMessageDialog(null, Personagem.obterEstatisticas());
            });

            btnSair.addActionListener(e -> System.exit(0));

            add(painelPrincipal);
            setVisible(true);
        }

        // Método auxiliar para rolar textos longos no JOptionPane
        private void exibirTextoLongo(String titulo, String texto) {
            JTextArea textArea = new JTextArea(15, 40);
            textArea.setText(texto);
            textArea.setEditable(false);
            JOptionPane.showMessageDialog(null, new JScrollPane(textArea), titulo, JOptionPane.INFORMATION_MESSAGE);
        }
    }   

    public static void main(String[] args) {
        new GUI(new Arena());
    }
}