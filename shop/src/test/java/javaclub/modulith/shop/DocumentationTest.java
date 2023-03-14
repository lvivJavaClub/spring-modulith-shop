package javaclub.modulith.shop;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class DocumentationTest {

    ApplicationModules modules = ApplicationModules.of(ShopApplication.class);

    @Test
    void print_snippets_c4() {
        new Documenter(modules)
            .writeModulesAsPlantUml()
            .writeIndividualModulesAsPlantUml();
    }

    @Disabled
    @Test
    void print_snippets_plantuml() {
        var plantUml = Documenter.DiagramOptions.defaults().withStyle(Documenter.DiagramOptions.DiagramStyle.UML);
        new Documenter(modules)
            .writeModulesAsPlantUml(plantUml);
    }

    @Test
    void print_application_canvas() {
        new Documenter(modules)
            .writeModuleCanvases();
    }

}
