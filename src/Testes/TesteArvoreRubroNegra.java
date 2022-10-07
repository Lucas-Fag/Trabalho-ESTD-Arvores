package Testes;

import Arvores.ArvoreRubroNegra;

public class TesteArvoreRubroNegra {
    
    public void piorCaso() {

        for (int tamanhoVetor = 1; tamanhoVetor <= 1000; tamanhoVetor++) {
            int vetor[] = CriaVetor.criaVetorCrescente(tamanhoVetor);
            ArvoreRubroNegra<Integer> arvore = new ArvoreRubroNegra<>();
            int quantidadeOperacoes;

            for (int valor : vetor) {
                arvore.adicionar(valor);
            }

            quantidadeOperacoes = arvore.getQuantidadeOperacoes();

            System.out.println("Vetor com tamanho " + tamanhoVetor + ", realizado " + quantidadeOperacoes + " para incluir e balancear.");
        }
    }

    public void casoMedio() {

        for (int tamanhoVetor = 1; tamanhoVetor <= 1000; tamanhoVetor++) {
            int vetor[] = CriaVetor.criaVetorAleatorio(tamanhoVetor);
            ArvoreRubroNegra<Integer> arvore = new ArvoreRubroNegra<>();
            int quantidadeOperacoes;

            for (int valor : vetor) {
                arvore.adicionar(valor);
            }

            quantidadeOperacoes = arvore.getQuantidadeOperacoes();

            System.out.println("Vetor com tamanho " + tamanhoVetor + ", realizado " + quantidadeOperacoes + " para incluir e balancear.");
        }
    }
}
