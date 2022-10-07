package Arvores;
import java.util.function.Consumer;

import EstruturasLineares.Fila;
import EstruturasLineares.Pilha;

public class ArvoreAvl<T extends Comparable<T>> {
    class Elemento {
        Elemento pai;
        Elemento esquerda;
        Elemento direita;
        T valor;
    
        public Elemento(T valor) {
            this.valor = valor;
        }
    }
  
    private Elemento raiz;

    public boolean isVazia() {
        return raiz == null;
    }

    public Elemento adicionar(T valor) {
        Elemento e = new Elemento(valor);
        Elemento pai = this.raiz;

        System.out.println("Adicionando " + valor);

        while (pai != null) {
            if (valor.compareTo(pai.valor) < 0) {
                if (pai.esquerda == null) {
                    e.pai = pai;
                    pai.esquerda = e;
                    balanceamento(pai);
                    
                    return e;
                } else {
                    pai = pai.esquerda;
                }
            } else {
                if (pai.direita == null) {
                    e.pai = pai;
                    pai.direita = e;
                    balanceamento(pai);

                    return e;
                } else {
                    pai = pai.direita;
                }
            }
        }

        this.raiz = e;
        return e;
    }

    public void balanceamento(Elemento elemento) {
        while (elemento != null) {
            int fator = fb(elemento);

            if (fator > 1) {
                //Arvore mais profunda para esquerda, rotação para a direita
                if (fb(elemento.esquerda) > 0) {
                    System.out.println("RSD(" + elemento.valor + ")");
                    rsd(elemento);
                } else {
                    System.out.println("RDD(" + elemento.valor + ")");
                    rdd(elemento);
                }
            } else if (fator < -1) {
                //Arvore mais profunda para direita, rotação para a esquerda
                if (fb(elemento.direita) < 0) {
                    System.out.println("RSE(" + elemento.valor + ")");
                    rse(elemento);
                } else {
                    System.out.println("RDE(" + elemento.valor + ")");
                    rde(elemento);
                }
            }

            elemento = elemento.pai;
        }
    }
    
    public Elemento adicionar(Elemento pai, T valor) {
        Elemento e = new Elemento(valor);
        
        e.pai = pai;
         
        if (pai == null) {
            raiz = e;
        }
        
        return e;
    }

    public void remover(Elemento e) {
        if (e.esquerda != null)
            remover(e.esquerda);  
        
        if (e.direita != null)
            remover(e.direita);
        
        if (e.pai == null) {
            raiz = null;
        } else {
            if (e.pai.esquerda == e) {
                e.pai.esquerda = null;
            } else {
                e.pai.direita = null;
            }
        }
    }

    public void percorrer(Elemento e, Consumer<T> callback) {
        if (e != null) {
            if (e.valor == (Integer) 5) {
                System.out.println("");
            }
            
            callback.accept(e.valor);
            percorrer(e.esquerda, callback);
            percorrer(e.direita, callback);
        }
    }

    public Elemento pesquisar(Elemento e, T valor) {
        while (e != null) {
            if (e.valor.equals(valor)) {
                return e;
            } else if (valor.compareTo(e.valor) > 0) {
                e = e.direita;
            } else {
                e = e.esquerda;
            }
        }
        
        return null;
    }

    public int caminho(Elemento e) {
        int contador = 1;

        while (e.pai != null) { //Enquanto não alcançamos a raiz
            contador++;
            e = e.pai;
        }

        return contador;
    }

    public void percorrerInOrder(Elemento e, Consumer<T> callback) {
        if (e != null) {
            percorrerInOrder(e.esquerda, callback);
            callback.accept(e.valor);
            percorrerInOrder(e.direita, callback);
        }
    }

    public void percorrerPosOrder(Elemento e, Consumer<T> callback) {
        if (e != null) {
            percorrerPosOrder(e.esquerda, callback);
            percorrerPosOrder(e.direita, callback);
            callback.accept(e.valor);
        }
    }

    public void percorrer(Consumer<T> callback) {
        this.percorrer(raiz, callback);
    }

    public void percorrerInOrder(Consumer<T> callback) {
        this.percorrerInOrder(raiz, callback);
    }

    public void percorrerPosOrder(Consumer<T> callback) {
        this.percorrerPosOrder(raiz, callback);
    }

    public void percorrerLargura(Consumer<T> callback) {
        Fila<ArvoreAvl<T>.Elemento> fila = new Fila<>();

        fila.adicionar(raiz);

        while (!fila.isVazia()) {
            ArvoreAvl<T>.Elemento e = fila.remover();

            //visitando o valor do elemento atual
            callback.accept(e.valor); 

            if (e.esquerda != null) {
                fila.adicionar(e.esquerda);
            }

            if (e.direita != null) {
                fila.adicionar(e.direita);
            }
        }
    }

    public void percorrerProfundidade(Consumer<T> callback) {
        Pilha<ArvoreAvl<T>.Elemento> pilha = new Pilha<>();

        pilha.adicionar(raiz);

        while (!pilha.isVazia()) {
            ArvoreAvl<T>.Elemento e = pilha.remover();

            //visitando o valor do elemento atual
            callback.accept(e.valor); 

            if (e.direita != null) {
                pilha.adicionar(e.direita);
            }

            if (e.esquerda != null) {
                pilha.adicionar(e.esquerda);
            }
        }
    }

    private int altura(Elemento e){
        int esquerda = 0,direita = 0;
    
        if (e.esquerda != null) {
            esquerda = altura(e.esquerda) + 1;
        }
    
        if (e.direita != null) {
            direita = altura(e.direita) + 1;
        }
      
        return esquerda > direita ? esquerda : direita;
    }

    private int fb(Elemento e) {
        int esquerda = 0,direita = 0;
      
        if (e.esquerda != null) {
            esquerda = altura(e.esquerda) + 1;
        }
    
        if (e.direita != null) {
            direita = altura(e.direita) + 1;
        }
      
        return esquerda - direita;
    }
    
    private Elemento rse(Elemento e) {
        Elemento pai = e.pai;
        Elemento direita = e.direita;
    
        e.direita = direita.esquerda;
        e.pai = direita;
    
        direita.esquerda = e;
        direita.pai = pai;

        if (direita.pai == null) {
            this.raiz = direita;
        } else {
             if (pai.esquerda == e) {
                pai.esquerda = direita;
            } else {
                pai.direita = direita;
            }
        }
      
        return direita;
    }

    private Elemento rsd(Elemento e) {
        Elemento pai = e.pai;
        Elemento esquerda = e.esquerda;
      
        e.esquerda = esquerda.direita;
        e.pai = esquerda;
      
        esquerda.direita = e;
        esquerda.pai = pai;

        if (esquerda.pai == null) {
            this.raiz = esquerda;
        } else {
            if (pai.esquerda == e) {
                pai.esquerda = esquerda;
            } else {
                pai.direita = esquerda;
            }
        }
      
        return esquerda;
    }
    
    private Elemento rde(Elemento e) {
        e.direita = rsd(e.direita);
        return rse(e);
    }
    
    private Elemento rdd(Elemento e) {
        e.esquerda = rse(e.esquerda);
        return rsd(e);
    }

    public static void main(String args[]) {
        ArvoreAvl<Integer> a = new ArvoreAvl<>();
        
        a.adicionar(3);
        a.adicionar(1);
        a.adicionar(2);

        System.out.println("Altura da arvore: " + (a.altura(a.raiz) + 1));
    }
}