/**public class Personagem {

    // Atributos privados (encapsulamento)
    private String nome;
    private String tipo;
    private int vida;
    private int ataque;
    private int defesa;
    private boolean vivo;

    // Atributos estáticos — estatísticas gerais da arena
    private static int totalBatalhas = 0;
    private static int vitoriasGuerreiro = 0;
    private static int vitoriasMago = 0;
    private static int vitoriasArqueiro = 0;
    private static int vitoriasOutro = 0;

    // -------------------------------------------------------
    // Construtor COMPLETO (atributos personalizados)
    // -------------------------------------------------------
    public Personagem(String nome, String tipo, int vida, int ataque, int defesa) {
        this.nome = nome;
        this.tipo = tipo;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.vivo = true;
    }

    // -------------------------------------------------------
    // Construtor PADRÃO — sobrecarga (atributos pelo tipo)
    // -------------------------------------------------------
    public Personagem(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
        this.vivo = true;

        switch (tipo) {
            case "Guerreiro":
                this.vida = 100; this.ataque = 20; this.defesa = 100;
                break;
            case "Mago":
                this.vida = 100; this.ataque = 200; this.defesa = 25;
                break;
            case "Arqueiro":
                this.vida = 100; this.ataque = 75; this.defesa = 50;
                break;
            case "Paladino":
                this.vida = 100; this.ataque = 150; this.defesa = 200;
                break;
            case "Viking":
                this.vida = 100; this.ataque = 200; this.defesa = 150;
                break;
            default:
                this.vida = 100; this.ataque = 50; this.defesa = 50;
        }
    }

    // -------------------------------------------------------
    // Getters
    // -------------------------------------------------------
    public String getNome()   { return nome; }
    public String getTipo()   { return tipo; }
    public int getVida()      { return vida; }
    public int getAtaque()    { return ataque; }
    public int getDefesa()    { return defesa; }
    public boolean isVivo()   { return vivo; }

    // -------------------------------------------------------
    // Setters
    // -------------------------------------------------------
    public void setVida(int vida) {
        this.vida = vida;
        if (this.vida <= 0) {
            this.vida = 0;
            this.vivo = false;
        }
    }

    // -------------------------------------------------------
    // Métodos estáticos — estatísticas gerais da arena
    // -------------------------------------------------------
    public static int getTotalBatalhas() { return totalBatalhas; }

    public static void incrementarBatalhas() { totalBatalhas++; }

    public static void registrarVitoria(String tipo) {
        switch (tipo) {
            case "Guerreiro": vitoriasGuerreiro++; break;
            case "Mago":      vitoriasMago++;      break;
            case "Arqueiro":  vitoriasArqueiro++;  break;
            default:          vitoriasOutro++;     break;
        }
    }

    public static void exibirEstatisticas() {
        System.out.println("\n========== ESTATÍSTICAS DA ARENA ==========");
        System.out.println("Total de batalhas realizadas : " + totalBatalhas);
        System.out.println("Vitórias do Guerreiro        : " + vitoriasGuerreiro);
        System.out.println("Vitórias do Mago             : " + vitoriasMago);
        System.out.println("Vitórias do Arqueiro         : " + vitoriasArqueiro);
        System.out.println("Vitórias de outros tipos     : " + vitoriasOutro);
        System.out.println("===========================================\n");
    }

    // -------------------------------------------------------
    // toString — sobrescrita
    // -------------------------------------------------------
    @Override
    public String toString() {
        return "[" + tipo + "] " + nome
             + " | Vida: " + vida
             + " | Ataque: " + ataque
             + " | Defesa: " + defesa
             + " | Status: " + (vivo ? "Vivo" : "Morto");
    }
}
**/

public abstract class Personagem implements AcaoDeCombate {

    private String nome;
    private String tipo;
    private int vida;
    private int ataque;
    private int defesa;
    private boolean vivo;

    private static int totalBatalhas = 0;
    private static int vitoriasGuerreiro = 0;
    private static int vitoriasMago = 0;
    private static int vitoriasArqueiro = 0;
    private static int vitoriasOutro = 0;

    public Personagem(String nome, String tipo, int vida, int ataque, int defesa) {
        this.nome = nome;
        this.tipo = tipo;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.vivo = true;
    }

    public String getNome()   { return nome; }
    public String getTipo()   { return tipo; }
    public int getVida()      { return vida; }
    public int getAtaque()    { return ataque; }
    public int getDefesa()    { return defesa; }
    public boolean isVivo()   { return vivo; }

    public void setVida(int vida) {
        this.vida = vida;
        if (this.vida <= 0) {
            this.vida = 0;
            this.vivo = false;
        }
    }

    public static int getTotalBatalhas() { return totalBatalhas; }
    public static void incrementarBatalhas() { totalBatalhas++; }

    public static void registrarVitoria(String tipo) {
        switch (tipo) {
            case "Guerreiro": vitoriasGuerreiro++; break;
            case "Mago":      vitoriasMago++;      break;
            case "Arqueiro":  vitoriasArqueiro++;  break;
            default:          vitoriasOutro++;     break;
        }
    }

    public static String obterEstatisticas() {
        return "========== ESTATÍSTICAS DA ARENA ==========\n" +
               "Total de batalhas realizadas : " + totalBatalhas + "\n" +
               "Vitórias do Guerreiro        : " + vitoriasGuerreiro + "\n" +
               "Vitórias do Mago             : " + vitoriasMago + "\n" +
               "Vitórias do Arqueiro         : " + vitoriasArqueiro + "\n" +
               "Vitórias de outros tipos     : " + vitoriasOutro + "\n" +
               "===========================================\n";
    }

    @Override
    public String toString() {
        return "[" + tipo + "] " + nome
             + " | Vida: " + vida
             + " | Ataque: " + ataque
             + " | Defesa: " + defesa
             + " | Status: " + (vivo ? "Vivo" : "Morto");
    }
}