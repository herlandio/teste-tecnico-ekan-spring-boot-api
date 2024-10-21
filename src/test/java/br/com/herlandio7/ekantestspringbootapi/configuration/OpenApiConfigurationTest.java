package br.com.herlandio7.ekantestspringbootapi.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpenApiConfigurationTest {

    @Autowired
    private OpenAPI openAPI;

    @Test
    void testOpenApiConfiguration() {
        assertNotNull(openAPI);

        Info info = openAPI.getInfo();
        assertNotNull(info);
        assertEquals("Beneficiary Api", info.getTitle());
        assertEquals("1.0", info.getVersion());
        assertEquals("Manager beneficiaries and documents", info.getDescription());

        License license = info.getLicense();
        assertNotNull(license);
        assertEquals("License Apache 2.0", license.getName());
        assertEquals("http://springdoc.org", license.getUrl());

        Components components = openAPI.getComponents();
        assertNotNull(components);
        SecurityScheme securityScheme = components.getSecuritySchemes().get("bearerAuth");
        assertNotNull(securityScheme);
        assertEquals(SecurityScheme.Type.HTTP, securityScheme.getType());
        assertEquals("bearer", securityScheme.getScheme());
        assertEquals("JWT", securityScheme.getBearerFormat());
        assertEquals(SecurityScheme.In.HEADER, securityScheme.getIn());
        assertEquals("Authorization", securityScheme.getName());

        assertTrue(openAPI.getSecurity().stream().anyMatch(securityRequirement -> securityRequirement.containsKey("bearerAuth")));
    }
}
