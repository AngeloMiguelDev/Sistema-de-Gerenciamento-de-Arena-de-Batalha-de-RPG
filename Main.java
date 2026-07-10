import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Arena arena = new Arena();
        int opcao = -1;

        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║    ARENA DE BATALHA DE RPG       ║");
        System.out.println("╚══════════════════════════════════╝");

        while (opcao != 0) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Cadastrar personagem (atributos padrão por tipo)");
            System.out.println("2. Cadastrar personagem (atributos personalizados)");
            System.out.println("3. Listar personagens");
            System.out.println("4. Realizar batalha");
            System.out.println("5. Ver histórico de batalhas");
            System.out.println("6. Ver estatísticas da arena");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpa o buffer

            switch (opcao) {

                case 1:
                    System.out.print("Nome do personagem: ");
                    String nome1 = sc.nextLine();
                    System.out.println("Tipos disponíveis: Guerreiro, Mago, Arqueiro, Paladino, Viking");
                    System.out.print("Tipo: ");
                    String tipo1 = sc.nextLine();
                    arena.cadastrarPersonagem(nome1, tipo1);
                    break;

                case 2:
                    System.out.print("Nome do personagem: ");
                    String nome2 = sc.nextLine();
                    System.out.println("Tipos disponíveis: Guerreiro, Mago, Arqueiro, Paladino, Viking");
                    System.out.print("Tipo: ");
                    String tipo2 = sc.nextLine();
                    System.out.print("Vida: ");
                    int vida = sc.nextInt();
                    System.out.print("Ataque: ");
                    int ataque = sc.nextInt();
                    System.out.print("Defesa: ");
                    int defesa = sc.nextInt();
                    sc.nextLine();
                    arena.cadastrarPersonagem(nome2, tipo2, vida, ataque, defesa);
                    break;

                case 3:
                    arena.listarPersonagens();
                    break;

                case 4:
                    if (arena.getPersonagens().size() < 2) {
                        System.out.println("Cadastre pelo menos 2 personagens para batalhar.");
                        break;
                    }
                    arena.listarPersonagens();
                    System.out.print("Índice do personagem 1: ");
                    int idx1 = sc.nextInt();
                    System.out.print("Índice do personagem 2: ");
                    int idx2 = sc.nextInt();
                    sc.nextLine();
                    arena.realizarBatalha(idx1, idx2);
                    break;

                case 5:
                    arena.listarBatalhas();
                    break;

                case 6:
                    Personagem.exibirEstatisticas();
                    break;

                case 0:
                    System.out.println("Encerrando a arena. Até a próxima batalha!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        sc.close();
    }
}
