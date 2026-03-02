package com.es2.bridge;

 // Define a interface comum para todas as implementações de serviços.
 // Esta interface permite que diferentes serviços (Moodle, Google Drive, etc.) sejam utilizados de forma trocável usando da abstração.

public interface APIServiceInterface {

     // Retorna o conteúdo armazenado no serviço
     // Quando "0", retorna todos os conteúdos concatenados.
     // retorna o conteúdo armazenado ou null se não existir

    String getContent(String contentId);
    String setContent(String content);
}

