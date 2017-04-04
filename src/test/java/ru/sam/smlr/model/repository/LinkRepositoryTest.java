package ru.sam.smlr.model.repository;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sam.smlr.model.AbstractRepositoryTest;
import ru.sam.smlr.model.Link;

import java.util.List;
import java.util.Optional;

/**
 * Created by msamichev on 04.04.2017.
 */
@DatabaseSetup(LinkRepositoryTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = LinkRepositoryTest.DATASET)
public class LinkRepositoryTest extends AbstractRepositoryTest {

    public static final String DATASET = "classpath:datasets/link-table.xml";
    private static final Long LINK_1_ID = 100500L;
    private static final String LINK_1_TEXT = "http://www.rambler.ru";
    private static final String LINK_TBS_TEXT = "http://www.ru";
    private static final Long LINK_NOR_FOUND_ID = 1L;

    @Autowired
    LinkRepository repository;

    @Test
    public void findOneExistingTest() {
        Optional<Link> got = repository.findOne(LINK_1_ID);
        MatcherAssert.assertThat(got.isPresent(), Matchers.equalTo(true));
        Link link = got.get();
        MatcherAssert.assertThat(link, Matchers.equalTo(new Link(LINK_1_ID, LINK_1_TEXT)));
    }


    @Test
    public void findOneNotExistingTest() {
        Optional<Link> got = repository.findOne(LINK_NOR_FOUND_ID);
        MatcherAssert.assertThat(got.isPresent(), Matchers.equalTo(false));
    }


    @Test
    public void saveNewTest() {
        Link toBeSaved = new Link(LINK_TBS_TEXT);
        Link got = repository.save(toBeSaved);

        List<Link> links = repository.findAll();

        MatcherAssert.assertThat(links, Matchers.hasSize(4));
        MatcherAssert.assertThat(got.getText(), Matchers.equalTo(LINK_TBS_TEXT));
    }

}
