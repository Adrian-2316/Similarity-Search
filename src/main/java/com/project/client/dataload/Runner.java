package com.project.client.dataload;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Runner implements ApplicationRunner {

  /**
   * Run application.
   *
   * @param args - ApplicationArguments.
   */
  @Override
  public void run(ApplicationArguments args) {}
}
