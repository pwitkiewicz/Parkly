package com.parkly.backend.config;

import com.parkly.backend.bizz.owner.OwnerServiceImpl;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration(
    classes = OwnerServiceImpl.class,
    loader = AnnotationConfigContextLoader.class
)
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceTest {

}
