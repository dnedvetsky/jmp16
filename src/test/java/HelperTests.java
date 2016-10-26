import frontend.NotebookHelper;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by Dovashar on 25.10.2016.
 */
@RunWith(JUnit4.class)
public class HelperTests {
    private static NotebookHelper helper;

    @BeforeClass
    public static void setUp() {
        helper = new NotebookHelper();
        helper.deleteAllNotes();
        helper.printAllNotes();
        helper.addNote("tag1", "text1");
        helper.addNote("tag2", "text2");
    }

    @Before
    public void before() {
        helper.addNote("beforeNote", "text3");
    }

    @Test
    public void verifyNotesAreAdded() {
        helper.buildSearchCondition("tag", "tag1").printNotesViaSearch();
    }

    @After
    public void after()
    {
        helper.buildSearchCondition("tag", "beforeNote").deleteFoundNote();
    }

    @AfterClass
    public static void tearDown()
    {
        System.out.println("Printing all notes");
        helper.printAllNotes();
    }
}
