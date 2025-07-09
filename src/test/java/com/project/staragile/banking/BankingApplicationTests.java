package com.project.staragile.banking;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class BankingApplication {

    private final MeterRegistry meterRegistry;

    public BankingApplication(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public static void main(String[] args) {
        SpringApplication.run(BankingApplication.class, args);
    }

    // ✅ Set common tags on all metrics
    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application", "finance-world");
    }

    // ✅ Custom metric increment on startup
    @PostConstruct
    public void initCustomMetric() {
        meterRegistry.counter("custom.metric.init", "env", "prod").increment();
    }
}
