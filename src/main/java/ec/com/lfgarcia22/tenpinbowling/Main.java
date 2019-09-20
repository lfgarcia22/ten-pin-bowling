package ec.com.lfgarcia22.tenpinbowling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Application Started...");
        LOG.info("");
        LOG.info("Ten-Pin Bowling");
        LOG.info("By:\tLuis García Castro");
        LOG.info("");
        LOG.info("");
    }
}
