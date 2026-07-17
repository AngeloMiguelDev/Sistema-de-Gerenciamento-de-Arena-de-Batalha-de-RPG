public class Mago extends Personagem {

    public Mago(String nome) {
        super(nome, "Mago", 100, 200, 25);
    }

    public Mago(String nome, int vida, int ataque, int defesa) {
        super(nome, "Mago", vida, ataque, defesa);
    }

    @Override
    public int calcularDano(Personagem defensor) {
        // Polimorfismo: Mago ignora metade da defesa do alvo!
        int defesaReduzida = defensor.getDefesa() / 2;
        return Math.max(1, this.getAtaque() - defesaReduzida);
    }
}