package dev.pages.psuarez.springbootapistarter.config;

/*@Configuration
public class CorsConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
      .allowedOrigins("*")
      .allowedHeaders("Authorization", "Content-Type")
      .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE");
      // .maxAge(3600); // 1h Cache
  }
}*/

// TODO: Check if it works without this config
public class CorsConfig {}
