package com.hardy.leavetracking.infra.handler;

import com.hardy.leavetracking.infra.exception.InvalidLeaveBalanceException;
import com.hardy.leavetracking.infra.exception.LeaveRequestNotFoundException;

/**
 * Classe responsável por capturar e formatar as exceções de forma centralizada.
 * Em Pure Java, isto serve para imprimir mensagens de erro amigáveis na
 * consola.
 */
public class GlobalExceptionHandler {

    /**
     * Trata a exceção de saldo de licenças inválido.
     * 
     * @param e A exceção capturada.
     */
    public static void handleInvalidBalance(InvalidLeaveBalanceException exception) {
        System.err.println("[ERROR] Leave Balance Error: " + exception.getMessage());
    }

    /**
     * Trata a exceção de pedido de licença não encontrado.
     * 
     * @param e A exceção capturada.
     */
    public static void handleRequestNotFound(LeaveRequestNotFoundException exception) {
        System.err.println("[ERROR] Request Error: " + exception.getMessage());
    }

    /**
     * Trata erros genéricos e inesperados.
     * 
     * @param e A exceção capturada.
     */
    public static void handleGenericException(Exception exception) {
        System.err.println("[ERROR] Unexpected Error: " + exception.getMessage());
    }
}
