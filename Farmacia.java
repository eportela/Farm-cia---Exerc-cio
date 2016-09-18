package Exercicios;

import javax.swing.*;

/**
 * Created by handyc on 17/09/16.
 */
public class Farmacia {


    static final int MAX = 2; //constante para o vetor
    static Medicamento[] medicamento = new Medicamento[MAX];
    static int controladorVet = 0;
    private static boolean fullFarm = true;

    public static void main(String[] args) {
        do {
            String op = JOptionPane.showInputDialog("\t\tDrogarias CoffeeCode\n\n"
                    + "[1] - Cadastrar Medicamentos\n"
                    + "[2] - Consultar Medicamentos\n"
                    + "[3] - Adiciona na cesta\n"
                    + "[4] - Retira da cesta\n"
                    + "[5] - Realiza Pagamento\n"
                    + "[6] - Sair\n\n");
            try {
                switch (Integer.parseInt(op)) {
                    case 1: {
                        cadastrarMedicamento();
                    }
                    break;

                    case 2: {
                        do{
                            consultaMedicamento();
                        }while (JOptionPane.showConfirmDialog(null, "Deseja consultar mais algum medicamento?") == JOptionPane.YES_OPTION);
                    }
                    break;
                    case 3: {
                        adicionaNaCesta();
                    }
                    break;

                    case 4: {

                    }
                    break;

                    case 5: {

                    }
                    break;

                    case 6: {
                        System.exit(0);
                    }
                    break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        }
        while (JOptionPane.showConfirmDialog(null, "Deseja realizar mais alguma operação?") == JOptionPane.YES_OPTION);

    }

    public static int verificaFarmacia(){
        for(int i = 0; i<MAX; i++){
            if((medicamento[i] == null)){
                return i;
            }
        }
        return MAX;
    }

    public static void cadastrarMedicamento() {

        boolean cad = false;
        controladorVet = verificaFarmacia();

        String codigo = "";
        String nome = "";
        String descricao = "";
        String tipo = "";
        String preco = "";
        String estoque = "";
        String numTipo = "";


        do {
            try {
                codigo = JOptionPane.showInputDialog("\t\tCadastro de medicamentos\n\n" +
                        "Código: ");
                nome = JOptionPane.showInputDialog("\t\tCadastro de medicamentos\n\n" +
                        "Nome: ");
                descricao = JOptionPane.showInputDialog("\t\tCadastro de medicamentos\n\n" +
                        "Descrição: ");
                do {

                    numTipo = JOptionPane.showInputDialog("\t\tCadastro de medicamentos\n\n" +
                            "TIPO DO MEDICAMENTO\n" +
                            "[1] - Solução em cápsulas/Comprimidos\n" +
                            "[2] - Solução em líquida\n" +
                            "[3] - Solução injetável\n\n"
                    );
                    switch (Integer.parseInt(numTipo)) {
                        case 1: {
                            tipo = "Solução em cápsulas/Comprimidos";
                        }
                        break;

                        case 2: {
                            tipo = "Solução em líquido";
                        }
                        break;

                        case 3: {
                            tipo = "Solução injetável";
                        }
                        break;

                        default:
                            JOptionPane.showMessageDialog(null, "Digite um valor correspondente");
                    }
                } while (Integer.parseInt(numTipo) <= 0 || Integer.parseInt(numTipo) > 3);

                preco = JOptionPane.showInputDialog("\t\tCadastro de medicamentos\n\n" +
                        "Preço: ");
                estoque = JOptionPane.showInputDialog("\t\tCadastro de medicamentos\n\n" +
                        "Estoque: ");
                cad = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                cad = false;
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                cad = false;
            }
            if (controladorVet < MAX) {
                if (cad == true) {
                    if(controladorVet>0){
                        for(int i = 0; i<controladorVet; i++){
                            if( (medicamento[i].getNome().equals(nome) && medicamento[i].getTipo().equals(tipo)) && (medicamento[i].getPreco() == Float.parseFloat(preco)) ){
                               String escolha = JOptionPane.showInputDialog(null, "Você já tem esse produto cadastrado. Deseja alterar o estoque?");
                                if(Integer.parseInt(escolha) == 1) {
                                    String valor = JOptionPane.showInputDialog(null, "Informe o novo valor do estoque referente ao medicamento: " + medicamento[i].getNome());
                                    medicamento[controladorVet] = new Medicamento(Integer.parseInt(codigo), nome, descricao, tipo, Float.parseFloat(preco));
                                    medicamento[i].setEstoque(Integer.parseInt(valor));
                                    JOptionPane.showMessageDialog(null, "Estoque do medicamento "+medicamento[i].getNome()+" atualizado");
                                    estoque = valor;
                                }
                            }
                        }
                    }
                    System.out.println("caiu aqui");
                    medicamento[controladorVet] = new Medicamento(Integer.parseInt(codigo), nome, descricao, tipo, Float.parseFloat(preco));
                    medicamento[controladorVet].setEstoque(Integer.parseInt(estoque));
                    controladorVet++;
                } else
                    JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o produto. Ocorreu erros ao salvar.");

            } else
                JOptionPane.showMessageDialog(null, "Compre mais memória em: www.alunosfucapi.com.br");

        }while (JOptionPane.showConfirmDialog(null, "Deseja cadastrar mais algum medicamento?") == JOptionPane.YES_OPTION);

        if(controladorVet==1)
            return;

    }

    public static void consultaMedicamento() {

        String nome = JOptionPane.showInputDialog("CONSULTA DE MEDICAMENTOS\n\n");
        int i = 0;
        int naoEncontrado = 0;
        int encontrado = 0;
        do {
            if(medicamento[i] != null) {
                System.out.println("caiu no primeiro if");
                if (medicamento[i].getNome().equals(nome)) {
                    medicamento[i].imprimir();
                    i++;
                    return;
                } else {
                    naoEncontrado++;
                }
            }else {
                System.out.println("caiu no else do primeiro if");
                i++;
            }
        } while (i < MAX);

        if (naoEncontrado <= MAX - 1 )
            JOptionPane.showMessageDialog(null, "O medicamento não foi encontrado");

    }

    public static void adicionaNaCesta(){

    }
}
