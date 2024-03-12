package br.com.herlandio7.ekantestspringbootapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.herlandio7.ekantestspringbootapi.models.Document;
import br.com.herlandio7.ekantestspringbootapi.repositories.DocumentRepository;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Document saveDocument(Document document) {
        return documentRepository.save(document);
    }
}
