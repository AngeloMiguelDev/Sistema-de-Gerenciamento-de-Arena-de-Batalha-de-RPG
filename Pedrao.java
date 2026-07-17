public class Pedrao extends Personagem {

    // Se o usuário só escolher o Pedrão, o nome já é automático
    public Pedrao() {
        super("Pedrão", "Boss Supremo", 500, 300, 200);
    }

    // Caso você queira criar um "Clone do Pedrão" com outro nome
    public Pedrao(String nome, int vida, int ataque, int defesa) {
        super(nome, "Boss Supremo", vida, ataque, defesa);
    }

    @Override
    public int calcularDano(Personagem defensor) {
        // Presença Esmagadora: Defesa do alvo é inútil contra o Pedrão. Dano puro.
        // Garante no mínimo 50 de dano, mesmo que reduzam o ataque dele no futuro
        return Math.max(50, this.getAtaque());
    }
}