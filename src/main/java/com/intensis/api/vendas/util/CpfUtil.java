package com.intensis.api.vendas.util;

public class CpfUtil {

    public static String calcularDigitosVerificadores(String cpfSemDigitos) {
        if (cpfSemDigitos == null || !cpfSemDigitos.matches("\\d{9}")) {
            throw new IllegalArgumentException("CPF deve conter exatamente 9 dígitos numéricos.");
        }

        int primeiroDV = calcularDV(cpfSemDigitos, 10);
        int segundoDV = calcularDV(cpfSemDigitos + primeiroDV, 11);

        return "" + primeiroDV + segundoDV;
    }

    private static int calcularDV(String cpfParcial, int pesoInicial) {
        int soma = 0;
        for (int i = 0; i < cpfParcial.length(); i++) {
            int digito = Character.getNumericValue(cpfParcial.charAt(i));
            soma += digito * (pesoInicial - i);
        }

        int resto = soma % 11;
        int dv = 11 - resto;
        return (dv >= 10) ? 0 : dv;
    }

    public static String gerarCpfCompleto(String cpfSemDigitos) {
        return cpfSemDigitos + calcularDigitosVerificadores(cpfSemDigitos);
    }

    public static boolean validarCpf(String cpfCompleto) {
        if (cpfCompleto == null || !cpfCompleto.matches("\\d{11}")) {
            return false;
        }

        // Rejeita CPFs com todos os dígitos iguais
        if (cpfCompleto.chars().distinct().count() == 1) {
            return false;
        }

        String cpfSemDV = cpfCompleto.substring(0, 9);
        String dvCalculado = calcularDigitosVerificadores(cpfSemDV);

        return cpfCompleto.endsWith(dvCalculado);
    }

    public static String removerMascara(String cpf) {
        if (cpf == null) {
            return null;
        }

        // Remove todos os caracteres não numéricos
        return cpf.replaceAll("[^0-9]", "");
    }

}
