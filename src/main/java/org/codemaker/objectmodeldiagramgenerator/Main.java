package org.codemaker.objectmodeldiagramgenerator;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.codemaker.objectmodeldiagramgenerator.domain.entities.OmgDefinition;
import org.codemaker.objectmodeldiagramgenerator.domain.services.PumlDiagramService;
import org.codemaker.objectmodeldiagramgenerator.domain.valueobjects.PumlDiagram;
import org.codemaker.objectmodeldiagramgenerator.infrastructure.OmgDefinitionReader;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException {

    // 1. Set up all options
    Option inputOption = Option.builder("i").longOpt("input")
            .desc("path to the input XLSX file containing the object model(s) and additional information").valueSeparator('=')
            .required(true).hasArg(true).build();
    Option outputOption = Option.builder("o").longOpt("output")
            .desc("path to the output folder where the diagram files should be created. If not specified, the diagram files will be " +
                    "created in the same folder where the input file resides.")
            .valueSeparator('=').required(false).hasArg(true).build();
    Option helpOption = Option.builder("h").longOpt("help").desc("shows this usage info").required(false).hasArg(false).build();
    Options options = new Options();
    options.addOption(inputOption);
    options.addOption(outputOption);
    options.addOption(helpOption);

    // 2. Parse the arguments and show usage help, if needed.
    CommandLine commandLine = null;
    try {
      commandLine = new DefaultParser().parse(options, args);
    } catch (ParseException e) {
      System.err.println("Error while parsing the arguments. " + e.getMessage() + "\n");
    }

    if (commandLine == null || commandLine.hasOption(helpOption.getOpt()) || commandLine.getOptions().length == 0) {
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp(100, "objectmodeldiagramgenerator",
              "\nCreates a list of diagrams out of object model(s) which are defined in a specific format inside an Excel file.\n\n",
              options, "\n", true);
      return;
    }

    if (!commandLine.hasOption(inputOption.getOpt())) {
      System.err.println(
              "Option " + inputOption.getLongOpt() + " is missing. Without input filename there is nothing to do here. Call " +
                      "\"systemdiagramgenerator --help\" for a usage info.");
      return;
    }

    // 3. Derive input and output file information to be able to start the real work
    String inputFilename = commandLine.getOptionValue(inputOption.getOpt());
    Path inputFilePath = Paths.get(inputFilename);

    String outputFoldername = commandLine.getOptionValue(outputOption.getOpt());
    Path outputFolderPath;
    if (outputFoldername == null) {
      outputFolderPath = inputFilePath.toAbsolutePath().getParent();
    } else {
      outputFolderPath = Paths.get(outputFoldername);
    }

    // 4. Start the real work
    System.out.println();
    System.out.println("Preparing:");
    System.out.println("    input file:    " + inputFilePath.toAbsolutePath());
    System.out.println("    output folder: " + outputFolderPath.toAbsolutePath());

    System.out.println();
    System.out.println("Reading the Excel sheet:");
    OmgDefinitionReader definitionReader = new OmgDefinitionReader(Files.newInputStream(inputFilePath.toFile().toPath()));
    OmgDefinition definition = definitionReader.read();
    if (!Files.exists(outputFolderPath)) {
      Files.createDirectory(outputFolderPath);
    }

    System.out.println();
    System.out.println("Writing the diagrams:");
    PumlDiagramService pumlDiagramService = new PumlDiagramService(definition);
    List<PumlDiagram> pumlDiagrams = new ArrayList<>();
    pumlDiagrams.addAll(pumlDiagramService.createDiagrams(PumlDiagramService.Mode.gradually));
    pumlDiagrams.addAll(pumlDiagramService.createDiagrams(PumlDiagramService.Mode.everything));
    for (PumlDiagram pumlDiagram : pumlDiagrams) {
      Path outputFilePath = Paths.get(outputFolderPath + "/" + pumlDiagram.getName() + ".puml");
      System.out.println("    " + outputFilePath);
      FileWriter fileWriter = new FileWriter(outputFilePath.toFile(), false);
      fileWriter.write(pumlDiagram.getContent());
      fileWriter.close();
    }
  }
}
