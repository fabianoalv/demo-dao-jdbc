package aplicacao;

import java.util.Date;

import model.entidades.Departamento;
import model.entidades.Vendedor;

public class Programa {

	public static void main(String[] args) {

		Departamento dp = new Departamento(1,"Livros");
		
		Vendedor vendedor = new Vendedor(1, "Fabiano", "fabiano@gmail.com", new Date(), 3000.00, dp);
		
		System.out.println(vendedor);

	}

}
