package com.es2.bridge;

// Especialização da abstração APIRequest que agrega todos os conteúdos de um serviço antes de os retornar.
// Esta classe demonstra como podemos estender a hierarquia de abstração sem modificar as implementações.
// Herda de APIRequest e sobrescreve o método getContent para fornecer comportamento especializado de agregação.

public class APIRequestContentAggregator extends APIRequest {

    public APIRequestContentAggregator() {
        super();
    }


     // Sobrescreve o método da classe pai para sempre retorna todos os conteúdos concatenados, independentemente do contentId.

    @Override
    public String getContent(String serviceId, String contentId) throws ServiceNotFoundException {
        // Obtém o serviço pelo ID
        APIServiceInterface service = services.get(serviceId);

        if (service == null) {
            throw new ServiceNotFoundException();
        }

        // Sempre delega com "0" para obter todos os conteúdos agregados
        return service.getContent("0");
    }
}

