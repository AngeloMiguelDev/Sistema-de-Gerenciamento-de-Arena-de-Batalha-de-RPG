import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Main {

    public static class GUI extends JFrame {
        private final Arena arena;
        
        public GUI(Arena arena) {
            this.arena = arena;
            setTitle("⚔️ Arena de Batalha Épica ⚔️");
            setSize(600, 650);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            // --- TÍTULO DA JANELA ---
            JLabel titulo = new JLabel("Arena de Batalha RPG", SwingConstants.CENTER);
            titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
            titulo.setForeground(new Color(44, 62, 80));
            titulo.setBorder(new EmptyBorder(20, 10, 20, 10));
            add(titulo, BorderLayout.NORTH);

            // --- PAINEL DE BOTÕES ---
            JPanel painelPrincipal = new JPanel(new GridLayout(7, 1, 15, 15));
            painelPrincipal.setBorder(new EmptyBorder(10, 40, 30, 40));

            JButton btnCadastrar = criarBotaoEstilizado("1. Cadastrar Personagem (Padrão)");
            JButton btnCadastrarPers = criarBotaoEstilizado("2. Cadastrar Personagem (Personalizado)");
            JButton btnListar = criarBotaoEstilizado("3. Listar Personagens");
            JButton btnBatalhar = criarBotaoEstilizado("4. Iniciar Batalha!");
            JButton btnHistorico = criarBotaoEstilizado("5. Histórico de Batalhas");
            JButton btnEstatisticas = criarBotaoEstilizado("6. Estatísticas da Arena");
            JButton btnSair = criarBotaoEstilizado("0. Sair do Jogo");
            
            // Destaca o botão de batalhar e o de sair com cores diferentes
            btnBatalhar.setBackground(new Color(231, 76, 60)); // Vermelho
            btnBatalhar.setForeground(Color.WHITE);
            btnSair.setBackground(new Color(149, 165, 166)); // Cinza

            painelPrincipal.add(btnCadastrar);
            painelPrincipal.add(btnCadastrarPers);
            painelPrincipal.add(btnListar);
            painelPrincipal.add(btnBatalhar);
            painelPrincipal.add(btnHistorico);
            painelPrincipal.add(btnEstatisticas);
            painelPrincipal.add(btnSair);

            add(painelPrincipal, BorderLayout.CENTER);

            // --- LÓGICA DE CRIAÇÃO ---
            java.util.function.Function<String[], Personagem> criarPersonagem = (args) -> {
                String nome = args[0];
                String tipo = args[1];
                
                if (args.length == 2) {
                    if (tipo.equals("Guerreiro")) return new Guerreiro(nome);
                    if (tipo.equals("Mago")) return new Mago(nome);
                    if (tipo.equals("Arqueiro")) return new Arqueiro(nome);
                    if (tipo.equals("Paladino")) return new Paladino(nome);
                    if (tipo.equals("Viking")) return new Viking(nome);
                    if (tipo.equals("Pedrão (Boss)")) return new Pedrao();
                    return new Guerreiro(nome);
                } else {
                    int v = Integer.parseInt(args[2]);
                    int a = Integer.parseInt(args[3]);
                    int d = Integer.parseInt(args[4]);
                    
                    if (tipo.equals("Guerreiro")) return new Guerreiro(nome, v, a, d);
                    if (tipo.equals("Mago")) return new Mago(nome, v, a, d);
                    if (tipo.equals("Arqueiro")) return new Arqueiro(nome, v, a, d);
                    if (tipo.equals("Paladino")) return new Paladino(nome, v, a, d);
                    if (tipo.equals("Viking")) return new Viking(nome, v, a, d);
                    if (tipo.equals("Pedrão (Boss)")) return new Pedrao(nome, v, a, d);
                    return new Guerreiro(nome, v, a, d);
                }
            };

            // --- AÇÕES DOS BOTÕES ---
            btnCadastrar.addActionListener((ActionEvent e) -> {
                String nome = JOptionPane.showInputDialog(this, "Digite o nome do personagem:", "Novo Herói", JOptionPane.QUESTION_MESSAGE);
                if (nome == null || nome.trim().isEmpty()) return;

                // Todas as classes 
                String[] tipos = {"Guerreiro", "Mago", "Arqueiro", "Paladino", "Viking", "Pedrão (Boss)"}; 
                JComboBox<String> tipoCombo = new JComboBox<>(tipos);
                
                if (JOptionPane.showConfirmDialog(this, tipoCombo, "Escolha a classe", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
                    Personagem p = criarPersonagem.apply(new String[]{nome, (String) tipoCombo.getSelectedItem()});
                    String msg = this.arena.cadastrarPersonagem(p);
                    JOptionPane.showMessageDialog(this, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            btnCadastrarPers.addActionListener((ActionEvent e) -> {
                String nome = JOptionPane.showInputDialog(this, "Digite o nome:", "Novo Herói Customizado", JOptionPane.QUESTION_MESSAGE);
                if (nome == null || nome.trim().isEmpty()) return;

                String[] tipos = {"Guerreiro", "Mago", "Arqueiro", "Paladino", "Viking", "Pedrão (Boss)"}; 
                JComboBox<String> tipoCombo = new JComboBox<>(tipos);
                
                if (JOptionPane.showConfirmDialog(this, tipoCombo, "Escolha a classe", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
                    try {
                        String v = JOptionPane.showInputDialog(this, "Vida:");
                        String a = JOptionPane.showInputDialog(this, "Ataque:");
                        String d = JOptionPane.showInputDialog(this, "Defesa:");
                        Personagem p = criarPersonagem.apply(new String[]{nome, (String) tipoCombo.getSelectedItem(), v, a, d});
                        String msg = this.arena.cadastrarPersonagem(p);
                        JOptionPane.showMessageDialog(this, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Erro: Digite apenas números para vida, ataque e defesa!", "Erro de Digitação", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            btnListar.addActionListener((ActionEvent e) -> {
                exibirTextoLongo("Lista de Personagens", this.arena.listarPersonagens());
            });

            // NOVO MÉTODO DE BATALHA: Agora lista os personagens existentes em dropdowns!
            btnBatalhar.addActionListener((ActionEvent e) -> {
                java.util.List<String> vivos = this.arena.getNomesPersonagensVivos();
                
                if (vivos.size() < 2) {
                    JOptionPane.showMessageDialog(this, "Cadastre pelo menos 2 personagens VIVOS para poder batalhar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // Cria os seletores contendo os personagens cadastrados
                JComboBox<String> combo1 = new JComboBox<>(vivos.toArray(new String[0]));
                JComboBox<String> combo2 = new JComboBox<>(vivos.toArray(new String[0]));
                
                JPanel painelSelecao = new JPanel(new GridLayout(2, 2, 10, 10));
                painelSelecao.add(new JLabel("Desafiante 1:"));
                painelSelecao.add(combo1);
                painelSelecao.add(new JLabel("Desafiante 2:"));
                painelSelecao.add(combo2);
                
                int resultado = JOptionPane.showConfirmDialog(this, painelSelecao, "Escolha os Lutadores", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                
                if (resultado == JOptionPane.OK_OPTION) {
                    String n1 = (String) combo1.getSelectedItem();
                    String n2 = (String) combo2.getSelectedItem();
                    
                    if (n1.equals(n2)) {
                        JOptionPane.showMessageDialog(this, "Um personagem não pode batalhar contra si mesmo!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    String log = this.arena.realizarBatalha(n1, n2);
                    exibirTextoLongo("Log da Batalha", log);
                }
            });

            btnHistorico.addActionListener((ActionEvent e) -> {
                exibirTextoLongo("Histórico de Batalhas", this.arena.listarBatalhas());
            });

            btnEstatisticas.addActionListener((ActionEvent e) -> {
                JOptionPane.showMessageDialog(this, Personagem.obterEstatisticas(), "Estatísticas da Arena", JOptionPane.INFORMATION_MESSAGE);
            });

            btnSair.addActionListener(e -> System.exit(0));

            setVisible(true);
        }

        private JButton criarBotaoEstilizado(String texto) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            botao.setFocusPainted(false);
            botao.setBackground(new Color(52, 152, 219));
            botao.setForeground(Color.WHITE);
            return botao;
        }

        private void exibirTextoLongo(String titulo, String texto) {
            JTextArea textArea = new JTextArea(15, 40);
            textArea.setText(texto);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
            textArea.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(textArea), titulo, JOptionPane.PLAIN_MESSAGE);
        }
    }   

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Usa o padrão se falhar
        }

        SwingUtilities.invokeLater(() -> new GUI(new Arena()));
    }
}