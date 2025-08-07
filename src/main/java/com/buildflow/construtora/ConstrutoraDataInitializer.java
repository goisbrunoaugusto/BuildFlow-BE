package com.buildflow.construtora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ConstrutoraDataInitializer implements CommandLineRunner {

    @Autowired
    private ConstrutoraService construtoraService;

    @Override
    public void run(String... args) throws Exception {
        // Lista de construtoras principais para inicializar o sistema
        List<String> construtorasPrincipais = Arrays.asList(
            "Odebrecht",
            "OAS",
            "Andrade Gutierrez",
            "Camargo Corrêa",
            "Queiroz Galvão",
            "OAS Engenharia",
            "Construtora Norberto Odebrecht",
            "Construtora Brasil",
            "Construtora São José",
            "Construtora Santa Cruz",
            "MRV",
            "TECON",
            "EVEN",
            "CYRELA",
            "Brookfield"
        );

        // Criar construtoras se não existirem
        for (String nomeConstrutora : construtorasPrincipais) {
            try {
                construtoraService.findOrCreateByNome(nomeConstrutora);
                System.out.println("Construtora criada/encontrada: " + nomeConstrutora);
            } catch (Exception e) {
                // Log do erro mas continua a execução
                System.err.println("Erro ao criar construtora " + nomeConstrutora + ": " + e.getMessage());
            }
        }
    }
} 