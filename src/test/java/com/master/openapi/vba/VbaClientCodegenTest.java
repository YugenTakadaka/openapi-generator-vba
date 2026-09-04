package com.master.openapi.vba;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.openapitools.codegen.CodegenConfig;
import org.openapitools.codegen.DefaultGenerator;
import org.openapitools.codegen.config.CodegenConfigurator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VbaClientCodegenTest {
    private static final Path FIXTURE = Path.of("examples", "fixtures", "minimal.yaml").toAbsolutePath();

    @TempDir
    Path temporaryDirectory;

    @Test
    void generatorIsDiscoverableThroughJavaSpi() {
        boolean discovered = ServiceLoader.load(CodegenConfig.class).stream()
                .map(ServiceLoader.Provider::get)
                .anyMatch(config -> "vba".equals(config.getName()));

        assertTrue(discovered, "The vba generator must be registered as a CodegenConfig service");
    }

    @Test
    void minimalFixtureGeneratesRepresentativeVbaSources() throws IOException {
        Path output = temporaryDirectory.resolve("generated");
        generate(output);

        String api = Files.readString(output.resolve("Apis/UsersApi.cls"));
        String model = Files.readString(output.resolve("Models/User.cls"));
        assertTrue(api.contains("Attribute VB_Name = \"UsersApi\""));
        assertTrue(api.contains("Option Explicit"));
        assertTrue(api.contains("Public Function getUser(ByVal id As String) As Object"));
        assertTrue(api.contains("Set response = m_Client.Invoke(\"GET\", path, bodyText)"));
        assertTrue(api.contains("Set getUser = m_Client.DeserializeObject(response.Body)"));
        assertFalse(api.contains("As User"), "Typed model responses must not be generated before hydration exists");
        assertTrue(model.contains("Attribute VB_Name = \"User\""));
        assertTrue(model.contains("Option Explicit"));
        assertTrue(model.contains("Public Property Get id() As String"));
        assertTrue(model.contains("Public Property Let name(ByVal value As String)"));
        assertTrue(model.contains("Public Property Get revision() As Variant\n    revision = m_revision"));
        assertTrue(model.contains("Public Property Let revision(ByVal value As Variant)\n    m_revision = value"));
        assertFalse(model.contains("Property Set revision"), "Scalar int64 Variants must not use object assignment");
    }

    @Test
    void repeatedGenerationIsDeterministicForVbaSources() throws IOException {
        Path firstOutput = temporaryDirectory.resolve("first");
        Path secondOutput = temporaryDirectory.resolve("second");
        generate(firstOutput);
        generate(secondOutput);

        List<Path> firstFiles = generatedVbaFiles(firstOutput);
        List<Path> secondFiles = generatedVbaFiles(secondOutput);
        assertEquals(firstFiles, secondFiles);
        for (Path relativePath : firstFiles) {
            assertEquals(
                    Files.readString(firstOutput.resolve(relativePath)),
                    Files.readString(secondOutput.resolve(relativePath)),
                    () -> relativePath + " changed between identical generations"
            );
        }
    }

    private static void generate(Path output) {
        CodegenConfigurator configurator = new CodegenConfigurator()
                .setGeneratorName("vba")
                .setInputSpec(FIXTURE.toString())
                .setOutputDir(output.toString());
        new DefaultGenerator(false).opts(configurator.toClientOptInput()).generate();
    }

    private static List<Path> generatedVbaFiles(Path output) throws IOException {
        try (Stream<Path> paths = Files.walk(output)) {
            return paths.filter(Files::isRegularFile)
                    .map(output::relativize)
                    .filter(path -> path.toString().endsWith(".cls"))
                    .sorted()
                    .toList();
        }
    }
}
