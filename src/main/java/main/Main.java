package main;

import converter.Converter;
import org.apache.commons.io.FilenameUtils;
import teXTable.TableLayoutFactory;
import teXTable.TeXTableFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

        if (args.length == 1) {
            throw new IllegalArgumentException("Output path required.");
        }

        if (args.length < 1) {
            throw new IllegalArgumentException("Path to .csv file and output path must be specified as command line arguments.");
        }

        if (args.length > 2) {
            throw new IllegalArgumentException("Incorrect usage. You only need to specify the path to a .csv file and an output path.");
        }

        String pathToCSV = args[0];
        String outputPath = args[1] + FilenameUtils.getBaseName(pathToCSV) + ".tex";

        Converter converter = new Converter(pathToCSV, new TableLayoutFactory(), new TeXTableFactory());

        try {

            String convertedTable = converter.convert();

            try {

                Files.write(Paths.get(outputPath), convertedTable.getBytes());

            } catch(IOException e) {

                System.out.println("Could not write to output path.");
                System.exit(1);

            }

        } catch(IOException e) {

            System.out.println("Could not read .csv file. Check the path.");
            System.exit(1);

        }
    }
}
