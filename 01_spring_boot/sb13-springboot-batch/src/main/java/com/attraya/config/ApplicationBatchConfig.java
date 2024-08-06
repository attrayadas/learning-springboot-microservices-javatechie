package com.attraya.config;

import com.attraya.entity.Customer;
import com.attraya.repository.CustomerRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class ApplicationBatchConfig {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    /** Item Reader */
    public FlatFileItemReader<Customer> itemReader() {
        FlatFileItemReader<Customer> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("C:/Users/Attraya/Documents/learning-springboot-microservices-javatechie/01_spring_boot/sb13-springboot-batch/MOCK_DATA.csv"));
        itemReader.setLinesToSkip(1); // skipping header
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    private LineMapper<Customer> lineMapper() {
        DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();
        // delimiter
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "firstName", "lastName", "email", "gender", "contactNo", "country", "dob");

        // to convert to Customer object
        BeanWrapperFieldSetMapper<Customer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Customer.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    /** Item Processor */
    @Bean
    public CustomerDataProcessor processor(){
        return new CustomerDataProcessor();
    }

    /** Item Writer */
    @Bean
    public RepositoryItemWriter<Customer> itemWriter(){
        RepositoryItemWriter<Customer> itemWriter = new RepositoryItemWriter<>();
        itemWriter.setRepository(customerRepository);
        itemWriter.setMethodName("save");
        return itemWriter;
    }

    /** Create step object */
    @Bean
    public Step importCustomerStep(){
        return stepBuilderFactory.get("importCustomerStep").<Customer, Customer>chunk(10)
                .reader(itemReader())
                .processor(processor())
                .writer(itemWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job runJob(){
        return jobBuilderFactory.get("importCustomerJob")
                .flow(importCustomerStep())
                .end().build();
    }

    @Bean
    public TaskExecutor taskExecutor(){
        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        taskExecutor.setConcurrencyLimit(10);
        return taskExecutor;
    }
}
