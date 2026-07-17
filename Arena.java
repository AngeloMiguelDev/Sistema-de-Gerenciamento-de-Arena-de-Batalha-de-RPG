import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Arena {

    private Map<String, Personagem> personagens;
    private Set<String> nomesCadastrados;
    private List<Batalha> batalhas;

    public Arena() {
        this.personagens = new HashMap<>();
        this.nomesCadastrados = new HashSet<>();
        this.batalhas = new ArrayList<>();
    }

    public String cadastrarPersonagem(Personagem p) {
        if (nomesCadastrados.contains(p.getNome())) {
            return "Erro: O nome '" + p.getNome() + "' já está em uso!";
        }
        nomesCadastrados.add(p.getNome());
        personagens.put(p.getNome(), p);
        return "Personagem " + p.getNome() + " (" + p.getTipo() + ") cadastrado com sucesso!";
    }

    public String listarPersonagens() {
        if (personagens.isEmpty()) {
            return "Nenhum personagem cadastrado ainda.";
        }
        StringBuilder sb = new StringBuilder("\n========== PERSONAGENS ==========\n");
        for (Personagem p : personagens.values()) {
            sb.append(p.toString()).append("\n");
        }
        sb.append("=================================\n");
        return sb.toString();
    }

    public String realizarBatalha(String nome1, String nome2) {
        if (!personagens.containsKey(nome1) || !personagens.containsKey(nome2)) {
            return "Erro: Um ou ambos os nomes não foram encontrados na arena.";
        }

        Personagem p1 = personagens.get(nome1);
        Personagem p2 = personagens.get(nome2);

        if (!p1.isVivo() || !p2.isVivo()) {
            return "Erro: Um dos personagens já está morto e não pode batalhar.";
        }

        if (nome1.equalsIgnoreCase(nome2)) {
            return "Erro: Um personagem não pode batalhar contra si mesmo.";
        }

        Batalha b = new Batalha(p1, p2);
        batalhas.add(b);
        return b.getLog();
    }

    public String listarBatalhas() {
        if (batalhas.isEmpty()) {
            return "Nenhuma batalha realizada ainda.";
        }
        StringBuilder sb = new StringBuilder("\n========== HISTÓRICO DE BATALHAS ==========\n");
        for (int i = 0; i < batalhas.size(); i++) {
            sb.append("[").append(i + 1).append("] ").append(batalhas.get(i)).append("\n");
        }
        sb.append("===========================================\n");
        return sb.toString();
    }
    
    public int getQuantidadePersonagens() {
        return personagens.size();
    }

    // NOVO MÉTODO: Retorna apenas os nomes de quem está vivo para colocar na lista de escolha da batalha
    public List<String> getNomesPersonagensVivos() {
        List<String> vivos = new ArrayList<>();
        for (Personagem p : personagens.values()) {
            if (p.isVivo()) {
                vivos.add(p.getNome());
            }
        }
        return vivos;
    }
}