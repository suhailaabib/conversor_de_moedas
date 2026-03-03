
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        BuscaMoeda buscamoeda = new BuscaMoeda();

        int opcao = 0;

        while (opcao != 7) {

            System.out.println("***************************************");
            System.out.println("Converson de Moedas \n");
            System.out.println("1- Real para Dólar");
            System.out.println("2- Dólar para Real");
            System.out.println("3- Dólar para Euro");
            System.out.println("4- Euro para Dólar");
            System.out.println("5- Real para Euro");
            System.out.println("6- Euro para Real");
            System.out.println("7- Sair");
            System.out.println("***************************************");

            System.out.println("Escolha uma opção válida: ");

            opcao = leitura.nextInt();

            if (opcao == 7) {
                System.out.println("Encerrando Programa");
                break;
            }

            if (opcao < 1 || opcao > 7) {
                System.out.println("Opção inválida! Tente novamente.");
                continue;
            }


            System.out.println("Digite um valor para consulta:");
            var valorParaConverter = leitura.nextDouble();

            try {
                switch (opcao) {
                    case 1 -> mostrarConversao("BRL", "USD", valorParaConverter, buscamoeda);
                    case 2 -> mostrarConversao("USD", "BRL", valorParaConverter, buscamoeda);
                    case 3 -> mostrarConversao("USD", "EUR", valorParaConverter, buscamoeda);
                    case 4 -> mostrarConversao("EUR", "USD", valorParaConverter, buscamoeda);
                    case 5 -> mostrarConversao("BRL", "EUR", valorParaConverter, buscamoeda);
                    case 6 -> mostrarConversao("EUR", "BRL", valorParaConverter, buscamoeda);
                }
            } catch (Exception e) {
                System.out.println("Erro ao processar conversão: " + e.getMessage());
            }
        }
    }

    public static void mostrarConversao(String moeda1, String moeda2, double valor, BuscaMoeda consulta) throws IOException, InterruptedException {

        String json = consulta.converte(moeda1,moeda2);
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();


        BigDecimal taxa = BigDecimal.valueOf(jsonObject.get("conversion_rate").getAsDouble());


    BigDecimal resultado = taxa.multiply(BigDecimal.valueOf(valor));

    System.out.println("O valor " + valor + " [" + moeda1 + "] equivale a: =>>> "
    + resultado + " [" + moeda2 + "]\n");

     }

}
