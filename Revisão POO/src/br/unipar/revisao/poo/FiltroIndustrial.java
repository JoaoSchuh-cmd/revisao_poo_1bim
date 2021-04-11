package br.unipar.revisao.poo;

import java.util.Scanner;

public class FiltroIndustrial {
	double vazaoEntrada;
	double quantidadeSolidosProduzida, quantidadeSolidosEsperada;
	double percentualSolidosProduzido, percentualSolidosEsperado;
	int pressao;
	//**************************************** CALCULOS **************************************************
	double calculatePercentualSolidosProduzido() {
		calculateQuantidadeSolidosProduzida();
		percentualSolidosProduzido = quantidadeSolidosProduzida/10;
		return percentualSolidosProduzido;
	}
	
	void calculateQuantidadeSolidosProduzida() {
		if (pressao > 0 && pressao < 25) {
			quantidadeSolidosProduzida = vazaoEntrada*0.3;
		}
		if (pressao >= 25 && pressao < 50) {
			quantidadeSolidosProduzida = vazaoEntrada*0.6;
		}
		if (pressao >= 50 && pressao < 75) {
			quantidadeSolidosProduzida = vazaoEntrada*0.8;
		}
		if (pressao >= 75 && pressao < 100) {
			quantidadeSolidosProduzida = vazaoEntrada*0.9;
		}
	}
	
	void aumentaPressao(){
		pressao = pressao + 5;
	}
	
	void reduzPressao() {
		pressao = pressao - 5;
	}
	//-------------------------------------FIM CALCULATE -------------------------------------------------
	
	
	//**************************** VERIFICAR O QUE FAZER *************************************************
	void verify () {
		calculatePercentualSolidosProduzido();
		
		if (pressao >= 100) {precisaParar(); return;}
		if (percentualSolidosProduzido == percentualSolidosEsperado) {maquinaOK(); return;}
		if (percentualSolidosProduzido > percentualSolidosEsperado) {precisaReduzirPressao(); return;}
		if (percentualSolidosProduzido < percentualSolidosEsperado) {precisaAumentarPressao(); return;}
	}
	
	void precisaParar() {
		pressao = 0;
		System.out.println("!!!A m�quina precisa parar por conta da alta press�o!!!");
		System.out.println("*M�quina Desliga*");
		System.out.println("A configura��o de press�o teve de ser reduzida �: " + pressao);
		System.out.println("Por seguran�a, a m�quina foi desligada e ter� de esperar um pouco para reiniciar.");
	}
	
	void precisaReduzirPressao() {
		do {
			reduzPressao();
			calculatePercentualSolidosProduzido();
		} while (percentualSolidosProduzido > percentualSolidosEsperado);
		System.out.println("Altere a pressa para: " + pressao);
	}
	
	void precisaAumentarPressao() {
		do {
			aumentaPressao();
			calculatePercentualSolidosProduzido();
		} while (percentualSolidosProduzido < percentualSolidosEsperado);
		System.out.println("Altere a pressa para: " + pressao);
	}
	
	void maquinaOK () {
		System.out.println("A configura��o de press�o j� est� adequada � receita de hoje :)");
	}
	//---------------------------------- FIM VERIFY ------------------------------
	
	public static void main(String[] args) {
		FiltroIndustrial maquina = new FiltroIndustrial();
		
		Scanner ps = new Scanner(System.in);
		Scanner vE = new Scanner(System.in);
		Scanner vS = new Scanner(System.in);
		
		System.out.println("Qual a receita para hoje? Por favor, indique a porcentagem de s�lidos esperada: \n");
		maquina.percentualSolidosEsperado = ps.nextDouble();
		
		System.out.println("Qual a pressao atual da m�quina? \n");
		maquina.pressao = ps.nextInt();
		
		System.out.println("Qual a vazao de entrada atual da m�quina? \n");
		maquina.vazaoEntrada = vE.nextDouble();
		
		maquina.verify();
	}
	
}
