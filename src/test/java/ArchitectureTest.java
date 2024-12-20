import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ArchitectureTest {
    @Test
    @DisplayName("Rule resource layer only depends on service layer")
    void ruleResourceLayerOnlyDependsOnServiceLayer() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("org.fungover.laboration2");

        ArchRule rule = noClasses().that().resideInAPackage("..resource..")
                .should().dependOnClassesThat().resideInAPackage("..invalid..");
        rule.check(importedClasses);
    }
}
