public class Guerreiro extends Personagem {
    
    // Construtor Padrão
    public Guerreiro(String nome) {
        super(nome, "Guerreiro", 100, 20, 100);
    }

    // Construtor Personalizado
    public Guerreiro(String nome, int vida, int ataque, int defesa) {
        super(nome, "Guerreiro", vida, ataque, defesa);
    }

    @Override
    public int calcularDano(Personagem defensor) {
        // Guerreiro tem dano normal
        return Math.max(1, this.getAtaque() - defensor.getDefesa());
    }
}