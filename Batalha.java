public class Batalha {

    // Atributos privados
    private Personagem personagem1;
    private Personagem personagem2;
    private Personagem vencedor;
    private String log; // resumo do que aconteceu na batalha

    // -------------------------------------------------------
    // Construtor — recebe os dois personagens e já executa a batalha
    // -------------------------------------------------------
    public Batalha(Personagem p1, Personagem p2) {
        this.personagem1 = p1;
        this.personagem2 = p2;
        this.log = "";
        executarBatalha();
    }

    // -------------------------------------------------------
    // Lógica principal da batalha
    // -------------------------------------------------------
    private void executarBatalha() {
        log += "\n========== INÍCIO DA BATALHA ==========\n";
        log += personagem1.getNome() + " vs " + personagem2.getNome() + "\n";
        log += "=======================================\n";

        // Vida temporária para não alterar o objeto original durante os turnos
        int vidaP1 = personagem1.getVida();
        int vidaP2 = personagem2.getVida();

        int turno = 1;

        // Cada turno: os dois atacam simultaneamente
        while (vidaP1 > 0 && vidaP2 > 0) {
            log += "\n--- Turno " + turno + " ---\n";

            // Dano causado = ataque do atacante - defesa do defensor (mínimo 1)
            int danoEm2 = Math.max(1, personagem1.getAtaque() - personagem2.getDefesa());
            int danoEm1 = Math.max(1, personagem2.getAtaque() - personagem1.getDefesa());

            vidaP2 -= danoEm2;
            vidaP1 -= danoEm1;

            // Garante que vida não fique negativa no log
            if (vidaP2 < 0) vidaP2 = 0;
            if (vidaP1 < 0) vidaP1 = 0;

            log += personagem1.getNome() + " causa " + danoEm2
                 + " de dano em " + personagem2.getNome()
                 + " (Vida restante: " + vidaP2 + ")\n";

            log += personagem2.getNome() + " causa " + danoEm1
                 + " de dano em " + personagem1.getNome()
                 + " (Vida restante: " + vidaP1 + ")\n";

            turno++;
        }

        // Atualiza a vida real dos personagens
        personagem1.setVida(vidaP1);
        personagem2.setVida(vidaP2);

        // Determina o vencedor
        log += "\n========== RESULTADO ==========\n";

        if (vidaP1 > vidaP2) {
            vencedor = personagem1;
        } else if (vidaP2 > vidaP1) {
            vencedor = personagem2;
        } else {
            vencedor = null; // empate
        }

        if (vencedor != null) {
            log += "Vencedor: " + vencedor.getNome() + " [" + vencedor.getTipo() + "]\n";
            Personagem.registrarVitoria(vencedor.getTipo());
        } else {
            log += "Resultado: EMPATE!\n";
        }

        log += "================================\n";

        // Atualiza estatísticas globais
        Personagem.incrementarBatalhas();
    }

    // -------------------------------------------------------
    // Getters
    // -------------------------------------------------------
    public Personagem getPersonagem1() { return personagem1; }
    public Personagem getPersonagem2() { return personagem2; }
    public Personagem getVencedor()    { return vencedor; }
    public String getLog()             { return log; }

    // -------------------------------------------------------
    // toString — sobrescrita
    // -------------------------------------------------------
    @Override
    public String toString() {
        String resultado;
        if (vencedor != null) {
            resultado = "Vencedor: " + vencedor.getNome();
        } else {
            resultado = "Empate";
        }
        return "Batalha: " + personagem1.getNome()
             + " vs " + personagem2.getNome()
             + " | " + resultado;
    }
}
