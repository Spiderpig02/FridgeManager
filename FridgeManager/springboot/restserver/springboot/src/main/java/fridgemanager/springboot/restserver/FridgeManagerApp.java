package springboot.src.main.java.fridgemanager.springboot.restserver;

import com.fasterxml.jackson.databind.Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * The Spring application.
 */
@SpringBootApplication
public class FridgeManagerApp {

  @Bean
  public Module objectMapperModule() {
    return FileHandler.createJacksonModule();
  }

  public static void main(String[] args) {
    SpringApplication.run(FridgeManagerApp.class, args);
  }
}
