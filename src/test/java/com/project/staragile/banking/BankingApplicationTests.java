package com.project.staragile.banking;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.prometheus.PrometheusMeterRegistry;

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

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application", "finance-world");
    }

    @PostConstruct
    public void initCustomMetric() {
        if (meterRegistry instanceof PrometheusMeterRegistry) {
            PrometheusMeterRegistry promRegistry = (PrometheusMeterRegistry) meterRegistry;
            promRegistry.counter("custom_metric_initialized_total", "purpose", "init").increment();
        }
    }
}
