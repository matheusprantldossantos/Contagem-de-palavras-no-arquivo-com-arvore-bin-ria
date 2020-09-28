public class ArvoreAVL {
    private No raiz;

    private static int altura(No no) {
        
        return (no == null ? -1 : no.altura);
    }

    private static No retorna_mais_profundo_na_esquerda(No no) {
        if (no.esquerda != null) {
            return retorna_mais_profundo_na_esquerda(no.esquerda);
        }
        return no;
    }

    private static No rotacao_direita(No raiz) {
        
        No aux = raiz.esquerda;
        raiz.esquerda = aux.direita;
        aux.direita = raiz;
        
        raiz.altura = Math.max(altura(raiz.direita), altura(raiz.esquerda)) + 1;
        aux.altura = Math.max(altura(aux.esquerda), raiz.altura) + 1;
        return aux;
    }

    private static No rotacao_esquerda(No raiz) {
        
        No aux = raiz.direita;
        raiz.direita = aux.esquerda;
        aux.esquerda = raiz;
        
        raiz.altura = Math.max(altura(raiz.direita), altura(raiz.esquerda)) + 1;
        aux.altura = Math.max(altura(aux.direita), raiz.altura) + 1;
        return aux;
    }

    private static No rotacao_esquerda_direita(No raiz) {
        raiz.esquerda = rotacao_esquerda(raiz.esquerda);
        return rotacao_direita(raiz);
    }

    private static No rotacao_direita_esquerda(No raiz) {
        raiz.direita = rotacao_direita(raiz.direita);
        return rotacao_esquerda(raiz);
    }

    private static No insere_elemento(No raiz, String valor) {
        if (raiz == null) { 
            return new No(valor);
        } else if (valor.compareTo(raiz.valor) < 0) { 

            raiz.esquerda = insere_elemento(raiz.esquerda, valor);
            if (altura(raiz.esquerda) - altura(raiz.direita) == 2) {
                if (valor.compareTo(raiz.esquerda.valor) < 0) {
                    raiz = rotacao_direita(raiz);
                } else {
                    raiz = rotacao_esquerda_direita(raiz);
                }
            }
        } else if (valor.compareTo(raiz.valor) > 0) {
            raiz.direita = insere_elemento(raiz.direita, valor);
            if (altura(raiz.direita) - altura(raiz.esquerda) == 2) {
                if (valor.compareTo(raiz.direita.valor) > 0) {
                    raiz = rotacao_esquerda(raiz);
                } else {
                    raiz = rotacao_direita_esquerda(raiz);
                }
            }
        } else {
            raiz.contador += 1;
        }
        raiz.altura = Math.max(altura(raiz.esquerda), altura(raiz.direita)) + 1;
        return raiz;
    }

    private static No remove_elemento(No raiz, String valor) {
        if (valor.compareTo(raiz.valor) < 0) {
            if (raiz.esquerda != null) {
                raiz.esquerda = remove_elemento(raiz.esquerda, valor);
                if (altura(raiz.direita) - altura(raiz.esquerda) == 2) {
                    if (raiz.esquerda == null) { 
                        
                        raiz = rotacao_direita(raiz);
                    } else if (valor.compareTo(raiz.esquerda.valor) < 0) { 
                        raiz = rotacao_esquerda(raiz);
                    } else {
                        raiz = rotacao_direita_esquerda(raiz);
                    }
                }
            }
        } else if (valor.compareTo(raiz.valor) > 0) {
            if (raiz.direita != null) {
                raiz.direita = remove_elemento(raiz.direita, valor);
                if (altura(raiz.esquerda) - altura(raiz.direita) == 2) {
                    if (raiz.direita == null) {
                        raiz = rotacao_direita(raiz);
                    } else if (valor.compareTo(raiz.direita.valor) > 0) {
                        raiz = rotacao_direita(raiz);
                    } else {
                        raiz = rotacao_esquerda_direita(raiz);
                    }
                }
            }
        } else { 
            if (raiz.esquerda == null && raiz.direita == null) { 
                return null;
            } else if (raiz.esquerda != null && raiz.direita == null) {
                return raiz.esquerda;
            } else if (raiz.esquerda == null && raiz.direita != null) { 
                return raiz.direita;
            } else { 
                
                No aux = retorna_mais_profundo_na_esquerda(raiz.direita);
                String temp = raiz.valor;
                raiz.valor = aux.valor;
                aux.valor = temp;
                raiz.direita = remove_elemento(raiz.direita, valor);

               
                raiz.direita.altura = Math.max(altura(raiz.direita.esquerda), altura(raiz.direita.direita)) + 1;
            }
        }

       
        raiz.altura = Math.max(altura(raiz.esquerda), altura(raiz.direita)) + 1;
        return raiz;
    }

    private static boolean existe_elemento(No no, String valor) {
        if (no == null) {
            return false;
        } else if (valor.compareTo(no.valor) == 0) {
            return true;
        } else if (valor.compareTo(no.valor) < 0) {
            return existe_elemento(no.esquerda, valor);
        } else {
            return existe_elemento(no.direita, valor);
        }
    }

    private static No retorna_no(No no, String valor) {
        if (no == null) {
            return null;
        } else if (valor.compareTo(no.valor) == 0) {
            return no;
        } else if (valor.compareTo(no.valor) < 0) {
            return retorna_no(no.esquerda, valor);
        } else {
            return retorna_no(no.direita, valor);
        }
    }

    private static void imprime_preordem(No raiz) {
        if (raiz == null)
            return;
        System.out.print(raiz.valor + " ");
        imprime_preordem(raiz.esquerda);
        imprime_preordem(raiz.direita);
    }

    private static void imprime_inordem(No raiz) {
        if (raiz == null)
            return;
        imprime_inordem(raiz.esquerda);
        System.out.print(raiz.valor + " ");
        imprime_inordem(raiz.direita);
    }

    private static void imprime_posordem(No raiz) {
        if (raiz == null)
            return;
        imprime_posordem(raiz.esquerda);
        imprime_posordem(raiz.direita);
        System.out.print(raiz.valor + " ");
    }

    private static No esvazia(No no) {
        if (no.esquerda != null)
            no.esquerda = esvazia(no.esquerda);
        if (no.direita != null)
            no.direita = esvazia(no.direita);
        return null;
    }

    public void insere_elemento(String valor) {
        this.raiz = insere_elemento(this.raiz, valor);
    }

    public void remove_elemento(String valor) {
        this.raiz = remove_elemento(this.raiz, valor);
    }

    public boolean existe_elemento(String valor) {
        return existe_elemento(this.raiz, valor);
    }

    public No retorna_no(String valor) {
        return retorna_no(this.raiz, valor);
    }

    public void imprime_preordem() {
        imprime_preordem(this.raiz);
        System.out.println();
    }

    public void imprime_inordem() {
        imprime_inordem(this.raiz);
        System.out.println();
    }

    public void imprime_posordem() {
        imprime_posordem(this.raiz);
        System.out.println();
    }

    public void esvazia() {
        this.raiz = esvazia(this.raiz);
    }

    public int altura() {
        
        return this.raiz.altura + 1;
    }
}