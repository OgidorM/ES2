package com.es2.bridge;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("=== Bridge Pattern Demo ===\n");

            // 1. Criar a abstração (APIRequest)
            // O cliente interage apenas com esta classe
            APIRequest apiRequest = new APIRequest();

            // 2. Criar implementação concreta (APIMoodle) dinamicamente
            // Novos serviços podem ser adicionados em runtime sem recompilar
            APIServiceInterface moodleService = new APIMoodle();

            // 3. Registar o serviço na abstração (composição, não herança)
            String serviceId = apiRequest.addService(moodleService);
            System.out.println("Serviço Moodle registado com ID: " + serviceId);

            // 4. Usar a abstração para armazenar conteúdos
            // A abstração delega os pedidos para a implementação concreta
            String contentId1 = apiRequest.setContent(serviceId, "Conteúdo A ");
            String contentId2 = apiRequest.setContent(serviceId, "Conteúdo B ");
            String contentId3 = apiRequest.setContent(serviceId, "Conteúdo C");

            System.out.println("\nConteúdos adicionados com IDs: " + contentId1 + ", " + contentId2 + ", " + contentId3);

            // 5. Obter conteúdo específico através da abstração
            System.out.println("\n--- Usando APIRequest (Abstraction) ---");
            System.out.println("Conteúdo ID " + contentId1 + ": " + apiRequest.getContent(serviceId, contentId1));
            System.out.println("Conteúdo ID " + contentId2 + ": " + apiRequest.getContent(serviceId, contentId2));

            // 6. Demonstrar a RefinedAbstraction (APIRequestContentAggregator)
            // Especialização que agrega todos os conteúdos
            System.out.println("\n--- Usando APIRequestContentAggregator (RefinedAbstraction) ---");

            APIRequestContentAggregator aggregator = new APIRequestContentAggregator();
            String aggServiceId = aggregator.addService(new APIMoodle());

            // Adicionar conteúdos ao novo serviço
            aggregator.setContent(aggServiceId, "Parte 1 | ");
            aggregator.setContent(aggServiceId, "Parte 2 | ");
            aggregator.setContent(aggServiceId, "Parte 3");

            // O aggregator sempre retorna todos os conteúdos concatenados
            System.out.println("Conteúdo agregado: " + aggregator.getContent(aggServiceId, "qualquer"));


        } catch (ServiceNotFoundException e) {
            System.err.println("Erro: Serviço não encontrado!");
        }
    }
}