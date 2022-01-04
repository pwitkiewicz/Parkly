package com.parkly.backend.common;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public abstract class AbstractTestConfig
{


}
