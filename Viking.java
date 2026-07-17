public class Viking extends Personagem {

    public Viking(String nome) {
        super(nome, "Viking", 120, 90, 40);
    }

    public Viking(String nome, int vida, int ataque, int defesa) {
        super(nome, "Viking", vida, ataque, defesa);
    }

    @Override
    public int calcularDano(Personagem defensor) {
        int ataqueMomento = this.getAtaque();
        
        // Fúria Berserker: 30% de chance (Math.random() gera um número entre 0.0 e 1.0)
        if (Math.random() <= 0.30) {
            ataqueMomento *= 2; // Dobra o ataque!
        }
        
        return Math.max(1, ataqueMomento - defensor.getDefesa());
    }
}