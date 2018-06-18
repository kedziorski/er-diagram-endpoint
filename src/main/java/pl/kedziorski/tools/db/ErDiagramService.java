package pl.kedziorski.tools.db;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import schemacrawler.schemacrawler.RegularExpressionExclusionRule;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaInfoLevelBuilder;
import schemacrawler.tools.integration.graph.GraphExecutable;
import schemacrawler.tools.integration.graph.GraphOutputFormat;
import schemacrawler.tools.options.OutputOptions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;

@Service
class ErDiagramService {

    private JdbcTemplate jdbc;

    private final static String FILENAME = "database.png";
    private final static String EXCLUDE_PATTERN = ".*aud.*||.*revinf.*";


    ErDiagramService(@Autowired JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }



    ByteArrayResource generateErDiagram() throws Exception {
        Connection conn = jdbc.getDataSource().getConnection();

        SchemaCrawlerOptions options = new SchemaCrawlerOptions();
        options.setSchemaInfoLevel(SchemaInfoLevelBuilder.standard());
        options.setTableInclusionRule(new RegularExpressionExclusionRule(EXCLUDE_PATTERN));

        GraphExecutable ge = new GraphExecutable("GraphExecutable");
        ge.setSchemaCrawlerOptions(options);

        Path outputFile = new File(FILENAME).toPath();

        OutputOptions outputOptions = new OutputOptions(GraphOutputFormat.png, outputFile);
        ge.setOutputOptions(outputOptions);

        ge.execute(conn);

        return new ByteArrayResource(Files.readAllBytes(outputFile));
    }
}
