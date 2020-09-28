public class No {
    public String valor;
    public int contador;
    public int altura;
    public No esquerda;
    public No direita;

    No(String valor) {
        this.valor = valor;
        this.contador = 1;
        this.altura = 0;
        this.esquerda = null;
        this.direita = null;
    }
}