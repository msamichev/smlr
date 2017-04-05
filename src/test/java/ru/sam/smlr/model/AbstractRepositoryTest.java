package ru.sam.smlr.model;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import ru.sam.smlr.SmlrApplication;

/**
 * Created by msamichev on 04.04.2017.
 */
@TestPropertySource(locations = {"classpath:repositories-test.properties"})
@TestExecutionListeners(DbUnitTestExecutionListener.class)
@SpringBootTest(classes = {SmlrApplication.class})
@DirtiesContext
public abstract class AbstractRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
}
