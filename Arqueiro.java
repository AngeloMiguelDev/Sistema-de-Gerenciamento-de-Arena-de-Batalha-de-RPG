public class Arqueiro extends Personagem {

    public Arqueiro(String nome) {
        super(nome, "Arqueiro", 100, 75, 50);
    }

    public Arqueiro(String nome, int vida, int ataque, int defesa) {
        super(nome, "Arqueiro", vida, ataque, defesa);
    }

    @Override
    public int calcularDano(Personagem defensor) {
        // Flecha Perfurante: O defensor só consegue usar 70% da sua defesa
        int defesaReduzida = (int) (defensor.getDefesa() * 0.70);
        return Math.max(1, this.getAtaque() - defesaReduzida);
    }
}