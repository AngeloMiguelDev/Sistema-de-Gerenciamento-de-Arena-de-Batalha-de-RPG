public class Paladino extends Personagem {

    public Paladino(String nome) {
        super(nome, "Paladino", 150, 50, 100);
    }

    public Paladino(String nome, int vida, int ataque, int defesa) {
        super(nome, "Paladino", vida, ataque, defesa);
    }

    @Override
    public int calcularDano(Personagem defensor) {
        // Golpe de Escudo: Ataque base + metade da própria defesa
        int ataqueTotal = this.getAtaque() + (this.getDefesa() / 2);
        return Math.max(1, ataqueTotal - defensor.getDefesa());
    }
}