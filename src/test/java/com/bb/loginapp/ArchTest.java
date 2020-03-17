package com.bb.loginapp;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.bb.loginapp");

        noClasses()
            .that()
            .resideInAnyPackage("com.bb.loginapp.service..")
            .or()
            .resideInAnyPackage("com.bb.loginapp.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.bb.loginapp.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
