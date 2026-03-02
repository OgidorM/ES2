package com.es2.bridge;

import java.util.LinkedHashMap;

 // Utiliza LinkedHashMap para manter a ordem de inserção dos conteúdos, permitindo iteração ordenada quando necessário.

public class APIMoodle implements APIServiceInterface {

    // LinkedHashMap garante que os dados são acedidos pela ordem de inserção
    protected LinkedHashMap<String, String> content;

    private int contentIdCounter;

    public APIMoodle() {
        this.content = new LinkedHashMap<>();
        this.contentIdCounter = 0;
    }

    @Override
    public String getContent(String contentId) {
        if ("0".equals(contentId)) {
            if (content.isEmpty()) {
                return null;
            }
            // Concatena todos os valores do LinkedHashMap (mantém ordem de inserção)
            StringBuilder aggregated = new StringBuilder();
            for (String value : content.values()) {
                aggregated.append(value);
            }
            return aggregated.toString();
        }

        // Retorna o conteúdo específico ou null se não existir
        return content.get(contentId);
    }

    @Override
    public String setContent(String content) {
        // Incrementa o contador e gera um novo ID
        contentIdCounter++;
        String newId = String.valueOf(contentIdCounter);

        // Armazena o conteúdo com o ID gerado
        this.content.put(newId, content);

        return newId;
    }
}

