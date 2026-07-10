import java.util.ArrayList;

public class Arena {

    // Listas que guardam todos os personagens e batalhas cadastrados
    private ArrayList<Personagem> personagens;
    private ArrayList<Batalha> batalhas;

    // -------------------------------------------------------
    // Construtor
    // -------------------------------------------------------
    public Arena() {
        this.personagens = new ArrayList<>();
        this.batalhas = new ArrayList<>();
    }

    // -------------------------------------------------------
    // Cadastrar personagem (construtor completo)
    // -------------------------------------------------------
    public void cadastrarPersonagem(String nome, String tipo, int vida, int ataque, int defesa) {
        Personagem p = new Personagem(nome, tipo, vida, ataque, defesa);
        personagens.add(p);
        System.out.println("Personagem cadastrado: " + p);
    }

    // -------------------------------------------------------
    // Cadastrar personagem (construtor padrão — sobrecarga)
    // -------------------------------------------------------
    public void cadastrarPersonagem(String nome, String tipo) {
        Personagem p = new Personagem(nome, tipo);
        personagens.add(p);
        System.out.println("Personagem cadastrado: " + p);
    }

    // -------------------------------------------------------
    // Listar todos os personagens
    // -------------------------------------------------------
    public void listarPersonagens() {
        if (personagens.isEmpty()) {
            System.out.println("Nenhum personagem cadastrado ainda.");
            return;
        }
        System.out.println("\n========== PERSONAGENS ==========");
        for (int i = 0; i < personagens.size(); i++) {
            System.out.println("[" + i + "] " + personagens.get(i));
        }
        System.out.println("=================================\n");
    }

    // -------------------------------------------------------
    // Realizar batalha entre dois personagens pelo índice
    // -------------------------------------------------------
    public void realizarBatalha(int indice1, int indice2) {
        if (indice1 < 0 || indice1 >= personagens.size()
         || indice2 < 0 || indice2 >= personagens.size()) {
            System.out.println("Índice inválido. Verifique a lista de personagens.");
            return;
        }

        Personagem p1 = personagens.get(indice1);
        Personagem p2 = personagens.get(indice2);

        if (!p1.isVivo() || !p2.isVivo()) {
            System.out.println("Erro: um dos personagens já está morto e não pode batalhar.");
            return;
        }

        if (indice1 == indice2) {
            System.out.println("Erro: um personagem não pode batalhar contra si mesmo.");
            return;
        }

        Batalha b = new Batalha(p1, p2);
        batalhas.add(b);

        // Exibe o log completo da batalha
        System.out.println(b.getLog());
    }

    // -------------------------------------------------------
    // Listar histórico de batalhas
    // -------------------------------------------------------
    public void listarBatalhas() {
        if (batalhas.isEmpty()) {
            System.out.println("Nenhuma batalha realizada ainda.");
            return;
        }
        System.out.println("\n========== HISTÓRICO DE BATALHAS ==========");
        for (int i = 0; i < batalhas.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + batalhas.get(i));
        }
        System.out.println("===========================================\n");
    }

    // -------------------------------------------------------
    // Getter da lista de personagens (usado no Main para validação)
    // -------------------------------------------------------
    public ArrayList<Personagem> getPersonagens() {
        return personagens;
    }
}
