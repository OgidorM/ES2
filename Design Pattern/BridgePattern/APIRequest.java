package com.es2.bridge;

import java.util.HashMap;


 // Esta classe serve de interface entre o cliente e as implementações dos serviços.
 // O cliente interage sempre com esta classe, que por sua vez delega os pedidos para os objetos que contêm as implementações concretas (APIServiceInterface).

public class APIRequest {

    // Composição: APIRequest "has-a" collection of APIServiceInterface
    protected HashMap<String, APIServiceInterface> services;

    // Contador para gerar IDs únicos para cada serviço
    private int serviceIdCounter;

    public APIRequest() {
        this.services = new HashMap<>();
        this.serviceIdCounter = 0;
    }


     // Adiciona um novo serviço à coleção de serviços disponíveis.
     // Os serviços são instanciados dinamicamente e registados em runtime.

    public String addService(APIServiceInterface service) {
        // Incrementa o contador e gera um novo ID para o serviço
        serviceIdCounter++;
        String newServiceId = String.valueOf(serviceIdCounter);

        // Regista o serviço no HashMap
        services.put(newServiceId, service);

        return newServiceId;
    }


     // Retorna o conteúdo de um serviço específico.
     // Delega o pedido para a implementação concreta do serviço.

    public String getContent(String serviceId, String contentId) throws ServiceNotFoundException {
        // Obtém o serviço pelo ID
        APIServiceInterface service = services.get(serviceId);

        if (service == null) {
            throw new ServiceNotFoundException();
        }

        // Delega o pedido para a implementação concreta (Bridge delegation)
        return service.getContent(contentId);
    }


     // Armazena conteúdo num serviço específico.
     // Delega o pedido para a implementação concreta do serviço.

    public String setContent(String serviceId, String content) throws ServiceNotFoundException {
        // Obtém o serviço pelo ID
        APIServiceInterface service = services.get(serviceId);

        if (service == null) {
            throw new ServiceNotFoundException();
        }

        // Delega o pedido para a implementação concreta (Bridge delegation)
        return service.setContent(content);
    }
}

