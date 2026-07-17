import java.awt.GridLayout;
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
