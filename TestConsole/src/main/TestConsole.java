package main;

import test.LogicaTest;
import utils.PropertyReader;

public class TestConsole {

	public static void main(String[] args) {

		System.out.println("inicia main " + PropertyReader.getProp("infoSys"));

		LogicaTest logica = new LogicaTest();
		logica.principalLogica();

		System.out.println("finaliza main");
	}

}
