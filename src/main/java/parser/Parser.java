package parser;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public interface Parser {

    List<String[]> parse(Stream<String> fileStream);

}
