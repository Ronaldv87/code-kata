package com.rstraub.java.katabase;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CanaryTest {
  @Test
  void canary() {
    assertThat(true).isFalse();
  }
}
