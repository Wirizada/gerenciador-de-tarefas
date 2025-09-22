package br.com.kanban.gerenciadordetarefas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessRuleException extends RuntimeException{
    public BusinessRuleException(String message){
        super(message);
    }
}
