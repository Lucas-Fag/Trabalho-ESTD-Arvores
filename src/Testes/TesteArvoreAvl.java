package Testes;

import Arvores.ArvoreAvl;

public class TesteArvoreAvl {
    
    public void piorCaso() {

        for (int tamanhoVetor = 1; tamanhoVetor <= 1000; tamanhoVetor++) {
            int vetor[] = CriaVetor.criaVetorCrescente(tamanhoVetor);
            ArvoreAvl<Integer> arvore = new ArvoreAvl<>();
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
            ArvoreAvl<Integer> arvore = new ArvoreAvl<>();
            int quantidadeOperacoes;

            for (int valor : vetor) {
                arvore.adicionar(valor);
            }

            quantidadeOperacoes = arvore.getQuantidadeOperacoes();

            System.out.println("Vetor com tamanho " + tamanhoVetor + ", realizado " + quantidadeOperacoes + " para incluir e balancear.");
        }
    }

}
